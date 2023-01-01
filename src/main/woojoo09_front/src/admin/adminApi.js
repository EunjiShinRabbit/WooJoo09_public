import axios from "axios";
const HEADER = {"Content-type" : "application/json"}
// const BASE_URL = "http://localhost:9009/developerkirby/";
const BASE_URL = "/developerkirby/";

axios.defaults.withCredentials = true; // withCredentials 전역 설정

const api = {
  adminMemberSelect: async function() {
    const adminMemberSelectCmd = {
      cmd: "adminMemberSelect"
    }
    return await axios.post(BASE_URL+ "adminmemberselect", adminMemberSelectCmd, HEADER);
  },  
  adminMemberSearch: async function(target) {
    const adminMemberSearchCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "adminmembersearch", adminMemberSearchCmd, HEADER);
  },  
  adminMemberDelete: async function(target) {
    const adminMemberDeleteCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "adminmemberdelete", adminMemberDeleteCmd, HEADER);
  },  
  adminWriteSelect: async function() {
    const adminWriteSelectCmd = {
      cmd: "adminWriteSelect"
    }
    return await axios.post(BASE_URL+ "adminwriteselect", adminWriteSelectCmd, HEADER);
  }, 
  adminWriteSearch: async function(target) {
    const adminWriteSearchCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "adminwritesearch", adminWriteSearchCmd, HEADER);
  },   
  adminWriteDelete: async function(target) {
    const adminWriteDeleteCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "adminwritedelete", adminWriteDeleteCmd, HEADER);
  },  
  adminChatSelect: async function() {
    const adminChatSelectCmd = {
      cmd: "adminChatSelect"
    }
    return await axios.post(BASE_URL+ "adminchatselect", adminChatSelectCmd, HEADER);
  },  
  adminChatSearch: async function(target) {
    const adminChatSearchCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "adminchatsearch", adminChatSearchCmd, HEADER);
  },  
  adminChatSelectDetail: async function(target) {
    const adminChatSelectDetailCmd = {
      target: target
    }
    return await axios.post(BASE_URL+ "adminchatselectdetail", adminChatSelectDetailCmd, HEADER);
  },  
  adminNotiSend: async function(mail, title, content) {
    const adminNotiSendCmd = {
      mail :mail,
      title : title,
      content: content
    }
    return await axios.post(BASE_URL+ "adminnotisend", adminNotiSendCmd, HEADER);
  },  
  bannerSelect: async function() {
    const bannerSelectCmd = {
      cmd: "bannerSelect"
    }
    return await axios.post(BASE_URL+ "adminbannerselect", bannerSelectCmd, HEADER);
  },   
  bannerInsert: async function(bannerName, directUrl, imgUrl, isActive) {
    const bannerInsertCmd = {
      bannerName: bannerName,
      directUrl : directUrl,
      imgUrl : imgUrl,
      isActive : isActive
    }
    return await axios.post(BASE_URL+ "bannerinsert", bannerInsertCmd, HEADER);
  },  
  bannerUpdate: async function(bannerNum, bannerName, directUrl, imgUrl, isActive) {
    const bannerUpdateCmd = {
      bannerNum : bannerNum,
      bannerName: bannerName,
      directUrl : directUrl,
      imgUrl : imgUrl,
      isActive : isActive
    }
    return await axios.post(BASE_URL+ "bannerupdate", bannerUpdateCmd, HEADER);
  },  
  bannerDelete: async function(bannerNum) {
    const bannerDeleteCmd = {
      bannerNum: bannerNum
    }
    return await axios.post(BASE_URL+ "bannerdelete", bannerDeleteCmd, HEADER);
  },  
};

export default api;