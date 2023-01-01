import React from "react";
import { useState } from "react";
import "../style/chat.scss"
import { storage } from "../api/firebase"
import {uuidv4} from "../util/util"
import imgIcon from "../resources/add-photo.png"

const SendPhoto =({partner_num, changeChatSendImg, onClickImgMsgSend, sendImgbutton}) => {

  const [chatImgUrl, setChatImgUrl] = useState("");
  const [chatImg, setChatImg] = useState(null);
  const [chatImgErr, setChatImgErr] = useState('');

  const handleImage = (event) => {
    setChatImgUrl("");
    const image = event.target.files[0];
    if (!image) {
      // console.log("파일이 선택되지 않았습니다");
      setChatImgErr("파일이 선택되지 않았습니다");
      setChatImg("");
      changeChatSendImg("");
      setChatImgUrl("");
      return;
    }
    setChatImg(image);
    // console.log(image);
    setChatImgErr("");
  };

  const onSubmit = (event) => {
    event.preventDefault();
    setChatImgErr("");
    if (chatImg === "") {
      // console.log("파일이 선택되지 않았습니다");
      setChatImgErr("파일이 선택되지 않았습니다");
      return;
    }
    // 업로드 처리
    // console.log("업로드 처리");
    const storageRef = storage.ref("woojoo09/chatImg/"); //어떤 폴더 아래에 넣을지 설정
    const imgName = (partner_num + "chatImg" + uuidv4());
    // const imagesRef = storageRef.child(imgName);
    const imagesRef = storageRef.child(imgName);
    // const imagesRef = storageRef.child(image.name); //파일명

    // console.log("파일을 업로드하는 행위");
    const upLoadTask = imagesRef.put(chatImg);
    // console.log("태스크 실행 전");

    upLoadTask.on(
      "state_changed",
      (snapshot) => {
        // console.log("snapshot", snapshot);
        const percent = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
        // console.log(percent + "% done");
      },
      (error) => {
        console.log("err", error);
        setChatImgErr("파일 업로드에 실패했습니다." + error);
      },
      () => {
        upLoadTask.snapshot.ref.getDownloadURL().then((downloadURL) => {
          // console.log("File available at", downloadURL);
          setChatImgUrl(downloadURL);
          changeChatSendImg(downloadURL);
        });
      }
    );
    setChatImg('');
    changeChatSendImg('');
  };

  return (
    <div className="chatSendFile">
      <div>
          <form onSubmit={onSubmit}>
            <label>
            <img src={imgIcon} alt="대표이미지등록"/>
            <input type="file" accept="image/*" onChange={handleImage} />
            </label>
            {chatImg && <p className='imglst'>{chatImg.name}</p>}
            {chatImg && <button onClick={onSubmit}>선택 이미지 등록</button>}
          </form>
          {chatImgErr && <p className='imgErr'>{chatImgErr}</p>}
          {chatImgUrl && (
            <div>
              <p className="chatSendImgPre">이미지 미리보기</p>
              <div>
              <img src={chatImgUrl} alt="uploaded" />
              </div>
            </div>
          )}
      </div>
      
      <button onClick={ (e) => {onClickImgMsgSend(e); sendImgbutton(e);}} >전송</button>
    </div>
  )
}
export default SendPhoto