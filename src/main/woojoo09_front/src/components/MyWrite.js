import { Link } from "react-router-dom";

const MyWrite = () =>{
  return(
    <div className="mywrite">
      <p>작성글 조회</p>
      <p><Link to="/mywrite">내가 작성한 글</Link></p>
      <p><Link to="/mycomment">내가 댓글 작성한 글</Link></p>
    </div>
  );
};
export default MyWrite;