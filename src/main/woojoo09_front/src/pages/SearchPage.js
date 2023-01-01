import { useParams, useNavigate, useLocation} from 'react-router-dom';
import { useState, useEffect } from "react";
import Header from "../components/Header";
import Main from "../components/Main";
import Footer from "../components/Footer"

const SearchPage = () =>{
  let { target } = useParams();
  const location = useLocation();
  const [isLogin, setIsLogin] = useState(location.state.isLogin);
  const [isAdmin, setIsAdmin] = useState(false);
  const changeIsLogin = (value) => {
    setIsLogin(value);
  };
  const changeIsAdmin = (value) => {
    setIsAdmin(value);
  };

  return(
    <div className="wrapper">
      <Header
      isLogin={isLogin} 
      changeIsLogin={changeIsLogin}
      isAdmin={isAdmin}
      changeIsAdmin={changeIsAdmin}
      />
      <Main target = {target}/>
      <Footer/>
    </div>
  )
}
export default SearchPage