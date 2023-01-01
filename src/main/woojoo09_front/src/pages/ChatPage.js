import { useEffect, useState, useRef, useCallback } from "react";
import ChatList from "../components/ChatList";
import ChatHeader from "../components/ChatHeader";
import ChattingProduct from "../components/Chattingproduct";
import ChattingProductBuy from "../components/ChattingPoductBuy";
import ChatBuyButton from "../components/ChatBuyButton";
import ChatSellButton from "../components/ChatSellButton";
import api from "../api/api";
import { useLocation, useParams, useNavigate } from 'react-router-dom';
import send1 from "../resources/buluepurple_rocket.png"
import { Link } from "react-router-dom";
import { defaultImgs } from "../util/util";
import "../style/chat.scss"


const ChatPage = () =>{

  const navigate = useNavigate();
  let { partner_num } = useParams();
  // {nickname, img_url, chat_time, chat_content, is_read, partner_num}
   const location = useLocation();
   const memberNum = location.state.memberNum;
   const nickname = location.state.list.nickname;
   const acceptTradeData = location.state.list.accept_trade;
   const [acceptTrade, setAcceptTrade] = useState(acceptTradeData);
   const doneTradeData = location.state.list.done_trade;
   const [doneTrade, setDoneTrade] = useState(doneTradeData);
   const product = location.state.list.product;
   const price = location.state.list.price;
   const imgUrl = location.state.list.img_url;
   const host = location.state.list.host;
   const target = location.state.list.trade_num;
   const partner = location.state.list.part_mem_num;
   const tradeNum = location.state.list.trade_num;
   const category_num = location.state.list.category_num;
  

  const [socketConnected, setSocketConnected] = useState(false);
  const [inputMsg, setInputMsg] = useState("");
  const [rcvMsg, setRcvMsg] = useState("");
  // const webSocketUrl = `ws://localhost:9009/ws/chat`;

  const webSocketUrl = `ws://13.209.198.107/ws/chat`;
  // // roomId랑 sender은 받아와야함 -> navigate로 받아오면 될듯
  // const roomId = window.localStorage.getItem("chatRoomId");
  //  const sender = "곰돌이사육사";
   let ws = useRef(null);
   const [items, setItems] = useState([]);

  let roomId = partner_num;
  
  const [visible, setVisible] = useState(false);
  const [type, setType] = useState('');
  const [lists, setLists] = useState('');
  const [loading, setLoading] = useState(false);
  const [prepared, setPrepared] = useState(false);
  const [sender, setSender] = useState();
  const [chatContent, setChatContent] = useState();

  const [chatSendImg, setChatSendImg] = useState();

  const changeChatSendImg = (e)=>{
    setChatSendImg(e);
  }

  var options = {
    // year: 'numeric',
    month: 'numeric',
    day: 'numeric',
    hour: 'numeric',
    // hour: '2-digit',
    minute : 'numeric',
    timeZone : 'Asia/Seoul'
  }

  useEffect(() => {
    const fetchData = async () => {
     setLoading(true);
      try {
        const response = await api.chatContent(partner_num, memberNum);
        setLists(response.data[0]);
        console.log(response.data[0]);
        //console.log("데이터" + response.data[0].chattingContent[0].chat_content);
        setSender(response.data[0].chattingContent[0].sender);
        // console.log(response.data[0].chattingContent[0].sender);
        // console.log(response.data[0].chattingContent[0].chat_content);
        setChatContent(response.data[0].chattingContent[0].chat_content);
        // console.log(response.data[0])
        setPrepared(true);
        setAcceptTrade(acceptTradeData);
        setDoneTrade(doneTradeData);
      } catch (e) {
        console.log(e);

      }
      setLoading(false);
    };
    fetchData();
  }, [partner_num]);


  const visibleAccount = (e) => {
    setVisible(!visible);
    // setType(e.target.value)
  }

  // const EntranceBuy  = visible?
    //  <ChattingProductBuy /> :  <ChattingProduct />;


  const onChangMsg = (e) => {
      setInputMsg(e.target.value)
  }

  const onEnterKey = (e) => {
      if(e.key === 'Enter') {
        onClickMsgSend(e);
      }
  }

  const onClickMsgSend = (e) => {
    const fetchData = async () => {
      // console.log(new Date());
      try {
        // console.log(items);
          const res = await api.chatContentInsert(partner_num, inputMsg, "text", memberNum);
          // console.log("메시지 db에 보내짐" + res.data);
          // window.localStorage.setItem("chatRoomId", res.data);
          // setRoomId(res.data);
          //  window.location.replace("/chat");
        } catch {
            console.log("error");
        }
      };  
    e.preventDefault();
    ws.current.send(
        JSON.stringify({
        "type":"TALK",
        "roomId": roomId,
        "sender": memberNum,
        "message":inputMsg,
        "time" : new Date()
    }));
    setInputMsg("");
    fetchData();
  }

  const msgInsert = () =>{
    const fetchData = async () => {
      try {
        // console.log(items);
          const res = await api.chatContentInsert(partner_num, inputMsg, "text", memberNum);
          // console.log(res.data);
          // window.localStorage.setItem("chatRoomId", res.data);
          // setRoomId(res.data);
          //  window.location.replace("/chat");
      } catch {
          console.log("error");
      }
    };
    fetchData();
  } 

  const onClickMsgClose = () => {
      ws.current.send(
          JSON.stringify({
          "type":"CLOSE",
          "roomId": roomId,
          "sender":memberNum,
          "message": "종료 합니다.",
          "time" : new Date()
        }));
      ws.current.close();
  }

  useEffect(() => {
  //   // 화면이 렌더링 될 때 불러지는것, 자동으로 세션 연결
  //   // 화면이 로딩되자마자 웹소켓을 열어달라고 요청
      setItems([]);
      // console.log("방번호 : " + roomId);
      if (!ws.current) {
          ws.current = new WebSocket(webSocketUrl);
          ws.current.onopen = () => {
              // console.log("connected to " + webSocketUrl);
              setSocketConnected(true);
          };
      }
      if (socketConnected) {
        // console.log("socketConnected", socketConnected)
        // 연결 되면 바로 방으로 진입
          ws.current.send(
              JSON.stringify({
              "type":"ENTER",
              "roomId": roomId,
              "sender": memberNum,
              "message": "처음으로 접속 합니다.",
              "time" : new Date()
            }));
      }
      ws.current.onmessage = (evt) => {
          const data = JSON.parse(evt.data);
          // console.log(data.message);
          setRcvMsg(data.message);
          setItems((prevItems) => [...prevItems, data]);
    };
  }, [roomId, socketConnected]);

  const partnerAccept = () => {
    const fetchData = async () => {
      try {
        console.log(items);
          const res = await api.chatPartnerAccept(target, partner);
          // console.log(res.data);
          setAcceptTrade('ACCEPT')
      } catch {
          console.log("error");
      }
    };
    fetchData();
 } 

  const partnerRejecthost = () => {
    const fetchData = async () => {
      try {
        // console.log(items);
          const res = await api.chatPartnerRejecthost(target, partner);
          // console.log(res.data);
          setAcceptTrade('DELETE')
      } catch {
          console.log("error");
      }
    };
    fetchData();
} 

const partnerReject = () => {
  const fetchData = async () => {
    try {
      // console.log(items);
        const res = await api.chatPartnerReject(target);
        // console.log(res.data);
        setAcceptTrade('DELETE')
    } catch {
        console.log("error");
    }
  };
  fetchData();
} 

// 스크롤 고정
// useEffect(()=>{
//   window.scrollTo({ bottom: 0, behavior: "auto" });
// }, [])

  // 스크롤 하단 고정!!!
  const scrollRef = useRef();
  useEffect(() => {
    scrollRef.current.scrollTop = scrollRef.current.scrollHeight;
  });

  const onClickImgMsgSend = () => {
    const fetchData = async () => {
      try {
        // console.log(items);
          const res = await api.chatContentInsert(partner_num, chatSendImg, "img", memberNum);
          // console.log("메시지 db에 보내짐" + res.data);
        } catch {
          console.log("error");
        }
      };  
    ws.current.send(
        JSON.stringify({
        "type":"IMG",
        "roomId": roomId,
        "sender": memberNum,
        "message":chatSendImg,
        "time" : new Date()
    }));
    setInputMsg("");
    fetchData();
  }

  if(acceptTrade == 'DELETE' || doneTrade == 'DELETE'){
    navigate('/main')
  }
  

  return(
    <div className="wrapper">
      <div className="chatPageLeft"><ChatList/></div>

      <div className="chatRight">
        <div className="chatNickname">
          <span>{nickname}</span> 
          {doneTrade == 'ONGOING' && acceptTrade == 'REJECT' && <div className="chatStateWait">대기</div>} 
          {doneTrade == 'ONGOING' && acceptTrade == 'ACCEPT' && <div className="chatStatejoin">참여</div>} 
          {doneTrade == 'DONE' && <div className="chatStatedone">완료</div>}
        </div>

          <div className="chattingProduct">
            <div>
                <Link to ={`/detail/${tradeNum}`}>
                  <img src = {imgUrl? imgUrl : category_num === 1 ? defaultImgs.패션.imgUrl :
                      category_num === 2 ? defaultImgs.뷰티.imgUrl : category_num === 3 ? defaultImgs.생활.imgUrl :
                      category_num === 4 ? defaultImgs.식품.imgUrl : category_num === 5 ? defaultImgs.취미.imgUrl :
                      defaultImgs.반려동물.imgUrl} alt="물품이미지"/>
                </Link>
                  <div className="chatProInfo">
                  <p className="chatProductName">{product}</p>
                  <div className="chatPrice">{price}원</div>
                    

                  {host == memberNum ? acceptTrade == 'REJECT'? 
                  <div className="PartAcceptBtn">
                    <button onClick={partnerAccept}>공구승인</button>
                    <button onClick={partnerRejecthost}>공구거절</button>
                    <p>공구를 거절하면 채팅 내용과 입력한 정보가 모두 사라집니다</p>
                  </div>
                    : <></>
                    :<div className="PartAcceptBtn2"> <button onClick={partnerReject}>공구나가기</button>
                      <p>공구를 나가면 채팅 내용과 입력한 정보가 모두 사라집니다</p></div>
                    }
                  </div>
            </div>
          </div>
          <div className="chatContent" ref={scrollRef} >
            {prepared &&
            lists.chattingContent
            .map(({chat_content, chat_time, sender, msg_type}) => (
            <div className="chatTalkWrap">
                {memberNum != sender && <div className="chatMessage">
                  {msg_type === 'IMG'? <img className="chatImg" src={chat_content} alt="상대방이 보낸 이미지"/> 
                : chat_content}</div>}
                {memberNum != sender && <div className="chatTalkTime">{new Date(chat_time).toLocaleDateString("ko-KR", options)}</div>}
                {memberNum == sender && <div className="chatMessage-My">
                  {msg_type === 'IMG'? <img className="chatImg" src={chat_content} alt="내가 보낸 이미지"/> : chat_content}</div>}
                {memberNum == sender && <div className="chatTalkTime-My">{new Date(chat_time).toLocaleDateString("ko-KR", options)}</div>}  
                
            </div>
            ))} 
                <div className="chatTalkWrap">
                  {/* .filter((item) => item.type !== "ENTER") */}
                  {items
                        .filter((item) => item.type !== "ENTER")
                        .map((item) => (
                    <div className="chatTalkWrap">
                    {/* new Intl.DateTimeFormat('kr').format(new Date()) */}
                    <div className={ memberNum != item.sender ? "chatMessage" : "chatMessage-My"}>
                      {item.type == 'IMG'? <img className="chatImg" src={item.message} alt="채팅이미지"/>
                      : item.message}
                    </div>
                    <div className={ memberNum != item.sender ? "chatTalkTime" : "chatTalkTime-My"}>
                      {new Date(Date.parse(item.time)+ 1*1000*60*60*9).toLocaleDateString("ko-KR", options)}</div>
                    </div>
                      // <div className="chatMessage-My">{`${item.message}`}</div>
                      ))}
                  </div>  
          </div>

            {host == memberNum?  <ChatSellButton partner_num={partner_num}
             changeChatSendImg={changeChatSendImg} onClickImgMsgSend={onClickImgMsgSend}/> 
             : <ChatBuyButton partner_num={partner_num}/>    }
            <div className="chatBottom">
              <input className="chatSend" value ={inputMsg} onChange={onChangMsg} onKeyUp={onEnterKey}/>
              <button onClick={ (e) => {onClickMsgSend(e);}}><img src={send1} alt="send"/></button>
              {/* <button className="msg_close" onClick={onClickMsgClose}>채팅 종료 하기</button> */}
            </div>
            
        </div>
    </div>
  )
}
export default ChatPage
