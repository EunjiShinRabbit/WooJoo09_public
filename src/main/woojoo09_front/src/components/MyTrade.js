import { useNavigate } from "react-router-dom";

const MyTrade = ({memberInfo}) =>{
  
  const navigate = useNavigate();

  // 버튼 클릭시 호출
  const move = () => {
    // 두번재 인자의 state 속성에 원하는 파라미터를 넣어준다. (id, job을 넣어봤다)
   // eslint-disable-next-line no-lone-blocks
   {navigate('/myTrade', {
      state: {
        name : "주최한 공구",
        value : "myHostTrade",
        memberInfo : memberInfo
      }
    });
  }
  };
  const move1 = () => {
   navigate('/myTrade', {
    state: {
      name : "참여 대기 중인 공구",
      value : "myWaitTrade",
      memberInfo : memberInfo
    }
  });
}
  const move2 = () => {
    navigate('/myTrade', {
     state: 
      {
        name : "참여 중인 공구",
        value : "myJoinTrade",
        memberInfo : memberInfo
      },
   });
  }
   const move3 = () => {
    navigate('/myTrade', {
     state: {
        name : "참여 완료인 공구",
        value : "myDoneTrade",
        memberInfo : memberInfo
     }
   });
   }
   const move4 = () => {
    navigate('/myTrade', {
     state: {
      name : "찜한 공구",
      value : "myStarTrade",
      memberInfo : memberInfo
     }
   });
   }
   
  return(
    <div className="mywrite">
      <p>내 공구</p>
      <p onClick={move}>주최한 공구</p>
      <p onClick={move1}>참여 대기 중인 공구</p>
      <p onClick={move2}>참여 중인 공구</p>
      <p onClick={move3}>참여 완료인 공구</p>
      <p onClick={move4}>찜한 공구</p>
    </div>
  );
};
export default MyTrade;