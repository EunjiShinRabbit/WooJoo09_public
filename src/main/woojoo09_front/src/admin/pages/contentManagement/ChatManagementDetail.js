import { useState, useEffect } from "react";
import api from "../../adminApi";
import Loader from "../../components/Loader";
import TopBar from "../../components/TopBar";
import { useParams } from 'react-router-dom';
import Header from "../../components/Header";
import Sidebar from "../../components/SideBar";

const ChatManagementDetail = () =>{

  let { partnerNum } = useParams();

  const [lists, setLists] = useState('');
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
     setLoading(true);
      try {
        const response = await api.adminChatSelectDetail(partnerNum);
        setLists(response.data.chatDetailData);
        // console.log(response.data.chatDetailData)
      } catch (e) {
        console.log(e);
      }
      setLoading(false);
    };
    fetchData();
  }, []);
  if(loading) {
    return <div className="center"><Loader/></div>
  }

    return(
      <div className="adminWrapper">
        <Header/>
        <Sidebar/>
      <div className="center">
        <TopBar name="채팅 상세" high1="거래 참여 관리" high2="콘텐츠 관리"/>
        <div>
          {lists &&
            lists
            .map(({sender, chat_content, chat_time, msg_type}) => (
              <>
              <p>보내는 사람 : {sender}</p>
              <p>{chat_content}</p>
              <p>{chat_time}</p>
              <p>{msg_type}</p>
              </>
            ))
          }
        </div>
      </div>
      </div>
    );
};
export default ChatManagementDetail;