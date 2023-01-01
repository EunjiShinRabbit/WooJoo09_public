import { useState,  useEffect } from "react";
import api from "../api/api"


const ChatAccLook = ({partner_num}) =>{
    const [lists, setLists] = useState('');
    const [loading, setLoading] = useState(false);
    const [prepared, setPrepared] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
         setLoading(true);
          try {
            const response = await api.chatAccount(partner_num);
            setLists(response.data[0]);
            // console.log(response.data);
            // console.log(response.data[0].chatListContent[0].partner_num);
            // console.log(partner_num); 
            if(response.data[0].chatAccount[0].account_holder == null &&
              response.data[0].chatAccount[0].account_num == null&&
              response.data[0].chatAccount[0].bank == null
              ) {setPrepared(false);}
             else { setPrepared(true);}
          } catch (e) {
            console.log(e);
          }
          setLoading(false);
        };
        fetchData();
      }, []);

    return(
        <>
        {prepared ?
        lists.chatAccount.map(({account_holder, account_num, bank}) => (
           
        <div className="sendDelivery">
            계좌 정보는
            <br/>
            예금주 : {account_holder},
            <br/>
            계좌번호 : {account_num},
            <br/>
            은행 : {bank} 입니다.
        </div>

        )):
        <div className="sendDelivery">아직 입력된 계좌 정보가 없습니다.</div>
      } 
        </>
    );
}

export default ChatAccLook;