import React from "react";
import { useState } from "react";
import "../style/chat.scss"
import "../style/chat.scss"
import api from "../api/api";

const ChatShipAdr = ({partner_num, onChangeDelInput}) => {
    const [deliveryAddress, setDeliveryAddress] = useState("");
    const [deliveryName, setDeliveryName] = useState("");
    const [deliveryPhone, setDeliveryPhone] = useState("");


  const onChangeReceiverName = (e) => {
    setDeliveryName(e.target.value);
  }
  const onChangeReceiverAddress = (e) => {
    setDeliveryAddress(e.target.value);
  }
  const onChangeReceiverPhone = (e) => {
    setDeliveryPhone(e.target.value);
  }
  const deliveryaddrInsert = () =>{
    const fetchData = async () => {
      try {
        console.log();
          const res = await api.deliveryaddrsend(partner_num, deliveryAddress, deliveryName, deliveryPhone);
          // console.log(res.data);
          setDeliveryAddress("");
          setDeliveryName("");
          setDeliveryPhone("");
      } catch {
          console.log("error");
      }
    };
    fetchData();
  } 
    return(
        <>
            <div className="sendAccount">
                <div>배송정보 입력</div>
                <input value={deliveryName} onChange= {onChangeReceiverName} placeholder={"이름"}/>
                <input value={deliveryAddress} onChange={onChangeReceiverAddress} placeholder={"주소"}/>
                <input value={deliveryPhone} onChange={onChangeReceiverPhone} placeholder={"핸드폰번호"}/>
                <button onClick={(e) => {deliveryaddrInsert(e);onChangeDelInput(e);}}>완료</button>
            </div>
        </>
    );
};
export default ChatShipAdr;