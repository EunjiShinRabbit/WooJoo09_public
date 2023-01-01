import { useState,  useEffect } from "react";
import api from "../api/api"


const DelAddrLook = ({partner_num}) => {

    const [lists, setLists] = useState('');
    const [loading, setLoading] = useState(false);
    const [prepared, setPrepared] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
         setLoading(true);
          try {
            const response = await api.chatDeliAddr(partner_num);
            setLists(response.data[0]);
            // console.log(response.data);
            // console.log(response.data[0].chatListContent[0].partner_num);
            // console.log(partner_num);
            if(response.data[0].chatDeliaddress[0].delivery_name == null &&
              response.data[0].chatDeliaddress[0].delivery_address == null&&
              response.data[0].chatDeliaddress[0].delivery_phone == null
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
        lists.chatDeliaddress.map(({delivery_name, delivery_address, delivery_phone}) => (
           
        <div className="sendDelivery">
            원하시는 배송 정보는
            <br/>
            이름 : {delivery_name},
            <br/>
            배송지정보 : {delivery_address},
            <br/>
            핸드폰 번호 : {delivery_phone} 입니다.
        </div> 

        )) :
        <div className="sendDelivery">아직 입력된 배송 정보가 없습니다.</div>
        } 
        </>
    );
}
export default DelAddrLook;