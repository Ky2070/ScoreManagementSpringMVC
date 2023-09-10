
import { initializeApp } from "firebase/app";

import { getDatabase, ref, push, onValue } from "firebase/database";
const firebaseConfig = {
  apiKey: "AIzaSyDZxzFHgy7GEztcAFuQ_BLQW1qw8DLWeeU",
  authDomain: "testofnghia.firebaseapp.com",
  projectId: "testofnghia",
  storageBucket: "testofnghia.appspot.com",
  messagingSenderId: "1298977427",
  appId: "1:1298977427:web:bd61948f0b7c95864f7366",
  measurementId: "G-SLLG9C001T"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const database = getDatabase(app);
export { database, ref, push, onValue };