import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import CategoryPage from "./pages/CategoryPage";
import CelebratePage from "./pages/CelebratePage";
import ChatListPage from "./pages/ChatListPage";
import ChatPage from "./pages/ChatPage";
import DetailPage from "./pages/DetailPage";
import FindIdPage from "./pages/FindIdPage";
import FindPwdPage from "./pages/FindPwdPage";
import MainPage from "./pages/MainPage";
import MemberPage from "./pages/MemberPage";
import RegisterPage from "./pages/RegisterPage";
import TermAgreePage from "./pages/TermAgreePage";
import WritePage from "./pages/WritePage";
import TermPage from "./pages/TermPage";
import LoginPage from "./pages/LoginPage"
import MyTradePage from "./pages/MyTradePage"
import SearchPage from "./pages/SearchPage"
import UpdatePage from "./pages/UpdatePage";
import PrivacyPolicyPage from "./pages/PrivacyPolicyPage";

import "./style/common.scss";
import "./style/detail.scss";
import "./style/write.scss";
import "./style/chat.scss";
import "./style/celebrate.scss";
import "./style/findid.scss";
import "./style/findidcomplete.scss";
import "./style/findpwd.scss";
import "./style/login.scss";
import "./style/register.scss";
import "./style/resetpwd.scss";
import "./style/termagree.scss";
import "./admin/admin.scss";

import MemberManagement from "./admin/pages/memberManagement/MemberManagement";
import WriteManagement from "./admin/pages/contentManagement/WriteManagement";
import WriteManagementSearch from "./admin/pages/contentManagement/WriteManagementSearch";
import ChatManagement from "./admin/pages/contentManagement/ChatManagement";
import AdManagement from "./admin/pages/adManagement/AdManagement";
import AdManagementAdd from "./admin/pages/adManagement/AdManagementAdd";
import AdManagementDetail from "./admin/pages/adManagement/AdManagementDetail";
import NotiSend from "./admin/pages/notiManagement/NotiSend";
import ChatManagementDetail from "./admin/pages/contentManagement/ChatManagementDetail";

function App() {
  return (
    <>
    <Router>
        <Routes>
          <Route path='/' element={<MainPage/>}/>
          <Route path='/main' element={<MainPage/>}/>
          <Route path='/login' element={<LoginPage/>}/>
          <Route path='/category/:categoryName' element={<CategoryPage/>}/>
          <Route path='/findid' element={<FindIdPage/>}/>
          {/* <Route path='/findidcomplete' element={<FindIdComplete/>}/> */}
          <Route path='/findpwd' element={<FindPwdPage/>}/>
          {/* <Route path='/resetpwd' element={<ResetPwd/>}/>
          <Route path='/resetpwdcomplete' element={<ResetPwdComplete />}/> */}
          <Route path='/termagree' element={<TermAgreePage/>}/>
          <Route path='/register' element={<RegisterPage/>}/>
          <Route path='/celebrate' element={<CelebratePage/>}/>
          <Route path='/detail/:tradeNum' element={<DetailPage/>}/>
          <Route path='/search/:target' element={<SearchPage/>}/>
          <Route path='/chatlist' element={<ChatListPage/>}/>
          <Route path='/chat/:partner_num' element={<ChatPage/>}/>
          <Route path='/member' element={<MemberPage/>}/>
          <Route path='/MyTrade' element={<MyTradePage/>}/>
          <Route path='/write' element={<WritePage/>}/>
          <Route path='/update' element={<UpdatePage/>}/>
          <Route path='/term' element={<TermPage/>}/>
          <Route path='/privacypolicy' element={<PrivacyPolicyPage/>}/>

          {/* 여기서부터 어드민 */}
          {/* {!isLogin && <Navigate to="/login" />} */}
          <Route path="/ilovekirby/member" element={<MemberManagement/>}/>
          <Route path="/ilovekirby/content/writeManagement" element={<WriteManagement/>}/>
          <Route path="/ilovekirby/content/writeManagement/search/:query" element={<WriteManagementSearch/>}/>
          <Route path="/ilovekirby/content/chatManagement" element={<ChatManagement/>}/>
          <Route path="/ilovekirby/content/chatManagementDetail/:partnerNum" element={<ChatManagementDetail/>}/>
          <Route path="/ilovekirby/adManagement" element={<AdManagement/>}/>
          <Route path="/ilovekirby/adManagement/AdManagementDetail/:banner_num" element = {<AdManagementDetail/>}/>
          <Route path="/ilovekirby/adManagement/AdManagementAdd" element = {<AdManagementAdd/>}/>
          <Route path="/ilovekirby/noti/send" element={<NotiSend/>}/>
          
        </Routes>
    </Router>
    </>
  );
}

export default App;
