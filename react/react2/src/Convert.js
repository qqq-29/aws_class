import { useState } from "react";

//분을 입력받으면 초로 변환하는 컴포너트
//숫자를 입력하면 입력한 숫자에 해당하는 초가 바로 출력되도록 구현
function Convert(){

	let [min, setMin]= useState(0)

	const change=(e)=>{
		setMin(e.target.value)
	}

	return(
		<div>
			<div>
				<input type="number" onChange={change}/>
			</div>
			<input type="number" disabled={true} value={min*60}/>
		</div>
	)
}

export default Convert;