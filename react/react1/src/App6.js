/*
좋아하는 과일을 선택하면 h2태그 옆에 선택한 과일이 출력되도록 작성.
- 주의사항
	- 과일을 선택하세요.를 선택하면 선택된 과일이 없습니다가 출력
*/
import { useState } from "react";
function App6(){

	let [selectText, setSelectText] = useState("");
	let change = (e)=>{
		setSelectText(e.target.value)
	}	

	return(
		<div>
			<h1>좋아하는 과일은?</h1>
			<select onChange={change}>
				<option value="">과일을 선택하세요.</option>
				<option>사과</option>
				<option>바나나</option>
				<option>포도</option>
			</select>
			<h2>좋아하는 과일 : </h2>
			<h1>{selectText === "" ? "선택된 과일이 없습니다." : selectText}</h1>
		</div>
	)
}

export default App6;