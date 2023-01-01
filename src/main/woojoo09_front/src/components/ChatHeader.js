const ChatHeader = () =>{
    return(
        <div className="chatNickname">
        <span>닉네임</span> 
        <div className="chatStateWait">대기</div>
        {/* <div className="chatStatejoin">참여</div>
        <div className="chatStatedone">완료</div> */}
      </div>
    );
}
export default ChatHeader