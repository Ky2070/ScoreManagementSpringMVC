import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import Home from "./components/Home";
import Login from "./components/Login";
import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import "./App.css"
import Register from "./components/Register";
import HandleClass from "./components/HandleClass";
import ListScore from "./components/ListScore";
import ListStudent from "./components/ListStudent";
const App = () => {
  return (<>
    <BrowserRouter>
      <Header className="header" />
      <Routes className="routes">
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<div style={{ margin: '100px' }}><Login /></div>} />
        <Route path="/register" element={<div style={{ margin: '50px 200px 50px 200px' }}><Register /></div>} />
        <Route path="/listclass" element={<div style={{ margin: '50px 150px' }}><HandleClass /></div>} />
        <Route path="/listscore" element={<div style={{ margin: '50px 150px' }}><ListScore /></div>} />
        <Route path="/liststudent" element={<div style={{ margin: '50px 150px' }}><ListStudent /></div>} />
      </Routes>
      <Footer className="footer" />
    </BrowserRouter>

  </>)
}

export default App;
