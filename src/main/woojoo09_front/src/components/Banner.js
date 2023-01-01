import { useState, useEffect, useRef } from "react";
import { Link } from "react-router-dom";
import Loader from "./Loader";
import api from "../api/api";

const Banner = () =>{
  const [loading, setLoading] = useState(false);
  const [banner, setBanner] = useState();

  const [images, setImages] = useState([]);

  const [current, setCurrent] = useState(0);
  const [style, setStyle] = useState({
    marginLeft: `-${current}00%`
  });
  const [imgSize, setImgSize] = useState();

  const moveSlide = (i) => {
    let nextIndex = current + i;
    
    if (nextIndex < 0) nextIndex = imgSize - 1;
    else if (nextIndex >= imgSize) nextIndex = 0;

    setCurrent(nextIndex);
  };

  useEffect(() => {
      setStyle({ marginLeft: `-${current}00%` });
  }, [current]);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
        try {
          const response = await api.bannerSelect();
          // console.log(response.data);
          setBanner(response.data.banner);
          setImgSize(response.data.banner.length);
        } catch (e) {
          console.log(e);
        }
        setLoading(false);
      };
      fetchData();
  }, []);
  
  if(loading) {
    return (
      <div className={ (window.scrollY || document.documentElement.scrollTop) < 150 ? "category" : "category  changed"}
        style={{height:"10000000vh"}}>
        <Loader/>
      </div>
    )
  }

  return(
    <div className="banner">
      <div className="slide">
        <div className="btnLeft" onClick={() => { moveSlide(-1); }}>&lt;</div>
        <div className="window">
          <div className="flexbox" style={style}>
            {banner && banner.map((data, i) => (
              <a
                href={data.directUrl}
                key={i}
                className="img"
                style={{ backgroundImage: `url(${data.imgUrl})` }}
              ></a>
            ))}
          </div>
        </div>
        <div className="btnRight" onClick={() => { moveSlide(1); }}>&gt;</div>
        <div className="bannerPage">{current + 1} / {imgSize}</div>
      </div>
      {/* <div className="position">
        {images.current.map((x, i) => (
          <div
            key={i}
            className={i === current ? 'dot current' : 'dot'}
            ></div>
         ))}
      </div> */}
    </div>
  );
}
export default Banner