import Header from "../components/Header";
import Footer from "../components/Footer"
import Update from "../components/Update";
import { useState, useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import api from "../api/api";

const UpdatePage = () =>{

  const [isLogin, setIsLogin] = useState("");
  const [isAdmin, setIsAdmin] = useState(false);
  const changeIsLogin = (value) => {
    setIsLogin(value);
  };
  const changeIsAdmin = (value) => {
    setIsAdmin(value);
  };

  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.tokencheck();
        if(response.data.result === "OK"){
          setIsLogin(true);
        } else{
          setIsLogin(false);
        }
      } catch (e) {
        console.log(e);
      }
    };
    fetchData();
  }, []);

  if (!isLogin){
      navigate("/login", {state : "유효하지 않은 접근입니다\n로그인 후 이용해 주세요"});
  } else{
    return(
      <div className="writeWrapper">
        <Header
          isLogin={isLogin} 
          changeIsLogin={changeIsLogin}
          isAdmin={isAdmin}
          changeIsAdmin={changeIsAdmin}/>
        <Update/>
        <Footer/>
      </div>
    );
  }
}
export default UpdatePage