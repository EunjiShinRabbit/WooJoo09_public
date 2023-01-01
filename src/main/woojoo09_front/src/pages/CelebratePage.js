import Footer from '../components/Footer';
import Header from "../components/Header";
import Celebrate from "../components/Celebrate";
import { useNavigate  } from "react-router-dom";
import { useEffect } from 'react';

const CelebratePage = () =>{
  const navigate = useNavigate();

  const onClickCelLoginBtn = () => {
    navigate('/login');
  }

  const onClickCelMainBtn = () => {
    navigate('/');
  }

  useEffect(()=>{
    window.scrollTo({ top: 0, behavior: "auto" });
  }, [])

  return(
    <div className="wrapper">
      <Header/>
      <div className="celWrapper">     
        <div className="celebrate">
          <div className="celImg" />
            <div className="celMainWrite">         
            우주공구의 회원이 되신 것을 축하드립니다!
            </div>
            <div className="celDetailWrite">
              <h3>
              모든 회원가입절차가 완료되었습니다.<br/>
              등록하신 이메일로 우주공구의 소개 메일을 보내드렸으니,<br/>
              받지 못하셨다면 회원정보를 확인해주세요.<br/>
              로그인 후 우주공구의 모든 서비스를 이용할 수 있습니다.
              </h3>
            </div>
            <div className="celLowBox">
              <div className="celLoginBox">
                <button onClick={onClickCelLoginBtn}>로그인</button>
              </div>
              <div className="celMainBox">
                <button onClick={onClickCelMainBtn}>메인으로</button>
              </div>
            </div>
          </div>
        </div>
      <Footer/>
    </div>
  );
}
export default CelebratePage