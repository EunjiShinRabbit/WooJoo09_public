import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import ChatShipAdr from "./ChatShipAdr";
import ChatAccLook from "./ChatAccLook";
import ChatDeliveryLook from "./ChatDeliveryLook";

const ChatBuyButton = ({partner_num}) => {
    const [visibleAccLook, setvisibleAccLook] = useState(false);
    const [visibleDelInput, setvisibleDelInput] = useState(false);
    const [visibleDelLook, setvisibleDelLook] = useState(false);
    const [type, setType] = useState('');

    useEffect(() => {
        setvisibleAccLook(false);
        setvisibleDelInput(false);
        setvisibleDelLook(false);
    }, [partner_num]);

    const visibleAccountLook = (e) => {
        // console.log("선택한 값 : " +e.target.value)
        setvisibleAccLook(!visibleAccLook);
        setvisibleDelInput(false);
        setvisibleDelLook(false);
        setType(e.target.value)
      
    }
        // console.log("값이 있나요?" +type);
    const visibleDeliveryLook = (e) => {
        setvisibleDelLook(!visibleDelLook);
        setvisibleDelInput(false);
        setvisibleAccLook(false);

    }
    const visibleDeliveryInput = (e) => {
        setvisibleDelInput(!visibleDelInput);
        setvisibleDelLook(false);
        setvisibleAccLook(false);
    }
    const onChangeDelInput = (e) => {
        setvisibleDelInput(false);
    }

    return(
        <>
        <div className="sendPrivacy">
             {visibleAccLook && <ChatAccLook partner_num={partner_num}/> } 
             {visibleDelInput && <ChatShipAdr partner_num={partner_num} onChangeDelInput={onChangeDelInput}/> }
             {visibleDelLook && <ChatDeliveryLook partner_num={partner_num}/>} 
        </div>

        <div className="chatSell">
            <button onClick={visibleAccountLook} >{visibleAccLook ? "숨기기" : "계좌조회"}</button>
            <button onClick={visibleDeliveryInput}>{visibleDelInput ? "숨기기" : "배송정보입력"}</button>
            <button onClick={visibleDeliveryLook}>{visibleDelLook ? "숨기기" : "운송장조회"}</button>
            <button onClick={() => window.open('http://st.sweettracker.co.kr/#/', '_blank')}>배송조회</button>
            <button onClick={() => window.open('https://cyberbureau.police.go.kr/mobile/tm/sub/sub_02.jsp', '_blank')}>사기피해조회</button>
            
        </div>
     </>
    );
}
export default ChatBuyButton;