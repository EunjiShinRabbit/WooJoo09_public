import React from "react";
import { useState } from "react";
import { logisticsCompanys } from "../util/util";
import "../style/chat.scss"
import api from "../api/api";

const SendDelivery = ( {partner_num, sendDelButton} ) => {
        
  const [deliveryCompany, setDeliveryCompany] = useState("");
  const [directDeliveryCompany, setDirectcDeliveryCompany] = useState("");
  const [deliveryNum, setdeliveryNum] = useState("");
  const [Selected, setSelected] = useState("");
  // console.log(partner_num);

  const onChangeDeliveryCompany = (e) => {
    setDeliveryCompany(e.target.value);
    // console.log(e.target.value);
  }
  const onChangeDirectDeliveryCompany = (e) => {
    setDirectcDeliveryCompany(e.target.value);
    // console.log(e.target.value);
  }
  const onChangeDeliveryNum = (e) => {
    setdeliveryNum(e.target.value);
  }
   const deliveryInsert = () =>{
    const fetchData = async () => {
      if (deliveryCompany == "직접입력"){
        try {
          // console.log();
            const res = await api.deliverysend(partner_num, directDeliveryCompany, deliveryNum);
            // console.log(res.data);
            setDeliveryCompany("");
            setdeliveryNum("");
            // setSelected("");
        } catch(e) {
            console.log(e);
        }
      }else{
        try {
          // console.log();
            const res = await api.deliverysend(partner_num, deliveryCompany, deliveryNum);
            // console.log(res.data);
            setDeliveryCompany("");
            setdeliveryNum("");
            // setSelected("");
        } catch(e) {
            console.log(e);
        }
      }
    };
    fetchData();
  } 
 

    return(
        //value={""} 
        <div className="sendDelivery">
          <div>운송장</div>
          <select
            onChange={onChangeDeliveryCompany}
            >
              <option value={Selected} >택배사 선택</option>
              {logisticsCompanys.map((e) => (
              <option key={e.gisvalue} value={e.logisticsCompany}>
                {e.logisticsCompany}
              </option>
            ))}
          </select>
          {deliveryCompany === "직접입력" && 
          <input onChange= {onChangeDirectDeliveryCompany} placeholder={"택배사"}/>}
          <input value={deliveryNum} onChange={onChangeDeliveryNum} placeholder={"운송장번호"}/>

          <button onClick={(e) => {deliveryInsert(e);sendDelButton(e);}}>전송</button>
        </div>
    );
};
export default SendDelivery;