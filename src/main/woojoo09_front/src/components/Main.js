import { useState, useEffect } from "react";
import {categories, getCategory, citys, towns} from "../util/util";
import Card from "./Card";
import { Link } from "react-router-dom";
import api from "../api/api"
import { getCookie, setCookie } from "../util/cookie";
import Loader from "./Loader";
import disappointed from "../resources/disappointed.png"

const Main = ({categoryName, target, isLogin, isAdmin})=>{

  const [lineUp, setLineUp] = useState('recommend');
  const [city, setCity] = useState('');
  const [town, setTown] = useState('');

  const [lists, setLists] = useState([]);
  const [size, setSize] = useState(12);
  const [page, setPage] = useState(0);
  const [loading, setLoading] = useState(false);
  const [isLastPage, setIsLastPage] = useState(false);

  const changeLists = (value) => {
    setLists(value);
  };

  const [scrollPosition, setScrollPosition] = useState(0);
  const updateScroll = () => {
      setScrollPosition(window.scrollY || document.documentElement.scrollTop);
  }

  useEffect(()=>{
      window.addEventListener('scroll', updateScroll);
  });

  useEffect(() => {
    if(categoryName){
      // console.log("카테고리 통신 시작" + categoryName)
      const fetchData = async () => {
        setPage(0)
        setLoading(true);
         try {
          // console.log("카테고리이름: " + categoryName);
          // console.log("카테고리한글이름: " + getCategory(categoryName));
           const response = await api.tradeSelectCategory(getCategory(categoryName), lineUp, city, town, 0, size);
          //  console.log(response.data.content);
           setLists(response.data.content);
           setPage(1);
           setIsLastPage(false);
           if(response.data.last === true) setIsLastPage(true)
         } catch (e) {
           console.log(e);
         }
         setLoading(false);
       };
       fetchData();
    }else if(target){
      const fetchData = async () => {
        setPage(0)
        setLoading(true);
         try {
           const response = await api.tradeSearchSelect(target, 0, size);
          //  console.log(response.data.content);
           setLists(response.data.content);
           setPage(1);
           setIsLastPage(false);
           if(response.data.last === true) setIsLastPage(true)
         } catch (e) {
           console.log(e);
         }
         setLoading(false);
       };
       fetchData();
    }
    else{
      const fetchData = async () => {
        setPage(0)
        setLoading(true);
         try {
          // console.log("lineUp: "+lineUp + " city: " + city + " town: " + town + " page: " + page + "size" + size)
           const response = await api.tradeSelect(lineUp, city, town, 0, size);
          //  console.log(response.data.content);
           setLists(response.data.content);
           setPage(1);
           setIsLastPage(false);
           if(response.data.last === true) setIsLastPage(true)
         } catch (e) {
           console.log(e);
         }
         setLoading(false);
       };
       fetchData();
    }
  }, [categoryName, target, lineUp, city, town]);

  const appendList = () => {
    if(categoryName){
      const fetchData = async () => {
        setLoading(true);
        try {
          const response = await api.tradeSelectCategory(getCategory(categoryName), lineUp, city, town, page, size);
          // console.log(response.data.content);
          setLists(prev => ([...prev, ...response.data.content]));
          setPage(page + 1);
          if(response.data.last === true) {
            setIsLastPage(true);
            console.log()
          }
        } catch (e) {
          console.log(e);
        }
        setLoading(false);
      };
      fetchData();
      // console.log("이동해야하는 스크롤의 위치는 " + document.documentElement.scrollTop)
      goToBottom(document.documentElement.scrollTop)
    }else if(target){
      const fetchData = async () => {
        setLoading(true);
        try {
          const response = await api.tradeSearchSelect(target, page, size);
          // console.log(response.data.content);
          setLists(prev => ([...prev, ...response.data.content]));
          setPage(page + 1);
          if(response.data.last === true) {
            setIsLastPage(true);
            console.log()
          }
        } catch (e) {
          console.log(e);
        }
        setLoading(false);
      };
      fetchData();
      // console.log("이동해야하는 스크롤의 위치는 " + document.documentElement.scrollTop )
      goToBottom(document.documentElement.scrollTop)
    }
    else{
      const fetchData = async () => {
        setLoading(true);
        try {
          // console.log("lineUp: "+lineUp + " city: " + city + " town: " + town + " page: " + page + "size" + size)
          const response = await api.tradeSelect(lineUp, city, town, page, size);
          // console.log(response.data.content);
          setLists(prev => ([...prev, ...response.data.content]));
          setPage(page + 1);
          if(response.data.last === true) {
            setIsLastPage(true);
            console.log()
          }
        } catch (e) {
          console.log(e);
        }
        setLoading(false);
      };
      fetchData();
      // console.log("이동해야하는 스크롤의 위치는 " + document.documentElement.scrollTop )
      goToBottom(document.documentElement.scrollTop)
    }
  }

  const goToTop = () => {
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const goToBottom = (value) => {
    // console.log("이동할 스크롤 위치는 " + value);
    window.scrollTo({top: value, behavior: "smooth"});
  };

  if(loading) {
    return (
      <div className={(categoryName || target) ? (scrollPosition < 150 ? "category" : "category  changed")
        : (scrollPosition < 150 ? "main" : "main changed")}
        style={{height:"10000000vh"}}>
        <Loader/>
      </div>
    )
  }

  return(
    <div className={(categoryName || target) ? (scrollPosition < 150 ? "category" : "category  changed")
    : (scrollPosition < 150 ? "main" : "main changed")}>
      {!target && <p className="mainTitle">{categoryName? getCategory(categoryName) : "오늘의 공구"}</p>}
      {target && <p className="searchTitle">'
      <span style={{color : "#8679d9"}}>{target}</span>'에 대한 검색 결과</p>}
      <div className="maindiv">
        {!target && <div className="mainSelectBar">
          <div>
          <select
            value={lineUp}
            onChange={({ target: { value } }) => {
              setLineUp(value);
              // console.log(value)
            }}
          >
            <option value="recent">최신순</option>
            <option value="dateLimit">마감 임박순</option>
            <option value="recommend">추천순</option>
            <option value="lowPrice">낮은 가격순</option>
            <option value="highPrice">높은 가격순</option>
          </select>
          <select
            value={city}
            onChange={({ target: { value } }) => {
              setCity(value);
              // console.log(value)
              setTown('');
            }}
          >
            <option value="">지역 선택</option>
            {citys.map((e) => (
              <option key={e.city} value={e.name}>
                {e.name}
              </option>
            ))}
          </select>
          <select
            value={town}
            onChange={({ target: { value } }) => {
              setTown(value);
              // console.log(value)
            }}
          >
            <option value="">지역 전체</option>
            {towns
            .filter((e) => e.city === city)
            .map((e) => (
              <option key={e.town} value={e.name}>
                {e.name}
              </option>
            ))}
          </select>
          </div>
          {isLogin && !isAdmin && <Link to="/write"><button>등록하기</button></Link>}
        </div>}
        <div className="mainCardList">
          <Card lists={lists} isLogin={isLogin} isAdmin={isAdmin} changeLists={changeLists}/>
        </div>
        {lists.length === 0 && <div className="mainSorry">
            {/* <p>죄송합니다</p> */}
            <p>일치하는 항목이 없습니다</p>
            <img src={disappointed} alt="죄송합니다"></img>
          </div>}
        <div className="mainbuttons">
        {lists.length !== 0 && !isLastPage && <button onClick={
          appendList
        } className="mainbutton1"
          >더보기</button>}
        {lists.length !== 0 && <button onClick={goToTop}>맨위로</button>}
        </div>
      </div>
    </div>
  );
}
export default Main