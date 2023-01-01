import { useState } from "react";
const Pagination = (props) => {
  const numPages = Math.ceil(props.total / props.limit);
  const viewPages = (numPages > 10? 10 : numPages);
  return (
    <div className="pagination">
      <button 
        onClick={() => {
          props.setPage(props.page - 1); 
          props.setPageStart(Math.floor((props.page - 2) / 10));
          props.setCheckItems([]);
          }} 
        disabled={props.page === 1}>
        &lt;
      </button>
      {Array(viewPages)
        .fill()
        .map((_, i) => {
          if(props.pageStart * 10 + i + 1 <= numPages) {
            return(
              <button
                key={props.pageStart * 10 + i + 1}
                onClick={() => {
                  props.setPage(props.pageStart * 10 + i + 1);
                  props.setCheckItems([]);
                }}
                aria-current={props.page === props.pageStart * 10 + i + 1 ? "page" : null}
              >
                {props.pageStart * 10 + i + 1}
              </button>
            )
        }})}
      <button 
        onClick={() =>{
          props.setPage(props.page + 1); 
          props.setPageStart(Math.floor(props.page / 10));
          props.setCheckItems([]);}}
        disabled={props.page === numPages}
      >
        &gt;
      </button>
    </div>
  );
}

export default Pagination;
