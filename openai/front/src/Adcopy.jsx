import { useState } from "react"
import { Link } from "react-router-dom"
import {sendData3} from "./Ai"


function Adcopy(){
	const [isLoading, setIsLoading] = useState(false)
	const [result, setResult] = useState()
	const [form,setForm] = useState({product:'', feature:'', target:'', temp:'', count:''}) 

	const formSubmit = (e)=>{
		e.preventDefault()
		if(form.product === '' && form.feature === '' && form.target === '' && form.temp === '' && form.count === ''){
			alert("입력해주세요")
			return
		}
		setIsLoading(true)
		sendData3("/api/v1/ai/adcopy", form, 'json', 함수)

		const 함수 = (res)=>{
			setResult(res.answer)
			setIsLoading(false)
		}

		
	}

	const inputChange = (e)=>{
		const {name, value} = e.target
		setForm({...form, [name] : value})
		console.log(form)
	}

	return(
			<div>
				<Link to = "/list">뒤로가기</Link>
				<h1>광고 ai테스트</h1>
				<form style={{display:'flex'}} onSubmit={formSubmit}>
					<input name="product" onChange={inputChange} value={form.product}/>
					<input name="feature" onChange={inputChange} value={form.feature}/>
					<input name="target" onChange={inputChange} value={form.target}/>
					<input name="temp" onChange={inputChange} value={form.temp}/>
					<input name="count" onChange={inputChange} value={form.count}/>
					<div style={{display:'flex'}}>
					<div style={{width:'100%', border:"1px solid black"}}>
						{
							isLoading ? "[[작성중입니다. 잠시만 기다려주세요......]]" : result
						}
						{result}
					</div>
					</div>
					<button disabled={isLoading}>작성</button>
				</form>			
			</div>
		)
}

export default Adcopy