import { Link } from 'react-router-dom';
import React, { useState } from 'react';
import {sendData} from "./Ai"
import Summarize from './Summarize';
function Ask(){

	const [form, setForm] = useState({prompt : '', endpoint : '/ask'})
	const [result, setResult] = useState('')
	const [summary, setSummary] = useState('')

	//이벤트 
	const formSubmit = (e)=>{
		e.preventDefault()

		if(form.prompt.trim() === ''){
			alert("내용을 입력하세요.")
			return
		}

		sendData('/api/v1/ai/ask', form, 'json', (datas)=>{
			setResult(datas.message)
			setForm({...form, prompt : ''})
			if(datas.summary){
				setSummary(datas.summary)
			}
		})

	}

	const inputChange = (e)=>{
		//모든 이벤트에는 name과 value가 있다.
		const {name, value} = e.target
		setForm({...form, [name] : value})
		// setForm({...form, name : value}) 쓰면 안됨
		console.log(form)
	}

	// function formSubmit(){
	// }
	
	return(
		<div>
			<h1>기본 ai테스트</h1>
			<form style={{display:'flex'}} onSubmit={formSubmit}>
				<select name='endpoint' onChange={inputChange}>
					<option value="/ask">일반 챗봇</option>
					<option value="/chatbot">문맥 챗봇</option>
					<option value="/summary-chatbot">요약 챗봇</option>
				</select>
				<textarea name="prompt" rows={5} cols={30} 
					onChange={inputChange}
					value={form.prompt}>
				</textarea>
				<button>전송</button>
			</form>
			<h1>결과 : [요약 : {Summarize}]</h1>
			<div style={{border:"1px solid black", minHeight : "200px"}}>{result}</div>
		</div>
	)

	// const [form, setForm] = useState({msg : ''})
	// const inputChange = (e) => {
  //   setForm({
  //     ...form,
  //     [e.target.name]: e.target.value
  //   });
  // };
	// const inputSubmit = (e) =>{
	// 	e.preventDefault();
	// 	if (form.msg.trim()==""){
	// 		alert("입력해주세요")
	// 		return;
	// 	}
	// 	sendMsg()
	// }
	// async function sendMsg() {
	// 	const response = await fetch('/api/v1/ask',{
	// 		method : 'post',
	// 		headers : {"Content-Type" : "application/json"},
	// 		body : JSON.stringify(form)
	// 	})
	// 	const result = await response.text()
	// 	console.log(result)
		
	// }
	// return(
	// 	<div className='ai'>
	// 		<Link to="/">메인으로 돌아가기</Link>
	// 		<br></br>
	// 		<form className='ai_ask' onSubmit={inputSubmit}>
	// 			<input type='text' name ="msg" className="chat-input" value={form.msg} onChange={inputChange}/>
  //       <button type='submit' className='btn btn-chat-submit'>전송</button>
  //     </form>
	// 	</div>
	// )
}

export default Ask