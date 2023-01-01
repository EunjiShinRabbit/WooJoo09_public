const TopBar = (props) =>{
  return(
    <>
    <div className="topBar">
      <div><span>{props.name}</span></div>
      <div>
        <span>{props.high2}</span>
        {props.high2 && <span>&nbsp;&gt;&nbsp;</span>}
        <span>{props.high1}</span>
        {props.high1 && <span>&nbsp;&gt;&nbsp;</span>}
        <span>{props.name}</span>
      </div>
    </div>
    <hr/>
    </>
  );
};
export default TopBar;