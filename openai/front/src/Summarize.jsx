import { Link } from 'react-router-dom';
import React, { useState } from 'react';

function Summarize(){
	const [result, setResult] = useState()
	const [form,setForm] = useState({text:'', target_lan:'Korean', max_sentence:'3'}) 

	const formSubmit = (e)=>{
				e.preventDefault()
				
				if(form.text === ''){
					alert("내용을 입력하세요.")
					return
				}

				// setIsLoading(true)
				sendData3('/api/v1/ai/summarize', form, 'json')
		
			}
		
	async function sendData3(url, params, type, callbackFunc){
		const queryString = new URLSearchParams(params).toString()
		const response = await fetch(`${url}?${queryString}`,{
			method: 'POST',
			headers: {'Content-Type': 'application/json',},
      body: JSON.stringify(params) 
    });
		if(!response.ok){
					return
				}
				
				const result = type === 'json' ? await response.json() : await response.text()
				if(callbackFunc)
					callbackFunc(result)
	}

	const callbackFunc = (res)=>{
		console.log(res)
			setResult(res.answer)
			// setIsLoading(false)
		}

	const inputChange = (e)=>{
		const {name, value} = e.target
		setForm({...form, [name] : value})
		console.log(form)
	}

	return(
		<div>
				<Link to = "/list">뒤로가기</Link>
				<h1>번역 ai테스트</h1>
				<form style={{display:'flex'}} onSubmit={formSubmit}>
					<div style={{display:'flex'}}>
					<label style={{width:'100px'}}>번역할 분장</label>
					<textarea name="text" rows={5} cols={30} 
						style={{width:'100%'}}
						onChange={inputChange}
						// readOnly={isLoading}
						value={form.text}>
					</textarea>
					<label style={{width:'100px'}}>언어</label>
						<input name="target_lan" onChange={inputChange} value={form.target_lan}/>
					<label style={{width:'100px'}}>문장개수</label>
						<input name="max_sentence" onChange={inputChange} value={form.max_sentence}/>
					<div style={{width:'100%', border:"1px solid black"}}>
						{/* {
							isLoading ? "[[번역중입니다. 잠시만 기다려주세요......]]" : result
						} */}
						{result}
					</div>
					</div>
					{/* <button disabled={isLoading}>번역</button> */}
					<button>번역</button>
				</form>			
			</div>
	)

}

export default Summarize