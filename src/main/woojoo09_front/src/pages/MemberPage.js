import Footer from "../components/Footer";
import Header from "../components/Header";
import MemberInfo from "../components/MemberInfo"
import "../style/member.scss"
import { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import api from "../api/api";

const MemberPage = () =>{

  const navigate = useNavigate();

  const [isLogin, setIsLogin] = useState("");
  const [isAdmin, setIsAdmin] = useState(false);
  const [memberNum, setMemberNum] = useState();
  const [memberInfo, setMemberInfo] = useState('');
  const [isChange, setIsChange] = useState(false);

  const changeIsChange = (value) => {
    setIsChange(value);
  }

  const changeIsLogin = (value) => {
    setIsLogin(value);
  };

  const changeIsAdmin = (value) => {
    setIsAdmin(value);
  };

  useEffect(()=>{
    window.scrollTo({ top: 0, behavior: "auto" });
  }, [])

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.tokencheck();
        if(response.data.result === "OK"){
          setIsLogin(true);
          setMemberNum(response.data.memberNum);
          try {
            const res = await api.memberInfoNewNick(response.data.memberNum);
            setMemberInfo(res.data);
            // console.log(res.data)
          } catch (e) {
            console.log(e);
          }
        } else{
          setIsLogin(false);
        }
      } catch (e) {
        console.log(e);
      }
    };
    fetchData();
    }, [isChange]);


  if (!isLogin){
    navigate("/login", {state : "유효하지 않은 접근입니다\n로그인 후 이용해 주세요"});
  } 


  return(
    <div className="memberinfowrapper">
      <Header
      isLogin={isLogin} 
      changeIsLogin={changeIsLogin}
      isAdmin={isAdmin}
      changeIsAdmin={changeIsAdmin}/>
      <MemberInfo
      memberNum = {memberNum}
      memberInfo = {memberInfo} 
      isChange={isChange}
      changeIsChange = {changeIsChange}/>
      <Footer/>
    </div>
  );
  // }
}
export default MemberPage