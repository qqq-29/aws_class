import { useState } from "react";

/* 
쓰기 버튼을 클릭하면 읽기 버튼으로 변경되면서 input태그에 입력할 수 있도록 변경되고,
읽기 버튼을 클릭하면 쓰기 버튼으로 변경되면서 input태그에 입력할 수 없도록 변경
*/
function ReadOnly(){
	const [isRead, setIsRead]=useState(true)
	const [text, setText]=useState("쓰기모드로")

	const click=(e)=>{
		setText(isRead ? "읽기모드로" : "쓰기모드로");
		setIsRead(!isRead);
	}

	const btnClick2 = () => setIsRead(!isRead);

	return (
		<div>
			<input type="text" readOnly={isRead} />
			<button onClick={click}>{text}</button>
			<button onClick={btnClick2}>{isRead ? "쓰기모드로" : "읽기모드로"}</button>
		</div>
	)
}

export default ReadOnly;