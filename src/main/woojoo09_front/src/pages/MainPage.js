import Banner from "../components/Banner";
import Header from "../components/Header";
import Main from "../components/Main";
import Footer from "../components/Footer"
import { useState, useEffect } from "react";
import { useLocation} from 'react-router-dom';
import api from "../api/api";
import Loader from "../components/Loader";

const MainPage = () =>{
  const location = useLocation();
  const [isLogin, setIsLogin] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);
  const [loading, setLoading] = useState(false);

  const changeIsLogin = (value) => {
    setIsLogin(value);
  };
  const changeIsAdmin = (value) => {
    setIsAdmin(value);
  };

  useEffect(() =>{
    if(location.state){
      // console.log(location.state.isLogin);
      setIsLogin(location.state.isLogin);
    }
  }, []);

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

  return(
    <div className="wrapper">
      <Header
       isLogin={isLogin} 
       changeIsLogin={changeIsLogin}
       isAdmin={isAdmin}
       changeIsAdmin={changeIsAdmin}/>
      <Banner/>
      <Main 
        isLogin={isLogin}
        isAdmin={isAdmin}/>
      <Footer/>
    </div>
  )
}
export default MainPage