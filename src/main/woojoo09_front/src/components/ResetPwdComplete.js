import "../style/resetpwd.scss";
import { useNavigate  } from "react-router-dom";


const ResetPwdComplete = () => {
  const navigate = useNavigate();

  const onClickResetPwdLogin = () => {
    navigate('/login');
  }



    return (
        <>
        <h2>비밀번호 재설정</h2>
        <div className="resetPwdMain">
          <div className="resetPwdCompleteDt">
            <span>비밀번호가 변경되었습니다.</span>
          </div>
        </div>          
        <div className="resetPwdLoginBtn">
          <button onClick={onClickResetPwdLogin}>로그인하기</button>     
        </div>
        </>
    )

}
export default ResetPwdComplete;