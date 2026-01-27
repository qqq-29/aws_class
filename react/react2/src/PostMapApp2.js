import { useState } from "react";

/*
등록 버튼
*/
function MapApp2(){
	//게시글 등록할 때 게시글 번호
	let [num, setNum] = useState(2);
	let [posts, setPosts] = useState([
		{ num : 1, title : '제목입니다', content : '내용입니다.', writer : '홍길동'},
	]);
	let [inputTitle, setinputTitle] = useState("");
	let [inputWriter,setinputWriter] = useState("");
	let [inputContent,setinputContent] = useState("");

	const titleChange = (e)=>{
		setinputTitle(e.target.value);
	}
	const writerChange = (e)=>{
		setinputWriter(e.target.value);
	}
	const contentChange = (e)=>{
		setinputContent(e.target.value);
	}
	const postInsert = (e)=>{
		e.preventDefault();
		const obj ={
			num,
			title: inputTitle,
 			writer: inputWriter,
  		content: inputContent
		}
		setNum(num + 1);
		setPosts([...posts, obj]);
	}

	return(
		<div>
			<form onSubmit={postInsert}>
				<input type="text" placeholder="제목을 입력하세요." onChange={titleChange}/>
				<br/>
				<input type="text" placeholder="작성자를 입력하세요." onChange={writerChange}/>
				<br/>
				<textarea placeholder="내용을 입력하세요." onChange={contentChange}></textarea>
				<br/>
				<button>게시글 등록</button>
			</form>
			<h1>게시글 목록</h1>
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
					</tr>
				</thead>
				<tbody>
						{
							posts.map((item)=>{
								return(
									<tr key={"post"+item.num}>
										<th>{item.num}</th>
										<th>{item.title}</th>
										<th>{item.writer}</th>
									</tr>
								);
							})
						}
				</tbody>
			</table>
		</div>
	);
}

export default MapApp2;