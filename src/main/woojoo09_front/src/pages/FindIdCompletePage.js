import Footer from "../components/Footer";
import Header from "../components/Header";
import "../style/findidcomplete.scss";

const FindIdCompletePage = () =>{
  
  return(
    <div className="wrapper">
      <Header/>
      <div className="findIdWrapper">
        <div className="findId">
          <h2>아이디 찾기</h2>
          <div className="findIdCompleteMain">
            회원님의 아이디 찾기가 완료되었습니다.
          </div>
          <div className="findIdCompleteDt">
            회원님의 아이디는<br/>
            ooooo입니다.  
          </div>
          <div className="findIdCompleteLogin">
            <button>로그인하기</button>
          </div>
        </div>
      </div>
      <Footer/>
    </div>
);
}
export default FindIdCompletePage