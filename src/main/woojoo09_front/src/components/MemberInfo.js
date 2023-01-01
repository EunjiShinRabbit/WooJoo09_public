import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../style/member.scss"
import "../style/common.scss"
import ChangeMemberInfo from "./ChangeMemberInfo";
import MyTrade from "./MyTrade";
import api from "../api/api";
import MemberInfoList from "./MemeberInfoList";


const MemberInfo = ({memberNum, memberInfo, isChange, changeIsChange}) =>{

  const navigate = useNavigate();

  const [memberDeleteMsg, setMemberDeleteMsg] = useState('');
  const [inputId, setInputId] = useState('');
  const [inputPwd, setInputPwd] = useState('');
  const [isId, setIsId] = useState(false);
  const [isPwd, setIsPwd] = useState(false);
  const [idMsg, setIdMsg] = useState('');
  const [pwdMsg, setPwdMsg] = useState('');
  const [viewMemberDelete, setViewMemberDelete] = useState(false);
  const [realDelete, setRealDelete] = useState(false);

  const memberDelete = () =>{
    const fetchData = async () => {
      try {
        const response = await api.memberDelete(inputId, inputPwd);
        // console.log(response.data);
        if(response.data.memberDelete === "loginError") {
          setMemberDeleteMsg("로그인 상태를 확인해주세요");
        } else if(response.data.memberDelete === "notData"){
          setMemberDeleteMsg("일치하는 회원 정보가 없습니다");
        } else if(response.data.memberDelete === "NOK"){
          setMemberDeleteMsg("비밀번호를 확인해주세요");
        }
        else {
          setMemberDeleteMsg("탈퇴 처리 되었습니다 안녕히가세요");
          setTimeout(()=>{ 
            navigate('/main');
          }, 2500);
        }
      } catch (e) {
        console.log(e);
      }
    };
    fetchData();
  }

  const onChangeId = (e) => {
    const idRegEx = /^[A-za-z0-9]{3,15}$/g;
    const id = e.target.value;
    setInputId(id);
    if(!idRegEx.test(id) && !(id.length === 0)) {
      setIsId(false);
      setIdMsg("아이디 형식을 확인해주세요\n3~15자리 영문자 또는 숫자");
    } else{
      setIsId(true);
      setIdMsg("");
    }
  }


  const onChangePwd = (e) => {
    const passwordRegex = /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*+=-]).{8,20}$/
    const pwd = e.target.value ;
    setInputPwd(pwd);
    if (!passwordRegex.test(pwd)) {
      setPwdMsg('비밀번호 형식을 확인해주세요\n 영소문자+숫자+특수문자 8자리 이상 20자리 이하')
      setIsPwd(false)
    } else {
      setPwdMsg('')
      setIsPwd(true);
    }        
  }

  return(
    <div className="memberinfomain">
      <MemberInfoList memberInfo={memberInfo}/>
      <div className="memberinfocenter">
        <ChangeMemberInfo 
        isChange={isChange}
        changeIsChange={changeIsChange}
        memberNum={memberNum} 
        memberInfo={memberInfo}/>
        <MyTrade memberInfo={memberInfo}/>
        <div className="mobileFooter">
          <Link to ="/term">이용약관</Link>
          <span onClick={() => window.open('https://www.notion.so/8872912a168b42d48b065b8e2d05a017', '_blank')}>개인정보처리방침</span>
          <span onClick={() => window.open('https://sneaky-mascara-833.notion.site/bee0329b1932492a8faace3df9ab7a72', '_blank')}>서비스소개</span>
          <span onClick={() => window.open('https://open.kakao.com/o/gdOB3SUe', '_blank')}>카카오톡 문의하기</span>
        </div>
        <div className="unReg">
          <p onClick={()=>setViewMemberDelete(!viewMemberDelete)}>회원 탈퇴</p>
          {viewMemberDelete && <div className={`memberDelete ${realDelete? "cry" : ""}`}>
            <p>우주공구를 탈퇴하시면</p><p>공동구매와 거래 정보가 모두 사라지며</p>
            <p>재가입이 제한됩니다</p><p>(동일 아이디, 핸드폰 번호로 가입 불가)</p>
            <p>정말 탈퇴를 원하시면</p><p>회원 정보를 정확히 입력해주세요</p>
            <input type="text" placeholder="아이디" value={inputId} onChange={onChangeId}/>
            <p className="memberDeleteErr">{idMsg}</p>
            <input type="password" placeholder="비밀번호" value={inputPwd} onChange={onChangePwd}/>
            <p className="memberDeleteErr">{pwdMsg}</p>
            {isId && isPwd && !realDelete && <button onClick={()=>setRealDelete(true)}>탈퇴하기</button>}
            {isId && isPwd && realDelete && <button onClick={memberDelete}>정말로 탈퇴하기</button>}
            <p className="memberDeleteErr">{memberDeleteMsg}</p>
          </div>}
        </div>
      </div>
    </div>
  )
}

export default MemberInfo;