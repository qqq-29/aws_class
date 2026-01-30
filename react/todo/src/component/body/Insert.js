import { useState } from "react";


function Insert(){
	// 날짜와 할일을 입력받고, 등록 버튼을 클릭하면 할일 등록되도록 작성.`order`는 같은 날짜를 기준으로 할일 개수+1
	// 성공하면 입력된 할일을 비움

	const [date, setDate] = useState("");
	const [text, setText] = useState("");
	//const [order, setOrder] = useState()

	const dataChange = (e) =>{setDate(e.target.value)}
	const textChange = (e) =>{setText(e.target.value)}
	const submit = (e) =>{
	e.preventDefault();
	// let todo = {
	// 	date,
	// 	text,
		
	// }
	
	// Inserttodo(todo);
	Inserttodo();
}

	const Inserttodo = async()=>{
		try{
		const response = await fetch("/api/v1/todos",{
			method : "POST",
			headers : {
				"Content-Type" : "application/json"
			},
			body : JSON.stringify({
				date : date,
				text : text
			})
	});
	if(response.status === 200){
		const result = await response.json()
		if(result){
					alert("게시글을 등록했습니다.");
					//게시글 등록 후 /posts로 이동
					navigator("/posts");
				}else{
					alert("게시글을 등록하지 못했습니다.");
				}
	}
}catch(e){
	console.error(e);
}
}

	return(
		<div>
				<h1>할일 등록</h1>

				<form onSubmit={submit}>
					<div>
						<label htmlFor="date">날짜:</label>
						<input type="date" name="date" id="date" onChange={dataChange}></input>
					</div>
					<div>
						<label htmlFor="text">할일:</label>
						<input type="text" name="text" id="text" onChange={textChange}></input>
					</div>
					<button>등록</button>
				</form>
		</div>
	)
}

export default Insert;