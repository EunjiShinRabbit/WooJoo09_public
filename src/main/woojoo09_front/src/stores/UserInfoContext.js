import React, { createContext, useState } from 'react'; // createContext를 불러온다.

//하나의 Context를 생성합니다.
export const UserInfoContextStore = createContext();

/**
 * Context를 생성할 때 기본 값을 세팅한다. 
 * React에서는 값을 컨트롤 할 때는 Component를 만들어 사용하는걸 지향한다.
 *
 * **/
const UserInfoContext = (props) => {
  const [ tokenMsg, setTokenMsg ] = useState(); // 유저 토큰
  
  //유저 정보를 하나의 객체로 만들어준다.
  const UserInfo = {
    tokenMsg,
    setTokenMsg
  }

  /**
   * UserInfoContextStore.Provider : Provider는 구독(하위 Component)하고 있는 자식 Component에게 정보를 보내준다는 설정
   * value={UserInfo} : 자식 Component에게 값을 전달하기 위한 설정
   * {props.children} : 자식 Component를 랜더링 하기위해 설정
   */
  
  return (
    <UserInfoContextStore.Provider value={UserInfo}>
      {props.children}
    </UserInfoContextStore.Provider>
  );
};

export default UserInfoContext;