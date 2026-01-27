import { useState } from "react";

function ToDoList(){
	//할일을 저장할 state 변수를 선언. 문자열  todo
	const [toDo, setToDo] = useState("");
	//할일 목록을 저장할 state 변수를 선언. 객체 배열  todos
	//id : 번호, todo : 할일
	const [toDos, setToDos] = useState([
		{id : 1, todo : '할 일1'},
		{id : 2, todo : '할 일2'}
	])
	//번호를 저장할 변수를 선언
	let [num, setNum] = useState(3);
	//할일을 업력하면 state 변수에 저장하는 함수 선언 및 이벤트 등록
	const inputChange=(e)=>{
		setToDo(e.target.value);
	}
	//등록을 클릭하면 입력된 할일을 할일 목록에 추가하는 함수를 선언 및 이벤트 등록
	const postInsert=(e)=>{
		e.preventDefault();
		const newToDo = { 
			id:num, 
			todo:toDo 
		}
		setNum(num + 1);
		setToDos([...toDos, newToDo])
		setToDo("");
	}
	//num : 삭제할 할일 id
	const delTodo = num =>{
		//할일 목록 중 id가 num와 같지 않은 목록만 가져옴
		const newToDos = toDos.filter(item=>{
			return item.id !== num;
		});
		//newToDos, newToDos2 뜻은 똑같아
		//const newToDos2 = toDos.filter(item=>item.id !==num);
		//위에서 가져온 목록으로 수정
		setToDos(newToDos);
	}

	return(
		<div>
			<form onSubmit={postInsert}>
				<input type="text" placeholder="할일을 입력하세요." onChange={inputChange} value={toDo}/>
				<button>등록</button>
			</form>
			<h1>할 일 목록</h1>
			<ul>
				{
					toDos.length === 0 ?
					<li>등록된 할일이 없습니다.</li> :
					toDos.map((item)=>{
						return(
								
									<li key={"todo"+item.id}>
										{item.todo}
										<button style={{fontSize:'20px'}} onClick={(e)=>delTodo(item.id)}>&times;</button>
									</li>
								)
							})
						}
			</ul>
		</div>
	)
}

export default ToDoList;