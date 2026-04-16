
import {useEffect, useState} from "react";
import Main from './Main';
import Ask from './Ask.jsx';
import List from './List';
import Translate from "./Translate";
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Adcopy from "./Adcopy";
import Summarize from "./Summarize.jsx";

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" exact element={<Main/>} />
        <Route path="/list"element={<List/>} />
        <Route path="/ask"element={<Ask/>} />
        <Route path="/translate"element={<Translate/>}/>
        <Route path="/ad-copy"element={<Adcopy/>}/>
        <Route path="/summarize"element={<Summarize/>}/>
      </Routes>
    </BrowserRouter>
  );

}

export default App;
