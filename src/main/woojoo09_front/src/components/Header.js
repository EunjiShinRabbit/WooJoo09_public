import { useState, useEffect } from "react";
import { NavLink ,Link, useNavigate, useLocation } from "react-router-dom";
import logo from "../resources/logo.png";
import logoWhite from "../resources/logoWhite.png";
import search from "../resources/search_purple.png";
import chat from "../resources/chat.png";
import chatMoving from "../resources/chatMoving.gif";
import profile from "../resources/profile.png";
import chatWhite from "../resources/chat_white2.png";
import chatMovingWhite from "../resources/chatMoving_white2.gif";
import profileWhite from "../resources/profile_white2.png";
import chatBlack from "../resources/chat_black.png";
import chatMovingBlack from "../resources/chatMoving_black.gif";
import profileBlack from "../resources/profile_black.png";
import { categories } from "../util/util";
import api from "../api/api"

const Header = ({isLogin, changeIsLogin, isAdmin, changeIsAdmin}) =>{

  const [NewChat, setNewChat] = useState(0);
  const [searchTarget, setSearchTarget] = useState("");
  const [viewLogin, setViewLogin] = useState();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      setViewLogin(true)
      try {
        const response = await api.chatReadCheck();
        if(response.data.state === 'login'){
          changeIsLogin(true);
          setNewChat(response.data.countUnreadChat);
          // console.log(response.data.countUnreadChat);
        } else if(response.data.state === 'admin'){
          changeIsLogin(true);
          changeIsAdmin(true);
          console.log("관리자입니다")
        }
        else{
          changeIsLogin(false);
        }
      } catch(e) {
        console.log(e);
      }
    };
    fetchData();
    let chatFetch = setInterval(() => { 
      fetchData();
    }, 30000); // 30초마다 한번
    return () => clearInterval(chatFetch);
    // 이게 좀더 나으려나? 재귀 setTimeOut
    // let timerId = setTimeout(function tick() {
    //   alert('tick');
    //   timerId = setTimeout(tick, 2000); // (*)
    // }, 2000);
  },[]);


  const [scrollPosition, setScrollPosition] = useState(0);
    const updateScroll = () => {
        setScrollPosition(window.scrollY || document.documentElement.scrollTop);
    }
    useEffect(()=>{
        window.addEventListener('scroll', updateScroll);
    });
  // const [current, setCurrent] = useState('main');
  
  const handleKeyPress = e => {
    if(e.key === 'Enter') {
      // console.log(e.target.value.trim());
      navigate(`/search/${e.target.value.trim()}`, {state : {isLogin : isLogin}});
    }
  }

  const handleButton = () => {
    if(searchTarget.trim()) {
      // console.log(searchTarget.trim());
      navigate(`/search/${searchTarget.trim()}`, {state : {isLogin : isLogin}});
    }
  }

  const onChangeTarget = (e) =>{
    setSearchTarget(e.target.value);
  }


  return(
    <div className={scrollPosition < 150 ? "header" : "changedHeader"}>
      <div className="headerTop">
        <div className="headerLogo">
          <Link to="/main" state={{isLogin : isLogin}}>
            {scrollPosition < 150 && <img src={logo} alt="우주공구"/>}
            {scrollPosition >= 150 && <img src={logoWhite} alt="우주공구"/>}
          </Link>
        </div>
        <div className="headerSearch">
          <input type="text" placeholder=""
          onKeyPress={handleKeyPress}
          onChange={onChangeTarget}/>
          <img src={search} alt="검색" onClick={handleButton}/>
        </div>
        {viewLogin && <div className="headerLogin">
          { isLogin && !isAdmin ?
          <>
          <Link to="/member">
            <img src={scrollPosition < 150 ? profileBlack : profileWhite} alt="내정보"/>
          </Link>
          <Link to="/chatlist">
            {NewChat == 0 ?
              <img src={scrollPosition < 150 ? chatBlack : chatWhite} alt="채팅"/>
              : <img src={scrollPosition < 150 ? chatMovingBlack : chatMovingWhite} alt="채팅"/>}
          </Link>
          </> : (isAdmin? <Link to="/ilovekirby/member" className="headerLoginBtn">관리자(admin)</Link>: 
          <button onClick={()=>{
            navigate("/login");
          }} className="headerLoginBtn">로그인</button>)}
        </div>}
      </div>
      <div className="headerBottom">
        <div className="headerCategory">
          {categories.map((category) => (
            <NavLink to ={`/category/${category.value}`}
            state = {{ isLogin : isLogin }}
            // className={category.value === current? "selected" : ""}
            // onClick={()=>{
            //   setCurrent(category.value)
            //   console.log(current)
            // }}
            activeClassName="active"
            >{category.name}</NavLink>
          ))}
        </div>
      </div>
    </div>
  );
};
export default Header;