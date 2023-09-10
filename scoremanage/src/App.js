import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import Home from "./components/Home";
import Login from "./components/Login";
import React, { createContext, useReducer } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import "./App.css"
import Register from "./components/Register";
import HandleClass from "./components/HandleClass";
import ListScore from "./components/ListScore";
import ListStudent from "./components/ListStudent";
import MyUserReducer from "./reducers/MyUserReducer";
import cookie from "react-cookies";
import SchoolYear from "./components/SchoolYear";
import { SchoolYearProvider } from './reducers/SchoolYearContext';
export const MyUserContext = createContext();


const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);
  return (<>
    <MyUserContext.Provider value={[user, dispatch]}>
    <SchoolYearProvider>
      <BrowserRouter>
        <Header className="header" />
        <Routes className="routes">
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<div style={{ margin: '100px' }}><Login /></div>} />
          <Route path="/register" element={<div style={{ margin: '50px 200px 50px 200px' }}><Register /></div>} />
          <Route path="/listclass" element={<div style={{ margin: '50px 150px' }}><HandleClass /></div>} />
          <Route path="/listscore" element={<div style={{ margin: '50px 150px' }}><ListScore /></div>} />
          <Route path="/liststudent" element={<div style={{ margin: '50px 150px' }}><ListStudent /></div>} />
          <Route path="/schoolyear" element={<div style={{ margin: '50px 150px' }}><SchoolYear /></div>} />
        </Routes>
        <Footer className="footer" />
      </BrowserRouter>
      </SchoolYearProvider>
    </MyUserContext.Provider>
  </>)
}

export default App;
