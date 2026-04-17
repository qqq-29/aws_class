import { useState } from "react"
import { Link } from "react-router-dom"
import {sendData} from "./Ai"


function Adcopy(){
	const [isLoading, setIsLoading] = useState(false)
	const [result, setResult] = useState()
	const [form,setForm] = useState({product:'', feature:'', target:'전연령', temp:'0.2', count:'50'}) 

	const formSubmit = (e)=>{
		e.preventDefault()
		if(form.product === '' && form.feature === '' && form.target === '' && form.temp === '' && form.count === ''){
			alert("입력해주세요")
			return
		}
		setIsLoading(true)
		sendData("/api/v1/ai/ad-copy", form, 'json', 함수)
	}

	const 함수 = (res)=>{
		console.log(res)
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
				<h1>광고 ai테스트</h1>
				<form style={{display:'flex'}} onSubmit={formSubmit}>
					<div style={{display:'flex', margin:'10px 0'}}>
						<label style={{width:'100px'}}>제품명</label>
						<input name="product" onChange={inputChange} value={form.product}/>
					</div>
					<div style={{display:'flex', margin:'10px 0'}}>
						<label style={{width:'100px'}}>제품특징</label>
						<input name="feature" onChange={inputChange} value={form.feature}/>
					</div>
					<div style={{display:'flex', margin:'10px 0'}}>
						<label style={{width:'100px'}}>타겟</label>
					<select name="target" onChange={inputChange} value={form.target}>
						<option>전연령</option>
						<option>10대</option>
						<option>20대</option>
						<option>30대</option>
					</select>
					</div>
					<div style={{display:'flex', margin:'10px 0'}}>
						<label style={{width:'100px'}}>문구창의성</label>
						<span>일반</span>
						<input type="range" name="temp" min={0} max={1} step={0.1} onChange={inputChange} value={form.temp}/>
						<span>창의적</span>
					</div>
					<div style={{display:'flex', margin:'10px 0'}}>
						<label style={{width:'100px'}}>글자제한</label>
						<input name="count" onChange={inputChange} value={form.count}/>
					</div>
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