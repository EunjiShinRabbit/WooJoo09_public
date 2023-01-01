import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../style/member.scss"
import "../style/common.scss"
import api from "../api/api";
import defaultProfileImg from "../resources/profile_sample.png";
import grade1 from "../resources/grade_icon1_wreck.png"
import grade2 from "../resources/grade_icon2_comet.png"
import grade3 from "../resources/grade_icon3_planet.png"
import grade4 from "../resources/grade_icon4_nebula.png"
import grade5 from "../resources/grade_icon5_galaxy.png"
import grade6 from "../resources/grade_icon6_space.png"



const MemberInfoList =(props) => {
  const navigate = useNavigate();
  const [logoutErr, setLogoutErr] = useState();

  const logout = () =>{
    const fetchData = async () => {
      // setLoading(true); 로딩있으면 깜빡거리는거같아서 뺏음, 데이터 뭐 많이 가져오는것도없고
      try {
        const response = await api.logout();
        // console.log(response.data);
        if(response.data.logout === "OK") {
          navigate("/")
        } else {
          setLogoutErr("로그아웃에 실패했습니다");
        }
      } catch (e) {
        console.log(e);
      }
      // setLoading(false);
    };
    fetchData();
  }

  var options = {
    year: 'numeric',
    month: 'numeric',
    day: 'numeric',
    // hour: 'numeric',
    // hour: '2-digit',
    // minute : 'numeric',
    timeZone : 'Asia/Seoul'
  }

  
  return(
   
    <div className="memberinfoheader">
      <div className="profilecard">
        {/* {memberInfo && memberInfo.map(member => ( */}
          <>
          <div className="profileimg">
          {props.memberInfo.pfImg ? <img src={props.memberInfo.pfImg} alt="프로필 이미지"/> :
          <img src={defaultProfileImg} alt="기본 프로필 이미지(공부하는 커비)"/>}
          </div>  
          <div className="profileinfo">
          </div>
            
                {/* <p>회원등급 : <img className="memberrankimg" src={
                member.grade === "새싹"? rankIcon1 : (member.grade === "잎새"? rankIcon2 : 
                (member.grade === "가지열매나무"? rankIcon3 : (member.grade === "열매"? rankIcon4:rankIcon5)))
                } alt={member.grade}/>{member.grade}
                </p> */}
                <div className="profileinfo">
                <p>닉네임 : {props.memberInfo.nickname}</p>
                <p>가입일 : {new Date(props.memberInfo.regDate).toLocaleDateString("ko-KR", options)}</p>
                <p>전화번호 : {props.memberInfo.phone}</p>
                <p>이메일 : {props.memberInfo.email}</p>
                <p>회원등급 : <img style={{width : "18px"}}
                src={ props.memberInfo.grade === "잔해" ? grade1 : (props.memberInfo.grade === "혜성" ? grade2 : (
              props.memberInfo.grade === "행성" ? grade3 : (props.memberInfo.grade === "성운" ? grade4 : (
                props.memberInfo.grade === "은하" ? grade5 : grade6))))} 
          alt="등급아이콘"/>{props.memberInfo.grade}
                </p>
          </div>
          </>
        <button onClick={logout} className="logout">로그아웃</button>
      </div>
    </div>

  );
}
export default MemberInfoList;