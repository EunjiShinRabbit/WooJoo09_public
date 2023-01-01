import { useState, useEffect } from "react";
import api from "../../adminApi";
import SearchBar from "../../components/SearchBar";
import TopBar from "../../components/TopBar";
import Pagination from "../../components/Pagination";
import Loader from "../../components/Loader";
import Modal from "../../components/Modal";
import Header from "../../components/Header";
import Sidebar from "../../components/SideBar";
import { useNavigate } from "react-router-dom";

const MemberManagement = () =>{
  const navigate = useNavigate();
  const [lists, setLists] = useState('');
  const [limit, setLimit] = useState(10);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;
  const [pageStart, setPageStart] = useState(0);

  const [loading, setLoading] = useState(false);
  
  const [inputSearch, setInputSearch] = useState('');

  const [thisProfile, setThisProfile] = useState('');
  const [thisIntroduce, setThisIntroduce] = useState('');

  // 체크된 아이템을 담을 배열
  const [checkItems, setCheckItems] = useState([]);
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

  // 모달창 노출 여부 state
  const [modalOpen, setModalOpen] = useState(false);

  // 모달창 노출
  const showModal = () => {
      setModalOpen(true);
  };

  useEffect(() => {
    setInputSearch('');
    const fetchData = async () => {
     setLoading(true);
      try {
        const response = await api.adminMemberSelect();
        if(response.data.adminMemberSelect === "OK"){
          setLists(response.data.memberData);
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

  const onChangeSearch = (e) =>{
    setInputSearch(e.target.value);
  }

  const memberSearch = () =>{
    const fetchSearchData = async () => {
      setLoading(true);
       try {
         const response = await api.adminMemberSearch(inputSearch);
         if(response.data.adminMemberSearch === "OK"){
          setLists(response.data.memberData);
         }
       } catch (e) {
         console.log(e);
       }
       setLoading(false);
     };
    fetchSearchData();
    setCheckItems([]);
  }

  const memberDelete = () =>{
    const fetchDeleteData = async () => {
      setLoading(true);
       try {
         const response = await api.adminMemberDelete(checkItems);
         if(response.data.adminMemberDelete === "OK"){
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

  return(
    <div className="adminWrapper">
      <Header/>
      <Sidebar/>
    <div className="center">
      <TopBar name="회원 현황" high1="회원 관리"/>
      <div className="searchBar">
        <input type="text" placeholder="닉네임/아이디/등급/실명" value ={inputSearch} onChange={onChangeSearch}/>
        <button onClick={memberSearch}>검색</button>
      </div>
      <div>
        <label className="pageselect">
          페이지 당 표시할 회원 수:&nbsp;
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
          <table className="membertb">
            <thead>
              <tr>
                <input type='checkbox' name='select-all'
                  onChange={(e) => handleAllCheck(e.target.checked)}
                  // 데이터 개수와 체크된 아이템의 개수가 다를 경우 선택 해제 (하나라도 해제 시 선택 해제)
                  checked={checkItems.length === (
                  Math.floor(lists.length/limit) >= page ? 
                  limit :lists.length % limit)? true : false} 
                />
                <th>회원번호</th>
                <th>id</th>
                <th>닉네임</th>
                <th>상태</th>
                <th>등급</th>
                <th>공구 주최 수</th>
                <th>공구 참여 수</th>
                <th>받은 신고 수</th>
                <th>이름</th>
                <th>핸드폰 번호</th>
                <th>이메일</th>
                <th>생년월일</th>
                <th>가입일</th>
                <th>광고 수신 동의 여부</th>
                <th>프로필 이미지</th>
                <th>주최자 소개</th>
              </tr>
            </thead>
            <tbody>
              {lists &&
                lists.slice(offset, offset + limit)
                .map(({ memberNum, nickname, grade, countTrade, countPartner, phone, email, regDate, pfImg, receiveAd,
                id, isActive, countComplain, birthDate, introduce, realName }) => (
                  <tr key={memberNum}>
                    <td>
                    <input type='checkbox' 
                      name={`select-${memberNum}`}
                      onChange={(e) => handleSingleCheck(e.target.checked, memberNum)}
                      // 체크된 아이템 배열에 해당 아이템이 있을 경우 선택 활성화, 아닐 시 해제
                      checked={checkItems.includes(memberNum) ? true : false} 
                      />
                    </td>
                    <td>{memberNum}</td>
                    <td>{id}</td>
                    <td>{nickname}</td>
                    <td>{isActive}</td>
                    <td>{grade}</td>
                    <td>{countTrade}</td>
                    <td>{countPartner}</td>
                    <td>{countComplain}</td>
                    <td>{realName}</td>
                    <td>{phone}</td>
                    <td>{email}</td>
                    <td>{birthDate}</td>
                    <td>{regDate}</td>
                    <td>{receiveAd}</td>
                    <td>
                      {pfImg && <button onClick={() =>{
                        showModal()
                        setThisProfile(pfImg)
                      }}>이미지 보기</button>}
                      {modalOpen && <Modal setModalOpen={setModalOpen} imgUrl={thisProfile}/>}
                    </td>
                    <td>
                      {introduce && <button onClick={() =>{
                        showModal()
                        setThisIntroduce(introduce)
                      }}>소개 보기</button>}
                      {modalOpen && <Modal setModalOpen={setModalOpen} text={thisIntroduce}/>}
                    </td>
                  </tr>
                ))
              }
            </tbody>
          </table>
          <div className="btnlst">
            <button onClick={memberDelete}>비활성화</button>
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
export default MemberManagement;