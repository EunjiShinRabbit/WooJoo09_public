import "../style/findidcomplete.scss";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import api from "../api/api";


const FindIdComplete = ({findIdEmail}) => {
    const navigate = useNavigate();

    const onClickFindIdComLogin = () => {
        navigate('/login');
    }

    // console.log(findIdEmail);
    // const findIdEmail = localStorage.getItem(props.findIdEmail);

    const [findIdMember, setFindIdMember] = useState('');


    useEffect(() => {
      const findIdData = async () => {
        try {
          const response = await api.memberinfoFindId(findIdEmail);
          setFindIdMember(response.data);
          // console.log(response.data)
        } catch (e) {
          console.log(e)
        }
      };
      findIdData();
    }, []);

    return (      
        <>       
        <div className="findIdCompleteMain">
          회원님의 아이디 찾기가 완료되었습니다.
        </div>
        {findIdMember && findIdMember.map(member => (
        <div className="findIdCompleteDt" key={member.findIdEmail}>
          회원님의 아이디는<br/>
          <br/>
          {member.id} 입니다.  
        </div>
        ))}
        <div className="findIdCompleteLogin">
          <button onClick={onClickFindIdComLogin}>로그인하기</button>
        </div>
        </>
    )
}

export default FindIdComplete