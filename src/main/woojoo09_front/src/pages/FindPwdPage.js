import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/api";
import Footer from "../components/Footer";
import Header from "../components/Header";
import ResetPwd from "../components/ResetPwd";

const FindPwdPage = () =>{
  const navigate = useNavigate();

  //재설정 페이지 유효성
  const [changeResetPwd, setChangeResetPwd] = useState(false);

  const [findPwdId, setFindPwdId] = useState('');
  const [findPwdEmail, setFindPwdEmail] = useState('');
  const [findPwdCodeInput, setFindPwdCodeInput] = useState('');
  const [findPwdServerCode, setFindPwdServerCode] = useState('');

  const [isFindPwdId, setIsFindPwdId] = useState(false);
  const [isFindPwdEmail, setIsFindPwdEmail] = useState(false); 
  //확인 버튼 유효성
  const [isFindPwdVerifyCode, setIsFindPwdVerifyCode] = useState(false);
  //확인 에러 메시지 유효성
  const [isFindPwdBtnErr, setIsFindPwdBtnErr] = useState(false);

  //에러 메시지
  const [findPwdIdOkMsg, setFindPwdIdOkMsg] = useState('');
  const [findPwdIdMsg, setFindPwdIdMsg] = useState('');

  const [findPwdEmailOkMsg, setFindPwdEmailOkMsg] = useState('');
  const [findPwdEmailMsg, setFindPwdEmailMsg] = useState('');

  const [findPwdBtnErrMsg, setFindPwdBtnErrMsg] = useState('');
  const [findPwdBtnOkMsg, setFindPwdBtnOkMsg] = useState('');

  //정규식
  const idRegEx = /^[A-za-z0-9]{3,15}$/g; 
  const emailRegEx = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
  const CodeRegEx = /^[0-9]+$/;

  const onChangeFindPwdId = (e) => {
    const inputFindPwdId = e.target.value;
    setFindPwdId(inputFindPwdId);
    if(inputFindPwdId.length === 0) {
      setIsFindPwdId(false);
      setFindPwdIdMsg("가입 시 등록한 아이디를 입력해주세요.");
    } else if(!idRegEx.test(inputFindPwdId)) {
      setIsFindPwdId(false);
      setFindPwdIdMsg("아이디 형식에 맞지 않습니다.");
    } else {
      setIsFindPwdId(true);
      setFindPwdIdOkMsg("");
    };
  }

  const onChangeFindPwdEmail = (e) => {
    const inputFindPwdEmail = e.target.value;
    setFindPwdEmail(inputFindPwdEmail);
    if(inputFindPwdEmail.length === 0) {
      setIsFindPwdEmail(false);
      setFindPwdEmailMsg("가입 시 등록한 이메일주소를 입력해주세요.")
    } else if(!emailRegEx.test(inputFindPwdEmail)) {
      setIsFindPwdEmail(false);
      setFindPwdEmailMsg("이메일 형식에 맞지 않습니다.");
    } else {
      setIsFindPwdEmail(true);
      setFindPwdEmailOkMsg("");
    };
  }

  //인증번호 받기 버튼
  const onClickFindPwdCode = () => {
    const findPwdCodeInput = document.getElementById('findPwdCodeInput');
    const idEmailFetchData = async () => {
      try {
        const response = await api.idEmailCk(findPwdId, findPwdEmail);
        if(response.data === true) {                  
          const emailfetchData = async () => {
            try {
              const emailRes = await api.verifyCodeEmailSend(findPwdEmail);
              // console.log(emailRes.data);
              setFindPwdServerCode(emailRes.data);              
              if(emailRes.data){
                findPwdCodeInput.style.display = 'block';
                setIsFindPwdEmail(true);
                setFindPwdEmailOkMsg("가입하신 이메일로 인증번호를 보내드렸습니다.")                               
              } else {
                setIsFindPwdEmail(false);
                setFindPwdEmailMsg("인증번호 전송에 실패했습니다.")
              }                
            } catch (e) {
              console.log(e)
            }
          }
          emailfetchData();
        } else if(response.data === false) {
          setIsFindPwdEmail(false);
          setFindPwdEmailMsg("가입하신 이름과 이메일을 찾을 수 없습니다.")
        }
      } catch (e) {
          console.log(e);        
      }
    }
    idEmailFetchData();
  }

  
  const onChangeFindPwdCodeInput = (e) => {
    const findPwdCodeInput = e.target.value;
    setFindPwdCodeInput(findPwdCodeInput);
    if(CodeRegEx.test(findPwdCodeInput)) {
      setIsFindPwdVerifyCode(true);
    } else {
      setIsFindPwdVerifyCode(false);
    }
  }

  //확인 버튼
  const onClickFindPwdOkBtn = () => {   
    // if(isFindPwdVerifyCode) navigate('/resetpwd');
    if(findPwdCodeInput == findPwdServerCode) {
      setChangeResetPwd(true);
    } else {
      setIsFindPwdVerifyCode(false);
      setIsFindPwdBtnErr(false);
      setFindPwdBtnErrMsg("인증번호가 일치하지 않습니다.");
    }
  }

  
  return(
    <div className="wrapper">
      <Header/>
      <div className="findPwdWrapper">
        <div className="findPwd">
          {changeResetPwd && 
          <ResetPwd 
          findPwdId={findPwdId}/>}

          {!changeResetPwd &&
          <>
          <h2>비밀번호 찾기</h2>
          <div className="findPwdMain">
            <div className="findPwdSmallBox">           
              <input type="text" value={findPwdId} className="findPwdId" placeholder="아이디"
              onChange={onChangeFindPwdId}></input> 
            </div>
            <div className="findPwdErrMsg">
              {!isFindPwdId && <span className="findPwdIdErr">{findPwdIdMsg}</span>}
              {isFindPwdId && <span className="findPwdIdOk">{findPwdIdOkMsg}</span>}
            </div>
            <div className="findPwdSmallBox">
              <input type="text" value={findPwdEmail} className="findPwdEmail" placeholder="이메일주소"
              onChange={onChangeFindPwdEmail}></input>
            </div>
            <div className="findPwdErrMsg">
              {!isFindPwdEmail && <span className="findPwdEmailErr">{findPwdEmailMsg}</span>}
              {isFindPwdEmail && <span className="findPwdEmailOk">{findPwdEmailOkMsg}</span>}
            </div>
            <div className="findPwdCode">
              <button id="findPwdCodeBtn" className={(isFindPwdId && isFindPwdEmail) ? 'findPwdCodeBtn' : 'notFindPwdCodeBtn'}
              onClick={onClickFindPwdCode}>인증번호 받기</button>
            </div>
            <div className="findPwdSmallBox">
              <input type="text" value={findPwdCodeInput} id="findPwdCodeInput" className="findPwdCodeInput" placeholder="인증번호 입력"
              style={{display: "none"}} onChange={onChangeFindPwdCodeInput}></input>
            </div>

            <div className="findPwdErrMsg">
              {!isFindPwdBtnErr && <span className="findPwdBtnErr">{findPwdBtnErrMsg}</span>}
              {isFindPwdBtnErr && <span className="findPwdBtnOk">{findPwdBtnOkMsg}</span>}             
            </div>

          </div>
          <div>
            <button id="pwdSearchButton" className={isFindPwdVerifyCode ? 'pwdSearchOkBtn' : 'pwdSearchNotBtn'}
            onClick={onClickFindPwdOkBtn}>확인</button>             
          </div>
          {/* isFindPwdVerifyCode ? ()=>{setChangeResetPwd(true)} : ()=>{setChangeResetPwd(false)}} */}
          </>
          }
        </div>
      </div>
      <Footer/>
    </div>
  );
}
export default FindPwdPage