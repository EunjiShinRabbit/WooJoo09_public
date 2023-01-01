
import ChatList from "../components/ChatList";
import "../style/chat.scss"
import "../style/common.scss"
import logo from "../resources/logoWhite.png"



const ChatListPage = () =>{



  return(
    <div>
      <div className="chatLeft"><ChatList/></div>
      <div className="chatting">
        <div className="chatListRight">
          <div><img src={logo} alt="로고"/></div>
          <div className="chatStart">
          채팅목록에서 대화를 시작해보세요!
          </div>
        </div>
      </div>
    </div>


  );
}
export default ChatListPage