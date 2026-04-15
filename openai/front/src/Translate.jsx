import { Link } from 'react-router-dom';
import React, { useState } from 'react';
import { sendData2 } from './Ai';
function Translate(){
		const [form, setForm] = useState({text : '', style : 'formal'})
		const [result, setResult] = useState('')
		//작업중인지 아닌지 확인하기 위한 변수
		const [isLoading, setIsLoading] = useState(false)

		const formSubmit = (e)=>{
			e.preventDefault()
			
			if(form.text === ''){
				alert("내용을 입력하세요.")
				return
			}

			if(form.style === ''){
				alert("내용을 입력하세요.")
				return
			}
			setIsLoading(true)
			sendData2('/api/v1/ai/translate', form, 'json', 번역콜백함수)
	
		}

		const 번역콜백함수 = (res) =>{
			setResult(res.answer)
			setIsLoading(false)
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
					<select name="style" onChange={inputChange}>
						<option value="formal">격식</option>
						<option value="casual">반말</option>
						<option value="business">비즈니스</option>
					</select>
					<div style={{display:'flex'}}>
					<textarea name="text" rows={5} cols={30} 
						style={{width:'100%'}}
						onChange={inputChange}
						readOnly={isLoading}
						value={form.text}>
					</textarea>
					<div style={{width:'100%', border:"1px solid black"}}>
						{
							isLoading ? "[[번역중입니다. 잠시만 기다려주세요......]]" : result
						}
						{result}
					</div>
					</div>
					<button disabled={isLoading}>번역</button>
				</form>			
			</div>
		)
}
export default Translate