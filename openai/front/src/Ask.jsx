import { Link } from 'react-router-dom';
import React, { useState } from 'react';
function AiAsk(){
	const [form, setForm] = useState({msg : ''})
	const inputChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };
	const inputSubmit = (e) =>{
		e.preventDefault();
		if (form.msg.trim()==""){
			alert("입력해주세요")
			return;
		}
		sendMsg()
	}
	async function sendMsg() {
		const response = await fetch('/api/v1/ask',{
			method : 'post',
			headers : {"Content-Type" : "application/json"},
			body : JSON.stringify(form)
		})
		const result = await response.text()
		console.log(result)
		
	}
	return(
		<div className='ai'>
			<Link to="/">메인으로 돌아가기</Link>
			<br></br>
			<form className='ai_ask' onSubmit={inputSubmit}>
				<input type='text' name ="msg" className="chat-input" value={form.msg} onChange={inputChange}/>
        <button type='submit' className='btn btn-chat-submit'>전송</button>
      </form>
		</div>
	)
}

export default AiAsk