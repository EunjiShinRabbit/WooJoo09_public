import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import UserInfoContext from "./stores/UserInfoContext"

ReactDOM.render(
  <React.StrictMode>
    {/* UserInfoContext가 App Component를 감싸준다. */}
    <UserInfoContext>
      <App />
    </UserInfoContext>
  </React.StrictMode>,
  document.getElementById('root')
);