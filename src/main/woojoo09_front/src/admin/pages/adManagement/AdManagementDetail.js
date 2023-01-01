import { useState, useEffect } from "react";
import api from "../../adminApi";
import Loader from "../../components/Loader";
import TopBar from "../../components/TopBar";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { storage } from "../../../api/firebase"
import Header from "../../components/Header";
import Sidebar from "../../components/SideBar";

const AdManagementDetail = () =>{
  const location = useLocation();
  const navigate = useNavigate();

  const [loading, setLoading] = useState(false);
  const [bannerNum, setBannerNum] = useState(location.state.bannerNum);
  const [bannerName, setBannerName] = useState(location.state.bannerName);
  const [directUrl, setDirectUrl] = useState(location.state.directUrl);
  const [imgUrl, setImgUrl] = useState(location.state.imgUrl);
  const [active,setActive] = useState(location.state.isActive);

  const [image, setImage] = useState("");
  const [error, setError] = useState("");


  const onChangeAd_name = (e) => setBannerName(e.target.value);
  const onChangeAd_url = (e) => setDirectUrl(e.target.value);


  if(loading) {
    return <div className="center"><Loader/></div>
  }

  const adminAdAdd = async () =>{

    const fetchAdUpdateData = async () => {
      setLoading(true);
       try {
         const response = await api.bannerUpdate(bannerNum, bannerName, directUrl, imgUrl, active);
         if(response.data.bannerUpdate === "OK"){
          navigate('/ilovekirby/adManagement')
         }
       } catch (e) {
         console.log(e);
       }
       setLoading(false);
     };
     fetchAdUpdateData();
     window.location.reload();
  }
  

  const handleImage = (event) => {
    const image = event.target.files[0];
    setImage(image);
    // console.log(image);
    setError("");
  };

  const onSubmit = (event) => {
    event.preventDefault();
    setError("");
    if (image === "") {
      // console.log("파일이 선택되지 않았습니다");
      setError("파일이 선택되지 않았습니다");
      return;
    }
    // 업로드 처리
    // console.log("업로드 처리");
    const storageRef = storage.ref("woojoo09/advertisement/"); //어떤 폴더 아래에 넣을지 설정
    const imgName = (bannerName + "Img");
    const imagesRef = storageRef.child(imgName);
    // const imagesRef = storageRef.child(image.name); //파일명

    // console.log("파일을 업로드하는 행위");
    const upLoadTask = imagesRef.put(image);
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
        setError("파일 업로드에 실패했습니다." + error);
      },
      () => {
        upLoadTask.snapshot.ref.getDownloadURL().then((downloadURL) => {
          // console.log("File available at", downloadURL);
          setImgUrl(downloadURL)
        });
      }
    );
  };

  const handleClickRadioButton = (e) => {
    setActive(e.target.value);
  }

  return(
    <div className="adminWrapper">
    <Header/>
    <Sidebar/>
    <div className="center">
      <TopBar name="배너 추가" high1="배너 관리" high2="광고 관리"/>
      <div>
      <label>
          <span>배너 이름</span>
          <input type="text" value={bannerName} onChange={onChangeAd_name}/>
        </label>
        <label>
          <span>배너 이동 URL</span>
          <input type="text" value={directUrl} onChange={onChangeAd_url}/>
        </label>
        <label>
          <span>배너 이미지</span>
          {error && {error}}
          <form className="adImgForm" onSubmit={onSubmit}>
            <input type="file" onChange={handleImage} />
            <button onClick={onSubmit}>업로드</button>
          </form>
          {imgUrl && (
            <div>
              <p>이미지 미리보기</p>
              <img className="adImgPreview" src={imgUrl} alt="uploaded"/>
            </div>
          )}
        </label>
        <br/>
        <label>
          <span>활성화 상태</span>
          <div>
          <label><input type="radio" name="active" onChange={handleClickRadioButton} 
          value="active" checked={active === "ACTIVE"}/>활성화</label>
          <label><input type="radio" name="active" onChange={handleClickRadioButton} 
          value="inactive" checked={active === "INACTIVE"}/>비활성화</label>
          </div>
        </label>
        <button onClick={adminAdAdd}><Link to={"/ilovekirby/adManagement"}>수정하기</Link></button>
      </div>
    </div>
    </div>
  );
};
export default AdManagementDetail;

