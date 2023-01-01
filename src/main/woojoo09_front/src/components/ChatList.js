
import { useState,  useEffect } from "react";
import { NavLink, Link, useNavigate } from "react-router-dom";
import house from "../resources/house.png"
import "../style/chat.scss"
import "../style/common.scss"
import api from "../api/api"
import { defaultImgs } from "../util/util"

const ChatList = () =>{

  const [seller, setSeller] = useState('');
  const [lists, setLists] = useState('');
  const [loading, setLoading] = useState(false);
  const [prepared, setPrepared] = useState(false);
  const [partnerNum, setPartnerNum] = useState('');
  const [memberNum, setMemberNum] = useState('');
  // const [nickname, setNickname] = useState('');
  // const [aceeptTrade, setAceeptTrade] = useState('');
  // const [doneTrade, setDoneTrade] = useState('');
  // const [product, setProduct] = useState('');
  const [chatTime, setChatTime] = useState('');
  const navigate = useNavigate();



  const date = new Date();

  var options = {
    // year: 'numeric',
    month: 'numeric',
    day: 'numeric',
    hour: 'numeric',
    // hour: '2-digit',
    minute : 'numeric',
  }
  
 

  useEffect(() => {
    const fetchData = async () => {
     setLoading(true);
      try {
        const response = await api.chatList();
        setLists(response.data[0]);
        setMemberNum(response.data[1])
        // console.log(response.data[0]);
        // console.log(response.data[0].chatListContent);
        // console.log(response.data[0].chatListContent[0].chat_time);
        // console.log(response.data[0].chatListContent[0].partner_num);
        // let chatContent = lists.chat_content;
        // console.log(chatContent);
        setPrepared(true);
        // console.log(response.data);
        // 통신하지 않고 렌더링 치기 위함
        // let chatAlert = lists.map(e => { 
        //   if(e.partnerNum === partnerNum && e.sender !== memberNum){
            
        //   }
        //   return chatAlert
        // })
        // lists(alert);
      } catch (e) {
        console.log(e);
      }
      setLoading(false);
    };
    fetchData();
  }, []);


  const chatTest = (partnerNum) =>{
    const fetchData = async () => {
      try {
        // console.log("partnerNum : " + partnerNum);
          const res = await api.chatRoomOpen(partnerNum);
          // console.log("res.data" + res.data);
      } catch(e) {
          console.log(e);
      }
    };
    fetchData();
  } 


  const move = (list) => {
    // 두번재 인자의 state 속성에 원하는 파라미터를 넣어준다. 
   // eslint-disable-next-line no-lone-blocks
   {navigate(`/chat/${list.partner_num}`, {
      state: {
        list : list,
        memberNum : memberNum
      }
    });
  }
  };
  // const isRead = (partnerNum, memberNum) => { 
  //   const fetchData = async () => {
  //     try {
  //         const res = await api.chatIsReadInsert(partnerNum, memberNum);
  //         console.log("res.data" + res.data);
  //     } catch {
  //         console.log("error");
  //     }
  //   };
  //   fetchData();
  // }


  return(
    <div className="wrapperLeft">
            {/* <div className="chatLogo">
             
            </div> */}
            <div className="chatList">
              <div className="chatLogo"><Link to= {'/main'}><img src = {house} alt="로고"/></Link></div>
              <div>채팅목록</div>
            </div>
            { prepared && 
            lists.chatListContent.map((list) => (
            <div className="chatDetail"> 
              <div className="chatButton">
                    <p onClick={()=>{chatTest(list.partner_num); move(list);}}>
                      <span><img src = {list.img_url? list.img_url : list.category_num === 1 ? defaultImgs.패션.imgUrl :
                      list.category_num === 2 ? defaultImgs.뷰티.imgUrl : list.category_num === 3 ? defaultImgs.생활.imgUrl :
                      list.category_num === 4 ? defaultImgs.식품.imgUrl : list.category_num === 5 ? defaultImgs.취미.imgUrl :
                      defaultImgs.반려동물.imgUrl} alt="물품이미지"/></span>
                      <p className="chatDetailNick">{list.nickname}</p>
                      <p className="chatTime">{new Date(list.chat_time).toLocaleDateString("ko-KR", options)}</p>
                      <div>{list.msgType == "IMG"? "사진" : list.chat_content.length > 10 ? list.chat_content.substring(0,10)+"...": list.chat_content}</div>                     
                           {list.countUnreadChat > 0 && <p className="chatAlert"/>}
                    </p>
                </div>
               </div>
              ))}
              
              
    </div>
  );
}
export default ChatList