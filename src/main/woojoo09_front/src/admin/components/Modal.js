
const Modal = ({ setModalOpen, imgUrl, text}) => {
  // 모달 끄기 
  const closeModal = () => {
      setModalOpen(false);
  };

  if(imgUrl){
    return (
      <div className="modalcontainer">
        <button className="modalclose" onClick={closeModal}>
          X
        </button>
        <p>
          {imgUrl && <img className="modalimg" src={imgUrl} alt="이미지"/>}
          {text && <p>{text}</p>}
        </p>
      </div>
    );
  }
  
}
export default Modal;