import PulseLoader from "react-spinners/PulseLoader";
const Loader = () =>{
  return(
    <div className="loaderWrapper">
      <PulseLoader
        color="#8679D9"
        size={20}
        margin={10}
        speedMultiplier={.7}
      />
    </div>
  );
};

export default Loader;