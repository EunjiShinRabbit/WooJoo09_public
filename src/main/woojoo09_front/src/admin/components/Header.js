import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import logo from "../../resources/logoWhite.png"
import api from "../../api/api"

const Header = () =>{ 
  const [isLogin, setIsLogin] = useState(true);
  const navigate = useNavigate();

  const logout = () =>{
    const fetchData = async () => {
      try {
        const response = await api.logout();
        // console.log(response.data);
        if(response.data.logout === "OK") {
          navigate("/")
        }
      } catch (e) {
        console.log(e);
      }
    };
    fetchData();
  }

  return(
    <div className="adminheader">
      <Link to={'/'} className="logo">
      <img src={logo} alt="우주공구"/>
      {/* <span className="title">우주공구</span> */}
      </Link>
      {isLogin && 
        <p className="headerMenu">
          <span  style={{color : "#FFF", paddingRight: "10px"}}>관리자(ADMIN)</span>
          {/* 로그아웃 기능 만들어야함 */}
          <span onClick={logout} style={{color : "#FFF", cursor: "pointer"}}>로그아웃</span>
        </p>
      }
    </div>
  );
};

export default Header;