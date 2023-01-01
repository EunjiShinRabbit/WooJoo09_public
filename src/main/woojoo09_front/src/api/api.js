import axios from "axios";
import { useContext } from 'react'; // useContext를 불러온다.
import { UserInfoContextStore } from "../stores/UserInfoContext"
import { getCookie } from "../util/cookie";
const HEADER = {"Content-type" : "application/json"}
// const HEADER = 'application/json';
// const BASE_URL = "http://localhost:9009/developerkirby/";
const BASE_URL = "/developerkirby/";

axios.defaults.withCredentials = true; // withCredentials 전역 설정
  // const cookieSet = {
  //   withCredentials: true
  // 안되면 헤더 앞에 이 object 추가해서 보내기}
const api = {
  tokencheck: async function() {
    const tokenCmd = {
      cmd : "tokencheck"
    }
    return await axios.post(BASE_URL + "tokencheck", tokenCmd, HEADER);
  },

  maketokentest: async function() {
    const testCmd = {
      cmd: "test"
    }
    return await axios.post(BASE_URL+ "test", testCmd, HEADER);
  },
  tradeSearchSelect: async function(target, page, size) {
    const searchSelectCmd = {
      target: target,
      page: page,
      size: size
    }
    return await axios.post(BASE_URL+ "searchselect", searchSelectCmd, HEADER);
  },
  chatReadCheck: async function() {
    const chatReadCheckCmd = {
      cmd: "chatReadCheck"
    }
    return await axios.post(BASE_URL+ "chatreadcheck", chatReadCheckCmd, HEADER);
  },
  tradeSelect: async function(option, city, town, page, size) {
    const tradeSelectCmd = {
      option: option,
      city: city,
      town: town,
      page: page,
      size: size
    }
    return await axios.post(BASE_URL+ "tradeselect", tradeSelectCmd, HEADER);
  },
  tradeSelectCategory: async function(category, option, city, town, page, size) {
    const tradeSelectCmd = {
      category: category,
      option: option,
      city: city,
      town: town,
      page: page,
      size: size
    }
    return await axios.post(BASE_URL+ "tradeselectcategory", tradeSelectCmd, HEADER);
  },
  starInsert: async function(target) {
    const starInsertCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "starinsert", starInsertCmd, HEADER);
  },
  starDelete: async function(target) {
    const starDeleteCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "stardelete", starDeleteCmd, HEADER);
  },
  tradeCount: async function() {
    const tradeDetailSelectCmd = {
      cmd: "countTrade"
    }
    return await axios.post(BASE_URL+ "tradecount", tradeDetailSelectCmd, HEADER);
  },
  tradeInsert: async function( imgUrl, representUrl, category, product, price, 
    limitPartner, dueDate, tradeMethod, city, town, tradePlace, productDetail) {
    const tradeInsertCmd = {
      representUrl: representUrl,
      category : category,
      product: product,
      price: price,
      limitPartner: limitPartner,
      dueDate: dueDate,
      tradeMethod: tradeMethod,
      city: city,
      town: town,
      tradePlace: tradePlace,
      productDetail: productDetail,
      imgUrl: imgUrl
    }
    return await axios.post(BASE_URL+ "tradeinsert", tradeInsertCmd, HEADER);
  },
  tradeImgUpdate: async function(tradeNum) {
    const tradeImgUpdateCmd = {
      tradeNum: tradeNum
    }
    return await axios.post(BASE_URL+ "tradeimgupdate", tradeImgUpdateCmd, HEADER);
  },
  countNthTrade: async function(tradeNum) {
    const countNthTradeCmd = {
      tradeNum: tradeNum
    }
    return await axios.post(BASE_URL+ "countnthtrade", countNthTradeCmd, HEADER);
  },
  tradeUpdate: async function( tradeNum, imgUrl,
    representUrl, category, product, price, limitPartner, dueDate, tradeMethod, city, town, tradePlace, productDetail) {
    const tradeUpdateCmd = {
      tradeNum :  tradeNum,
      representUrl: representUrl,
      category : category,
      product: product,
      price: price,
      limitPartner: limitPartner,
      dueDate: dueDate,
      tradeMethod: tradeMethod,
      city: city,
      town: town,
      tradePlace: tradePlace,
      productDetail: productDetail,
      imgUrl: imgUrl
    }
    return await axios.post(BASE_URL+ "tradeupdate", tradeUpdateCmd, HEADER);
  },
  tradeDetailSelect: async function(target) {
    const tradeDetailSelectCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "tradedetailselect", tradeDetailSelectCmd, HEADER);
  },
  tradeDetailImgSelect: async function(target) {
    const tradeDetailImgSelectCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "tradedetailimgselect", tradeDetailImgSelectCmd, HEADER);
  },
  partnerInsert: async function(target) {
    const partnerInsertCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "partnerinsert", partnerInsertCmd, HEADER);
  },
  partnerDelete: async function(target) {
    const partnerDeleteCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "partnerdelete", partnerDeleteCmd, HEADER);
  },
  partnerDeleteHost: async function(target, partner) {
    const partnerDeleteHostCmd = {
      target: target,
      partner : partner
    }
    return await axios.post(BASE_URL+ "partnerdeletehost", partnerDeleteHostCmd, HEADER);
  },
  partnerAccept: async function(target, partner) {
    const partnerAcceptCmd = {
      target: target,
      partner : partner
    }
    return await axios.post(BASE_URL+ "partnerinsert", partnerAcceptCmd, HEADER);
  },
  complainInsert: async function(target) {
    const complainInsertCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "complaininsert", complainInsertCmd, HEADER);
  },
  tradeDelete: async function(target) {
    const tradeDeleteCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "tradedelete", tradeDeleteCmd, HEADER);
  },
  tradeClose: async function(tradeNum) {
    const tradeCloseCmd = {
      tradeNum: tradeNum
    }
    return await axios.post(BASE_URL+ "tradeclose", tradeCloseCmd, HEADER);
  },
  tradeFinish: async function(tradeNum) {
    const tradeFinishCmd = {
      tradeNum: tradeNum
    }
    return await axios.post(BASE_URL+ "tradefinish", tradeFinishCmd, HEADER);
  },
  doneTradeUpdateFull: async function(target) {
    const doneTradeUpdateFullCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "donetradeupdatefull", doneTradeUpdateFullCmd, HEADER);
  },
  doneTradeUpdateDone: async function(target) {
    const doneTradeUpdateDoneCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "donetradeupdatedone", doneTradeUpdateDoneCmd, HEADER);
  },
  hostTradeSelect: async function(page, size) {
    const hostTradeSelectCmd = {
      page: page,
      size: size
    }
    return await axios.post(BASE_URL+ "hosttradeselect", hostTradeSelectCmd, HEADER);
  },
  partnerTradeSelectReject: async function(page, size) {
    const partnerTradeSelectRejectCmd = {
      page: page,
      size: size
    }
    return await axios.post(BASE_URL+ "partnertradeselectreject", partnerTradeSelectRejectCmd, HEADER);
  },
  partnerTradeSelectOngoing: async function(page, size) {
    const partnerTradeSelectOngoingCmd = {
      page: page,
      size: size
    }
    return await axios.post(BASE_URL+ "partnertradeselectongoing", partnerTradeSelectOngoingCmd, HEADER);
  },
  partnerTradeSelectDone: async function(page, size) {
    const partnerTradeSelectDonetCmd = {
      page: page,
      size: size
    }
    return await axios.post(BASE_URL+ "partnertradeselectdone", partnerTradeSelectDonetCmd, HEADER);
  },
  starTradeSelect: async function(page, size) {
    const starTradeSelectCmd = {
      page: page,
      size: size
    }
    return await axios.post(BASE_URL+ "startradeselect", starTradeSelectCmd, HEADER);
  },
  goodInsert: async function(target) {
    const goodInsertCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "goodinsert", goodInsertCmd, HEADER);
  },
  dislikeInsert: async function(target) {
    const dislikeInsertCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "dislikeinsert", dislikeInsertCmd, HEADER);
  },
  bannerSelect: async function() {
    const bannerSelectCmd = {
      cmd: "bannerSelect"
    }
    return await axios.post(BASE_URL+ "bannerselect", bannerSelectCmd, HEADER);
  },
  memberDelete: async function(id, pwd) {
    const memberDeleteCmd = {
      id : id,
      pwd : pwd
    }
    return await axios.post(BASE_URL+ "memberdelete", memberDeleteCmd, HEADER);
  },
  //로그아웃
  logout: async function() {
    const logoutCmd = {
      cmd: "logout"
    }
    return await axios.post(BASE_URL + "logout", logoutCmd, HEADER);
  },

  //로그인
  loginData: async function(loginId, loginPwd) {
    const loginCheck = {
      loginId: loginId,
      loginPwd: loginPwd
    }
    return await axios.post(BASE_URL + "login", loginCheck, HEADER);
  },

  //회원가입
  memberReg: async function(regId, regPwd, regNick, regName, regEmail, birthDate, regPhone, isAdOk, isActive) {
    const regCheck = {
      regId : regId,
      regPwd : regPwd,
      regNick : regNick,
      regName : regName,
      regEmail : regEmail,
      birthDate : birthDate,
      regPhone : regPhone,
      isAdOk : isAdOk,
      isActive : isActive
    }
    return await axios.post(BASE_URL + "memberinsert", regCheck, HEADER);
  },

  //가입축하이메일
  celMailSend: async function(regEmail) {
    const celMail = {
      regEmail: regEmail
    }
    return await axios.post(BASE_URL + "sendcelmail", celMail, HEADER);
  },

  //아이디 중복체크
  memberIdDup: async function(regId) {
    const regIdDup = {
      regId : regId
    }
    return await axios.post(BASE_URL + "iddup", regIdDup, HEADER);
  },

  //닉네임 중복체크
  memberNickDup: async function(regNick) {
    const regNickDup = {
      regNick: regNick
    }
    return await axios.post(BASE_URL + "nickdup", regNickDup, HEADER);
  },

  //아이디 찾기
  memberfindId: async function(findIdName, findIdEmail) {
    const findId = {
      findIdName: findIdName,
      findIdEmail: findIdEmail
    }
    return await axios.post(BASE_URL + "findid", findId, HEADER);
  },

  //이메일로 회원정보 가져오기
  memberinfoFindId: async function(findIdEmail) {
    const finIdMember = {
      findIdEmail: findIdEmail
    }
    return await axios.post(BASE_URL + "findidmember", finIdMember, HEADER);
  },

  //비밀번호 찾기
  idEmailCk: async function(findPwdId, findPwdEmail) {
    const findPwd = {
      findPwdId: findPwdId,
      findPwdEmail: findPwdEmail
    }
    return await axios.post(BASE_URL + "findpwd", findPwd, HEADER);
  },

  //이메일 인증번호 전송
  verifyCodeEmailSend: async function(findPwdEmail) {
    const findPwdCodeSend = {
      findPwdEmail: findPwdEmail
    }
    return await axios.post(BASE_URL + "findpwdverify", findPwdCodeSend, HEADER);
  },

  //현재 비밀번호 맞는지 체크
  currentPwd: async function(memberNum, inputPwd1) {
    const currentPwdData = {
      memberNum: memberNum,
      inputPwd1: inputPwd1
    }
    return await axios.post(BASE_URL + "currentPwd", currentPwdData, HEADER);
  },

  //비밀번호 재설정
  resetPwdData: async function(findPwdId, resetPwd) {
    const resetPwdData = {
      findPwdId: findPwdId,
      resetPwd: resetPwd
    }
    return await axios.post(BASE_URL + "resetpwd", resetPwdData, HEADER);
  },

  //휴대폰번호 인증
  memberPhoneReg: async function(regPhone) {
    const regPhoneCk = {
      regPhone: regPhone
    }
    return await axios.post(BASE_URL + "phoneverify", regPhoneCk, HEADER);
  },

  //현재 닉네임 가져오기
  memberInfoNewNick: async function(memberNum) {
    const memberNick = {
      memberNum: memberNum
    }
    return await axios.post(BASE_URL + "memberNick", memberNick, HEADER);
  },

  //닉네임 변경하기
  infoNewNickOk: async function(memberNum, infoNewNickInput) {
    const infoNewNick = {
      memberNum: memberNum,
      infoNewNickInput: infoNewNickInput
    }
    return await axios.post(BASE_URL + "infoNewNick", infoNewNick, HEADER); 
  },

  //회원정보수정 - 비밀번호 변경
  infoResetPwd: async function(memberNum, inputPwd2) {
    const infoResetPwd = {
      memberNum: memberNum,
      inputPwd2: inputPwd2
    }
    return await axios.post(BASE_URL + "infoResetPwd", infoResetPwd, HEADER); 
  },

  //회원정보수정 - 이메일 변경
  infoResetEmail: async function(memberNum, inputEmail) {
    const infoResetEmail = {
      memberNum: memberNum,
      inputEmail: inputEmail
    }
    return await axios.post(BASE_URL + "infoResetEmail", infoResetEmail, HEADER); 
  },
  
  //회원정보수정 - 광고수신여부 변경
  infoIsAd: async function(memberNum, infoAd) {
    const infoResetAd = {
      memberNum: memberNum,
      infoAd: infoAd
    }
    return await axios.post(BASE_URL + "infoResetAd", infoResetAd, HEADER); 
  },

  //회원정보수정 - 프로필사진 변경
  pfImgChange: async function(memberNum, infoPfImgUrl) {
    const infoImgChange = {
      memberNum: memberNum,
      infoPfImgUrl: infoPfImgUrl
    }
    return await axios.post(BASE_URL + "infoImgChange", infoImgChange, HEADER); 
  },

  //회원정보수정 - 주최자소개내용 변경
  infoIntroduce: async function(memberNum, inputIntroduce) {
    const newIntroduce = {
      memberNum: memberNum,
      inputIntroduce: inputIntroduce
    }
    return await axios.post(BASE_URL + "newIntroduce", newIntroduce, HEADER); 
  },


  // 채팅 리스트 가져오기
  chatList: async function(chatListContent){
    const chatList = {

    }
    return await axios.post(BASE_URL + "chatListSelect", chatList, HEADER);
  },
  
  // 채팅 내용 가져오기
  chatContent: async function(partner_num, memberNum){
    const chatContent = {
      partner_num: partner_num,
      memberNum : memberNum
    }
    return await axios.post(BASE_URL + "chatContentSelect", chatContent, HEADER);
  },
  // // 웹소켓
  // // 채팅방 개설 API
  // chatRoomOpen: async function(name) {
  //   const chatObject = {
  //       "name" : name,

  //   }
  //   return await axios.post(BASE_URL + "chat", chatObject, HEADER);
  // }, 
  chatRoomOpen: async function(partnerNum) {
    const chatObject = {
      partnerNum : partnerNum

    }
    return await axios.post(BASE_URL + "chat", chatObject, HEADER);
  }, 
  chatContentInsert : async function(partner_num, inputMsg, msgType, memberNum) {
    const chatObject = {
      partner_num : partner_num,
      inputMsg :inputMsg,
      msgType : msgType,
      memberNum : memberNum

    }
    return await axios.post(BASE_URL + "chatInsert", chatObject, HEADER);
  }, 
  accountsend : async function(partner_num, bank, account, accountholder) {
    const chatObject = {
      partner_num : partner_num,
      bank :bank,
      account : account,
      accountholder : accountholder

    }
    return await axios.post(BASE_URL + "accountsend", chatObject, HEADER);
  }, 
  deliverysend : async function(partner_num, deliveryCompany, deliveryNum) {
    const chatObject = {
      partner_num : partner_num,
      deliveryCompany :deliveryCompany,
      deliveryNum : deliveryNum

    }
    return await axios.post(BASE_URL + "deliverysend", chatObject, HEADER);
  }, 
  deliveryaddrsend : async function(partner_num, deliveryAddress, deliveryName, deliveryPhone) {
    const chatObject = {
      partner_num : partner_num,
      deliveryAddress :deliveryAddress,
      deliveryName : deliveryName,
      deliveryPhone : deliveryPhone

    }
    return await axios.post(BASE_URL + "deliveryaddrsend", chatObject, HEADER);
  }, 
  chatDeliAddr : async function(partner_num) {
    const chatObject = {
      partner_num : partner_num

    }
    return await axios.post(BASE_URL + "chatDeliSelect", chatObject, HEADER);
  }, 
  chatAccount : async function(partner_num) {
    const chatObject = {
      partner_num : partner_num

    }
    return await axios.post(BASE_URL + "chatAccountSelect", chatObject, HEADER);
  }, 
  chatDeliveryNum : async function(partner_num) {
    const chatObject = {
      partner_num : partner_num

    }
    return await axios.post(BASE_URL + "chatDeliNumSelect", chatObject, HEADER);
  }, 
  chatPartnerAccept : async function(target, partner) {
    const chatObject = {
      target : target,
      partner : partner

    }
    return await axios.post(BASE_URL + "partneraccept", chatObject, HEADER);
  }, 
  chatPartnerRejecthost : async function(target, partner) {
    const chatObject = {
      target : target,
      partner : partner

    }
    return await axios.post(BASE_URL + "partnerdeletehost", chatObject, HEADER);
  }, 
  chatPartnerReject : async function(target) {
    const chatObject = {
      target : target
  

    }
    return await axios.post(BASE_URL + "partnerdelete", chatObject, HEADER);
  }, 




}

export default api;