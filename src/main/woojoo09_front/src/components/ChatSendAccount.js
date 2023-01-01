import React from "react";
import { useState } from "react";
import "../style/chat.scss"
import {BankKind} from "../util/util"
import api from "../api/api";

const SendAccount = ({partner_num, sendAccountButton}) => {
        
    const [bank, setBank] = useState("");
    const [directBank, setDirectBank] = useState("");
    const [account, setAccount] = useState("");
    const [accountholder, setAccountholder] = useState("");
    const [bankSelect, setbankSelect] = useState("");
    
    // console.log(partner_num);


  const onChangeBank = (e) => {
    setBank(e.target.value);
    // console.log(e.target.value);
  }
  const onChangeDirectBank = (e) => {
    setDirectBank(e.target.value);
    // console.log(e.target.value);
  }
  const onChangeAccount = (e) => {
    setAccount(e.target.value);
  }
  const onChangeAccountholder = (e) => {
    setAccountholder(e.target.value);
  }

  const accountInsert = () =>{
    const fetchData = async () => {
      if(bank == "직접입력"){
        try {
          // console.log();
            const res = await api.accountsend(partner_num, directBank, account, accountholder);
            // console.log(res.data);
            setBank("");
            setAccount("");
            setAccountholder("");
            setbankSelect("");
        } catch(e) {
            console.log(e);
        }
      }else{
        try {
          // console.log();
            const res = await api.accountsend(partner_num, bank, account, accountholder);
            // console.log(res.data);
            setBank("");
            setAccount("");
            setAccountholder("");
            setbankSelect("");
        } catch(e) {
            console.log(e);
        }
      }
    };
    fetchData();
  } 
    return(

        <div className="sendAccount">
          <div>계좌번호</div>
          <select onChange= {onChangeBank} >
           <option value={bankSelect}>은행 선택</option>
              {BankKind.map((e) => (
              <option key={e.bankValue} value={e.bankName}>
                {e.bankName}
              </option>
               ))}
          </select>
          {bank === "직접입력" && <input onChange= {onChangeDirectBank} placeholder={"은행"} value={directBank} />}
          <input value={account} onChange={onChangeAccount} placeholder={"계좌번호"}/>
          <input value={accountholder} onChange={onChangeAccountholder} placeholder={"예금주"}/>
          <button onClick={ (e) => {accountInsert(e);sendAccountButton(e);}}>전송</button>
        </div>
    );
};
export default SendAccount;