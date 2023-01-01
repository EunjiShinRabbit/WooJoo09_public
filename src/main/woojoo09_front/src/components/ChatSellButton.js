import { useState, useEffect } from "react";
import SendAccount from "./ChatSendAccount";
import SendDelivery from "./ChatSendDelivery"
import SendPhoto from "./ChatSendPhoto";
import DelAddrLook from "./ChatDelAddrLook";

const ChatSellButton = ({partner_num, changeChatSendImg, onClickImgMsgSend}) => {
    const [visible, setVisible] = useState(false);
    const [visibleDel, setVisibleDel] = useState(false);
    const [visiblePho, setVisiblePho] = useState(false);
    const [visibleDelAddr, setVisibleDelAddr] = useState(false);
    const [type, setType] = useState('');

    useEffect(() => {
        setVisible(false);
        setVisibleDel(false);
        setVisibleDelAddr(false);
        setVisiblePho(false);
    }, [partner_num]);

    const visibleAccount = (e) => {
        // console.log("선택한 값 : " +e.target.value)
        setVisible(!visible);
        setVisibleDel(false);
        setVisiblePho(false);
        setVisibleDelAddr(false);
        setVisiblePho(false);
        setType(e.target.value)
    }
        // console.log("값이 있나요?" +type);
    const visibleDelivery = (e) => {
        setVisibleDel(!visibleDel);
        setVisible(false);
        setVisiblePho(false);
        setVisibleDelAddr(false);
        setVisiblePho(false);
    }
    const visiblePhoto = (e) => {
        setVisiblePho(!visiblePho);
        setVisible(false);
        setVisibleDel(false);
        setVisibleDelAddr(false);
    }

    const visibleDelAddress = (e) => {
        setVisibleDelAddr(!visibleDelAddr);
        setVisible(false);
        setVisibleDel(false);
        setVisiblePho(false);
        // console.log(partner_num);
    }

    const sendImgbutton = (e) => {
        setVisiblePho(false);
    }
    const sendAccountButton = (e) => {
        setVisible(false);
    }
    const sendDelButton = (e) => {
        setVisibleDel(false);
    }

    return(
        <>
        <div className="sendPrivacy">  
            { visiblePho && <SendPhoto partner_num={partner_num}
             changeChatSendImg={changeChatSendImg} onClickImgMsgSend={onClickImgMsgSend}
             sendImgbutton={sendImgbutton}/>}
            { visible && <SendAccount partner_num={partner_num} sendAccountButton={sendAccountButton}/> }
            { visibleDel && <SendDelivery  partner_num={partner_num} sendDelButton={sendDelButton}/> }
            { visibleDelAddr && <DelAddrLook partner_num={partner_num}/> }
        
        </div>

        <div className="chatSell">
            <button onClick={visiblePhoto}>{visiblePho ? "숨기기" : "사진전송"}</button>
            <button onClick={visibleAccount} value="bank">{visible ? "숨기기" : "계좌전달"}</button>
            <button onClick={() => window.open('http://st.sweettracker.co.kr/#/', '_blank')}>운송장 조회</button>
            <button onClick={visibleDelivery}>{visibleDel ? "숨기기" : "운송장 전송"}</button>
            <button onClick={visibleDelAddress}>{visibleDelAddr ? "숨기기" : "배송지 조회"}</button>
        </div>
     </>
    );
}
export default ChatSellButton;