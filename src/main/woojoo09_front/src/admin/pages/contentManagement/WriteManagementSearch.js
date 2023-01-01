import { useState, useEffect, useCallback } from "react";
import api from "../../adminApi";
import TopBar from "../../components/TopBar";
import Pagination from "../../components/Pagination";
import Loader from "../../components/Loader";
import { useParams, Link } from 'react-router-dom';
import Header from "../../components/Header";
import Sidebar from "../../components/SideBar";

const WriteManagementSearch = () =>{

  let { query } = useParams();

  const [lists, setLists] = useState('');
  const [limit, setLimit] = useState(10);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;
  const [pageStart, setPageStart] = useState(0);

  const [loading, setLoading] = useState(false);
  // const [prepared, setPrepared] = useState(false);

  // 체크된 아이템을 담을 배열
  const [checkItems, setCheckItems] = useState([]);

  const [inputSearch, setInputSearch] = useState('');

  // console.log(query);
  
  const onChangeSearch = (e) =>{
    setInputSearch(e.target.value);
  }

  const fetchSearchData = useCallback(
    async () => {
      setLoading(true);
      try {
        const response = await api.adminWriteSearch(query);
        if(response.data.adminWriteSearch === "OK"){
          setLists(response.data.writeData);
        }
      } catch (e) {
        console.log(e);
      }
      setLoading(false);}
  , [query]);

  useEffect(() => {
    fetchSearchData();
  }, [fetchSearchData]);

  if(loading) {
    return <div className="center"><Loader/></div>
  }
  
  // 체크박스 단일 선택
  const handleSingleCheck = (checked, id) => {
    if (checked) {
      // 단일 선택 시 체크된 아이템을 배열에 추가
      setCheckItems(prev => [...prev, id]);
    } else {
      // 단일 선택 해제 시 체크된 아이템을 제외한 배열 (필터)
      setCheckItems(checkItems.filter((el) => el !== id));
    }
  };

  // 체크박스 전체 선택
  const handleAllCheck = (checked) => {
    if(checked) {
      // 전체 선택 클릭 시 데이터의 모든 아이템(id)를 담은 배열로 checkItems 상태 업데이트
      const idArray = [];
      lists.slice(offset, offset + limit).forEach((el) => {idArray.push(el.writeNum)});
      setCheckItems(idArray);
    }
    else {
      // 전체 선택 해제 시 checkItems 를 빈 배열로 상태 업데이트
      setCheckItems([]);
    }
  }

  const writeDelete = () =>{
    window.localStorage.setItem("target", checkItems);
    const fetchDeleteData = async () => {
      setLoading(true);
       try {
         const response = await api.writeDelete();
         setLists(response.data);
       } catch (e) {
         console.log(e);
       }
       setLoading(false);
     };
    fetchDeleteData();
    setCheckItems([]);
    window.location.replace("/content/writeManagement");
  }

    return(
      <div className="adminWrapper">
        <Header/>
        <Sidebar/>
      <div className="center">
        <TopBar name="공동구매 관리" high1="콘텐츠 관리"/>
        <div className="searchBar">
          <input type="text" placeholder="카테고리, 상품, 작성자, 지역" value ={inputSearch} onChange={onChangeSearch}/>
          <button><Link to={`/content/writeManagement/search/${inputSearch}`}>검색</Link></button>
        </div>
        <div>
        <label className="pageselect">
            페이지 당 표시할 공동구매 수:&nbsp;
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
              <option value="20">30</option>
            </select>
          </label>
          <div className="tableWrapper">
            <table>
              <thead>
                <tr>
                  <input type='checkbox' name='select-all'
                    onChange={(e) => handleAllCheck(e.target.checked)}
                    // 데이터 개수와 체크된 아이템의 개수가 다를 경우 선택 해제 (하나라도 해제 시 선택 해제)
                    checked={checkItems.length === (
                    Math.floor(lists.length/limit) >= page ? 
                    limit :lists.length % limit)? true : false} 
                  />
                  <th>게시글 번호</th>
                  <th>카테고리</th>
                  <th>공동구매 제목</th>
                  <th>진행 상태</th>
                  <th>작성자</th>
                  <th>지역</th>
                  <th>지역 상세</th>
                  <th>작성일</th>
                </tr>
              </thead>
              <tbody>
                {lists &&
                  lists.slice(offset, offset + limit)
                  .map(({ tradeNum, product, writeDate, nickname, categoryName, doneTrade, city, town}) => (
                    <tr>
                      <td>
                      <input type='checkbox' 
                        name={`select-${tradeNum}`}
                        onChange={(e) => handleSingleCheck(e.target.checked, tradeNum)}
                        // 체크된 아이템 배열에 해당 아이템이 있을 경우 선택 활성화, 아닐 시 해제
                        checked={checkItems.includes(tradeNum) ? true : false} 
                        />
                      </td>
                      <td>{tradeNum}</td>
                      <td>{categoryName}</td>
                      <td><Link to={`/detail/${tradeNum}`}>{product}</Link></td>
                      <td>{doneTrade}</td>
                      <td>{nickname}</td>
                      <td>{city}</td>
                      <td>{town}</td>
                      <td>{writeDate}</td>
                    </tr>
                  ))
                }
              </tbody>
            </table>
            <div className="btnlst">
            <button onClick={writeDelete}>삭제</button>
            </div>
          </div>
          <Pagination
            total={lists.length}
            limit={limit}
            page={page}
            setPage={setPage}
            pageStart={pageStart}
            setPageStart={setPageStart}
            checkItems={checkItems} 
            setCheckItems={setCheckItems}
          />
        </div>
      </div>
      </div>
    );
};
export default WriteManagementSearch;