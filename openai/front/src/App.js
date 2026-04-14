
import {useEffect, useState} from "react";
import Main from './Main';
import Ask from './Ask';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main/>} />
        <Route path="/ask" element={<Ask/>} />
      </Routes>
    </BrowserRouter>
  );

}

export default App;
