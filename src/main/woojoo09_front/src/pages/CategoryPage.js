import { useParams, useNavigate, useLocation} from 'react-router-dom';
import { useState, useEffect } from "react";
import Footer from '../components/Footer';
import Header from "../components/Header";
import Loader from '../components/Loader';
import Main from "../components/Main";
import api from "../api/api"

const CategoryPage = () =>{
  let { categoryName } = useParams();
  const navigate = useNavigate();
  const location = useLocation();

  const [isLogin, setIsLogin] = useState(location.state.isLogin);
  const [isAdmin, setIsAdmin] = useState(false);
  const changeIsLogin = (value) => {
    setIsLogin(value);
  };
  const changeIsAdmin = (value) => {
    setIsAdmin(value);
  };

  // useEffect(() => {
  //   const fetchData = async () => {
  //     try {
  //       const response = await api.tokencheck();
  //       if(response.data.result === "OK"){
  //         setIsLogin(true);
  //       } else{
  //         setIsLogin(false);
  //       }
  //     } catch (e) {
  //       console.log(e);
  //     }
  //   };
  //   fetchData();
  // }, []);

  // if (!isLogin){
  //   navigate("/login", {state : "유효하지 않은 접근입니다\n로그인 후 이용해 주세요"});
  // } else{

    return(
      <>
      {/* <Loader/> */}
      <div className="categoryWrapper">
        <Header 
        isLogin={isLogin} 
        changeIsLogin={changeIsLogin}
        isAdmin={isAdmin}
        changeIsAdmin={changeIsAdmin}/>
        <Main categoryName = {categoryName}
        isLogin={isLogin}
        isAdmin={isAdmin}/>
        <Footer/>
      </div>
      </>
    );
  // }
  
}
export default CategoryPage