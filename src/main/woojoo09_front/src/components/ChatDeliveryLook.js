import { useState,  useEffect } from "react";
import api from "../api/api"


const ChatDeliveryLook = ({partner_num}) =>{
    const [lists, setLists] = useState('');
    const [loading, setLoading] = useState(false);
    const [prepared, setPrepared] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
         setLoading(true);
          try {
            const response = await api.chatDeliveryNum(partner_num);
            setLists(response.data[0]);
            // console.log(response.data);
            // console.log(response.data[0].chatListContent[0].partner_num);
            // console.log(partner_num);
            if(response.data[0].chatDeliNum[0].delivery_company == null &&
              response.data[0].chatDeliNum[0].delivery_num == null
              ){
              setPrepared(false);
            }
            else{setPrepared(true);}
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
        lists.chatDeliNum.map(({delivery_company, delivery_num}) => (
           
        <div className="sendDelivery">
            운송장 정보는
            <br/>
            택배사 : {delivery_company},
            <br/>
            운송장 번호 : {delivery_num} 입니다.
        </div>

        )) :
        <div className="sendDelivery">아직 입력된 운송장 정보가 없습니다.</div>} 
        </>
    );
    }
    
    export default ChatDeliveryLook;