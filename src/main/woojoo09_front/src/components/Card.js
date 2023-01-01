import { Link } from "react-router-dom";
import { useEffect, useState } from 'react'
import star from "../resources/star.png";
import api from "../api/api";
import yellowStar from "../resources/starFill.png"
import { defaultImgs } from "../util/util"

const Card = ({lists, isLogin, isAdmin, changeLists}) =>{
  const [thisDate, setThisDate] =  useState(new Intl.DateTimeFormat('kr').format(new Date()));
  const [starError, setStarError] = useState();

  const starInsert = (tradeNum) =>{
    const fetchData = async () => {
      // setLoading(true); 로딩있으면 깜빡거리는거같아서 뺏음, 데이터 뭐 많이 가져오는것도없고
      try {
        const response = await api.starInsert(tradeNum);
        // console.log(response.data);
        if(response.data.myStar === "loginError") {
          setStarError("로그인 상태를 확인해주세요");
        } else if (response.data.myStar === "duplicate"){
          setStarError("이미 스크랩을 한 공동구매입니다");
        } 
        else {
          let arr = lists.map(val => {
            if(val.tradeNum === tradeNum){
              val.myStar = 1;
            }
            return val
          })
          changeLists(arr);
        }
      } catch (e) {
        console.log(e);
      }
      // setLoading(false);
    };
    fetchData();
  }

  const starDelete = (tradeNum) =>{
    const fetchData = async () => {
      // setLoading(true); 로딩있으면 깜빡거리는거같아서 뺏음, 데이터 뭐 많이 가져오는것도없고
      try {
        const response = await api.starDelete(tradeNum);
        // console.log(response.data);
        if(response.data.myStar === "loginError") {
          setStarError("로그인 상태를 확인해주세요");
        } 
        else {
          let arr = lists.map(val => {
            if(val.tradeNum === tradeNum){
              val.myStar = 0;
            }
            return val
          })
          changeLists(arr);
        }
      } catch (e) {
        console.log(e);
      }
      // setLoading(false);
    };
    fetchData();
  }

  return(
    <>
    {lists && lists.map(list => (
    <div className="card">
      <Link to={`/detail/${list.tradeNum}`} >
      <div className="cardImg">
        {list.representImg? <img src={list.representImg} alt="상품 대표 이미지"/> : 
        <img src={list.categoryName === "패션" ? defaultImgs.패션.imgUrl :
        (list.categoryName === "뷰티" ? defaultImgs.뷰티.imgUrl : 
        (list.categoryName === "생활" ? defaultImgs.생활.imgUrl : 
        (list.categoryName === "식품" ? defaultImgs.식품.imgUrl :
        (list.categoryName === "취미" ? defaultImgs.취미.imgUrl : defaultImgs.반려동물.imgUrl))))
        } alt="상품 대표 이미지"/>}
      </div>
      <div className="cardDesc">
        <p>{list.categoryName}</p>
        <p className="cardName">{list.product.length > 16 ? list.product.substring(0,16)+"..." : list.product}</p>
        <p>{list.price}원</p>
        <p>{list.city && <span>{list.city}</span>}{list.town && <span>{list.town}</span>}</p>
        {list.doneTrade === 'ONGOING' && <p><span>{list.acceptPartner} / {list.limitPartner}</span>
        {/* <span>D - {
         Math.floor((new Date(list.dueDate).getTime() - new Date(thisDate).getTime()) / (1000 * 60 * 60 * 24))
        }</span> */}
        <span>D {Math.floor((new Date(list.dueDate).getTime() - new Date(thisDate).getTime()) / (1000 * 60 * 60 * 24)) > 0?
          ` - ${Math.floor((new Date(list.dueDate).getTime() - new Date(thisDate).getTime()) / (1000 * 60 * 60 * 24))}` :
          (Math.floor((new Date(list.dueDate).getTime() - new Date(thisDate).getTime()) / (1000 * 60 * 60 * 24)) < 0 ? 
          ` + ${Math.abs(Math.floor((new Date(list.dueDate).getTime() - new Date(thisDate).getTime()) / (1000 * 60 * 60 * 24)))}`
          : ` - DAY`)
          }</span></p>}
        {list.doneTrade === 'FULL' && <p><span>모집 완료</span></p>}
        {list.doneTrade === 'DONE' && <p><span>종료</span></p>}
        <p>{list.tradeMethod === 'BOTH' ? <><span>직거래</span><span>택배</span></> :
        (list.tradeMethod === 'DIRECT' ? <span>직거래</span> : <span>택배</span>)}</p>
      </div>
      </Link>
      {(!isLogin || isAdmin) ? <img className="cardStar" src={star} alt="스크랩"/> :
          list.myStar === 0 ?  <img className="cardStar" onClick={
            () => {starInsert(list.tradeNum);}} src={star} alt={`스크랩`}/> :
          <img className="cardStar" onClick={()=>{starDelete(list.tradeNum)}} src={yellowStar} alt="스크랩취소"/>}
    </div>
    ))}
    </>
  );
}
export default Card