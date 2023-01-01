import { useState, useEffect } from "react";
import api from "../../adminApi";
import SearchBar from "../../components/SearchBar";
import TopBar from "../../components/TopBar";
import Pagination from "../../components/Pagination";
import Loader from "../../components/Loader";
import { Link, useNavigate } from 'react-router-dom';
import Header from "../../components/Header";
import Sidebar from "../../components/SideBar";

const ChatManagement = () =>{

  const navigate = useNavigate();
  const [lists, setLists] = useState('');
  const [limit, setLimit] = useState(10);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;
  const [pageStart, setPageStart] = useState(0);

  const [loading, setLoading] = useState(false);

  const [inputSearch, setInputSearch] = useState('');
  const onChangeSearch = (e) =>{
    setInputSearch(e.target.value);
  }
  useEffect(() => {
    const fetchData = async () => {
     setLoading(true);
      try {
        const response = await api.adminChatSelect();
        if(response.data.adminChatSelect === "OK"){
          setLists(response.data.chatData);
        }
      } catch (e) {
        console.log(e);
      }
      setLoading(false);
    };
    fetchData();
  }, []);
  
  if(loading) {
    return <div className="center"><Loader/></div>
  }

  const commentSearch = () =>{
    const fetchSearchData = async () => {
      setLoading(true);
       try {
         const response = await api.adminChatSearch(inputSearch);
         if(response.data.adminChatSearch === "OK"){
          setLists(response.data.chatData);
         }
       } catch (e) {
         console.log(e);
       }
       setLoading(false);
     };
    fetchSearchData();
  }

  return(
    <div className="adminWrapper">
      <Header/>
      <Sidebar/>
    <div className="center">
      <TopBar name="거래 참여 관리" high1="콘텐츠 관리"/>
      <div className="searchBar">
        <input type="text" placeholder="주최자, 참가자, 상품" value ={inputSearch} onChange={onChangeSearch}/>
        <button onClick={commentSearch}> 검색 </button>
      </div>
      <div>
      <label className="pageselect">
          페이지 당 표시할 거래 참여 수:&nbsp;
          <select
            type="number"
            value={limit}
            onChange={({ target: { value } }) => {
              setLimit(Number(value));
              setPage(1);
              setPageStart(0);
            }}
          >
            <option value="5">5</option>
            <option value="7">7</option>
            <option value="10" selected>10</option>
            <option value="12">12</option>
            <option value="20">20</option>
          </select>
        </label>
        <div className="tableWrapper">
          <table>
            <thead>
              <tr>
                <th>거래 참여 번호</th>
                <th>공동구매</th>
                <th>진행 상태</th>
                <th>주최자</th>
                <th>참가자</th>
                <th>대화 수</th>
                <th>채팅 상세</th>
              </tr>
            </thead>
            <tbody>
              { lists &&
                lists.slice(offset, offset + limit)
                .map(({ partnerNum, product, tradeNum, acceptTrade, host, partner, countChat }) => (
                  <tr>
                    <td>{partnerNum}</td>
                    <td><Link to={`/detail/${tradeNum}`} style={{zIndex:"100"}}>{product}</Link></td>
                    <td>{acceptTrade}</td>
                    <td>{host}</td>
                    <td>{partner}</td>
                    <td>{countChat}</td>
                    <td style={{cursor : "pointer"}}
                    onClick={()=>{navigate(`/ilovekirby/content/chatManagementDetail/${partnerNum}`)}}>상세 보기</td>
                  </tr>
                ))
              }
            </tbody>
          </table>
        </div>
        <Pagination
          total={lists.length}
          limit={limit}
          page={page}
          setPage={setPage}
          pageStart={pageStart}
          setPageStart={setPageStart}
        />
      </div>
    </div>
    </div>
  );
};
export default ChatManagement;