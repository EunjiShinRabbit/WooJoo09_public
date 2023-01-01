import firebase from "firebase/compat/app";
import "firebase/compat/storage";
// import { initializeApp, getApp, getApps } from "firebase/app";
// Import the functions you need from the SDKs you need
// import { getAnalytics } from "firebase/analytics";
// import { getStorage } from "firebase/storage";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries
// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: /* 자신의 api 키 */,
  authDomain: /* 자신의 auth 도메인 */,
  projectId: /* 자신의 프로젝트 Id */,
  storageBucket: /* 자신의 스토리지 버킷 */,
  messagingSenderId: /* 자신의 Sender Id */,
  appId: /* 자신의 앱 Id */,
  measurementId: /* 자신의 Measurement Id */
};
// Initialize Firebase
// const app = initializeApp(firebaseConfig);

firebase.initializeApp(firebaseConfig);

var storage_obj = firebase.storage();

export default firebase;
export const storage = storage_obj;