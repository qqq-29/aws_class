
import {useEffect, useState} from "react";
import Main from './Main';
import ChatBot from "./ChatBot";
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {
  async function test(msg){

    const response = await fetch(`/api/v1/test?msg=${msg}`)
    
    if(!response.ok){
      console.log('연결 실패')
      return;
    }

    const res = await response.json();
    console.log(res.msg);

  }
  test('hi')

  async function testPost(msg){
		    const response = await fetch(`/api/v1/test`,{
          method : 'post',
          headers : {
            'Content-type' : 'application/json'
          },
          body : JSON.stringify({'msg' : msg})
        });

		    if(!response.ok){
			    console.log('연결실패');
          return;
		    }
        const res = await response.json();
			  console.log(res.msg);
	    } 
	    testPost('hi');
    
  // async function posttest() {
  //   const data = {msg:"hi"}
  //   const response = await fetch('')

  // }
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main/>} />
        <Route path="/chat" element={<ChatBot/>} />
      </Routes>
    </BrowserRouter>
  );

}

export default App;
