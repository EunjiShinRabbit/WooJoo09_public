import { useParams } from 'react-router-dom';
import { useState, useEffect } from "react";
import Footer from '../components/Footer';
import Header from '../components/Header';
import Detail from '../components/Detail';
import api from '../api/api';

const DetailPage = () =>{
  let { tradeNum } = useParams();

  const [isLogin, setIsLogin] = useState("");
  const [isAdmin, setIsAdmin] = useState(false);
  const changeIsLogin = (value) => {
    setIsLogin(value);
  };
  const changeIsAdmin = (value) => {
    setIsAdmin(value);
  };

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
    <div className="detailWrapper">
      <Header
      isLogin={isLogin} 
      changeIsLogin={changeIsLogin}
      isAdmin={isAdmin}
      changeIsAdmin={changeIsAdmin}/>
      <Detail
       isLogin={isLogin}
       isAdmin={isAdmin}
       tradeNum={tradeNum}/>
      <Footer/>
    </div>
  );
}
export default DetailPage