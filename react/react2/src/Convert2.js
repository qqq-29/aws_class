import { useState } from "react";

//분을 입력받으면 초로 변환하는 컴포너트
//숫자를 입력하면 입력한 숫자에 해당하는 초가 바로 출력되도록 구현
function Convert2(){

	let [sec, setSec]= useState(0)

	return(
		<div>
			<div>
				<input type="number" 
					disabled={true} 
					value={sec/60} />
			</div>
			<input type="number" 
				disabled={false} 
				onChange={(e)=>setSec(e.target.value)} 
				value={sec}/>
		</div>
	)
}

export default Convert2;