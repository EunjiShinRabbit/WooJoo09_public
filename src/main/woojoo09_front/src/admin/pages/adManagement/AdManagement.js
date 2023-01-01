import { useState, useEffect } from "react";
import api from "../../adminApi";
import SearchBar from "../../components/SearchBar";
import TopBar from "../../components/TopBar";
import Pagination from "../../components/Pagination";
import Loader from "../../components/Loader";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Header from "../../components/Header";
import Sidebar from "../../components/SideBar";

const AdManagement = () =>{
  const navigate = useNavigate();

  const [lists, setLists] = useState('');
  const [limit, setLimit] = useState(10);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;
  const [pageStart, setPageStart] = useState(0);

  const [loading, setLoading] = useState(false);

  // 체크된 아이템을 담을 배열
  const [checkItems, setCheckItems] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
     setLoading(true);
      try {
        const response = await api.bannerSelect();
        if(response.data.bannerSelect === "OK"){
          setLists(response.data.banner);
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

  const adminAdDelete = () =>{
    const fetchDeleteData = async () => {
      setLoading(true);
       try {
         const response = await api.bannerDelete(checkItems);
         if(response.data.bannerDelete === "OK"){
          navigate(0);
         }
       } catch (e) {
         console.log(e);
       }
       setLoading(false);
     };
    fetchDeleteData();
    setCheckItems([]);
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
      lists.forEach((el) => idArray.push(el.bannerNum));
      setCheckItems(idArray);
    }
    else {
      // 전체 선택 해제 시 checkItems 를 빈 배열로 상태 업데이트
      setCheckItems([]);
    }
  }

  return(
    <div className="adminWrapper">
    <Header/>
    <Sidebar/>
    <div className="center">
      <TopBar name="광고 관리" high1="배너 관리"/>
      {/* <SearchBar/> */}
      <div>
        <div className="tableWrapper">
          <table>
            <thead>
              <tr>
                <input type='checkbox' name='select-all'
                  onChange={(e) => handleAllCheck(e.target.checked)}
                  // 데이터 개수와 체크된 아이템의 개수가 다를 경우 선택 해제 (하나라도 해제 시 선택 해제)
                  checked={checkItems.length === (lists.length % 10) ? true : false} 
                />
                <th>배너 번호</th>
                <th>배너 이름</th>
                <th>이동 URL</th>
                <th>활성화 여부</th>
              </tr>
            </thead>
            <tbody>
              { lists &&
                lists.slice(offset, offset + limit)
                .map(({ bannerNum, bannerName, directUrl, imgUrl, isActive }) => (
                  <tr>
                    <td>
                    <input type='checkbox' 
                      name={`select-${bannerNum}`}
                      onChange={(e) => handleSingleCheck(e.target.checked, bannerNum)}
                      // 체크된 아이템 배열에 해당 아이템이 있을 경우 선택 활성화, 아닐 시 해제
                      checked={checkItems.includes(bannerNum) ? true : false} 
                      />
                    </td>
                    <td>{bannerNum}</td>
                    <td onClick={()=>{
                    navigate(`/ilovekirby/adManagement/AdManagementDetail/${bannerNum}`, {
                      state: {
                        bannerNum : bannerNum,
                        bannerName : bannerName,
                        directUrl : directUrl,
                        imgUrl : imgUrl,
                        isActive : isActive
                      }
                    })
                  }}
                  style={{cursor:"pointer"}}>{bannerName}</td>
                    <td>{directUrl}</td>
                    <td>{isActive}</td>
                  </tr>
                ))
              }
            </tbody>
          </table>
          <div className="btnlst">
          <button onClick={adminAdDelete}>삭제</button>
          <button><Link to={"/ilovekirby/adManagement/AdManagementAdd"}>추가</Link></button>
          </div>
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
export default AdManagement;