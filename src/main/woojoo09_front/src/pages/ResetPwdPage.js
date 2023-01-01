import { useState } from "react";
import Footer from "../components/Footer";
import Header from "../components/Header";
import "../style/resetpwd.scss";

const ResetPwdPage = () =>{
  const [resetPwd, setResetPwd] = useState('');
  const [resetPwdCk, setResetPwdCk] = useState('');

  //유효성 검사
  const [isResetPwd, setIsResetPwd] = useState(false);
  const [isResetPwdCk, setIsResetPwdCk] = useState(false);

  //에러 메시지
  const [resetPwdOkMsg, setResetPwdOkMsg] = useState('');
  const [resetPwdMsg, setResetPwdMsg] = useState('');

  const [resetPwdCkOkMsg, setResetPwdCkOkMsg] = useState('');
  const [resetPwdCkMsg, setResetPwdCkMsg] = useState('');

  //정규식
  const resetPwdEx = /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*+=-]).{8,20}$/;

  //비밀번호
  const onChangeResetPwd = (e) => {
    const inputResetPwd = e.target.value;
    setResetPwd(inputResetPwd);
    if(resetPwdEx.test(inputResetPwd)) {
      setIsResetPwd(true);
      setResetPwdOkMsg("사용 가능한 비밀번호 입니다.");
    } else if (inputResetPwd.length === 0) {
      setIsResetPwd(false);
      setResetPwdMsg("");
    } else {
      setIsResetPwd(false);
      setResetPwdMsg("8~20자리 소문자, 숫자, 특수문자를 입력해주세요.");
    }
  };

  //새 비밀번호 
  const onChangeResetPwdCk = (e) => {
    const inputResetPwdCk = e.target.value;
    setResetPwdCk(inputResetPwdCk); 
    if(resetPwd.length === 0){
      setIsResetPwdCk(false);
      setResetPwdCkMsg("새 비밀번호 항목을 먼저 확인해주세요.");
    } else if(inputResetPwdCk.length === 0){
      setIsResetPwdCk(false);
      setResetPwdCkMsg("비밀번호가 일치하지 않습니다.");
    } else if(resetPwdEx.test(inputResetPwdCk) && inputResetPwdCk === resetPwd){
      setIsResetPwdCk(true);
      setResetPwdCkOkMsg("비밀번호가 일치합니다.");
    } else{
      setIsResetPwdCk(false);
      setResetPwdCkMsg("비밀번호가 일치하지 않습니다.");
    }
  }

  const onClickResetPwd = () => {
    
  }

  return(
    <div className="wrapper">
      <Header/>
      <div className="resetPwdWrapper">
        <div className="resetPwd">
          <h2>비밀번호 재설정</h2>
          <div className="resetPwdMain">
            <div className="resetPwdSmallBox">           
              <input type="password" value={resetPwd} className="resetPwdInput" placeholder="새 비밀번호"
              onChange={onChangeResetPwd}></input> 
            </div>
            <div className="resetPwdErrMsg">
              {!isResetPwd && <span className="resetPwdErr">{resetPwdMsg}</span>}
              {isResetPwd && <span className="resetPwdOk">{resetPwdOkMsg}</span>}
            </div>
            <div className="resetPwdSmallBox">
              <input type="password" value={resetPwdCk} className="resetPwdInput" placeholder="새 비밀번호 확인"
              onChange={onChangeResetPwdCk}></input>
            </div> 
            <div className="resetPwdErrMsg">
              {!isResetPwdCk && <span className="resetPwdCkErr">{resetPwdCkMsg}</span>}
              {isResetPwdCk && <span className="resetPwdCkOk">{resetPwdCkOkMsg}</span>}
            </div>
            </div>          
            <div className="resetPwdComplete">
              <button className={(isResetPwd && isResetPwdCk) ? 'resetPwdCompleteBut' : 'resetPwdNotCompleteBut'}
              onClick={onClickResetPwd}>확인</button>     
            </div>
        </div>
      </div>
      <Footer/>
    </div>
  );
}
export default ResetPwdPage