import { useLocation } from 'react-router-dom';

const ChatListTime = () => {
    const location = useLocation();
    const chatTime = location.state.chat_time;
//   const displayCreatedAt = (chatTime) => {
//     let startTime = new Date(chatTime);
//     let nowTime = Date.now();
//     if (parseInt(startTime - nowTime) > -60000) {
//       return <Moment format="방금 전">{startTime}</Moment>;
//     }
//     if (parseInt(startTime - nowTime) < -86400000) {
//       return <Moment format="MMM D일">{startTime}</Moment>;
//     }
//     if (parseInt(startTime - nowTime) > -86400000) {
//       return <Moment fromNow>{startTime}</Moment>;
//     }
//   };

function timeForToday(chatTime) {
    const today = new Date();
    const timeValue = new Date(chatTime);

    const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
    if (betweenTime < 1) return '방금전';
    if (betweenTime < 60) {
        return `${betweenTime}분전`;
    }

    const betweenTimeHour = Math.floor(betweenTime / 60);
    if (betweenTimeHour < 24) {
        return `${betweenTimeHour}시간전`;
    }

    const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
    if (betweenTimeDay < 365) {
        return `${betweenTimeDay}일전`;
    }

    return `${Math.floor(betweenTimeDay / 365)}년전`;
}
  return (
    <>
    {timeForToday}
    </>
  );

}
export default ChatListTime;