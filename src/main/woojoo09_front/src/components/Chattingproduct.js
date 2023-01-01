import { Link } from "react-router-dom";
import fashion from "../resources/fashion_sample.png";
import "../style/chat.scss"

const chattingProduct =() => {
    return(
        <div className="chattingProduct">
          <div>
              <Link to="/write">
                <img src={fashion} alt="패션"/>
              </Link>
                <div>
                  <p className="chatProductName">상품이름</p>
                  <div className="chatPrice">가격</div>
                  <div>
                    <button>공구승인</button>
                    <button>공구거절</button>
                  </div>
                </div>
          </div>
        </div>
    );
}
export default chattingProduct;