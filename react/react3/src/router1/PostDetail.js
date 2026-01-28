import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function PostDetail(){
	//url에 있는 게시글 번호를 가져옴 /post/detail/:num으로 처리했기 때문에
	//num으로 받아옴
	let {num} = useParams();
	let [post, setPost] = useState({});
	const navigator = useNavigate();

	useEffect(()=>{
		//비동기 통신으로 게시글을 가져오는 함수를 선언
		const getPost = async ()=>{
			console.log("게시글 가져오는 중...");

			try{
				const response = await fetch("/api/v1/posts/" + num);
				
				if(response.status === 200){
					const result = await response.json();
					setPost(result);
				}
			}
			catch(e){
				console.error(e);
			}
		}
		//함수를 호출
		getPost();
	}, []);
	
	//빈 객체이면(게시글을 못가져오면)
	//객체의 키(속성)들의 개수가 0이면 => 객체에 속성이 없으면
	if(Object.keys(post).length === 0){
		return (
			<div>
				<h1>등록되지 않거나 삭제된 게시글입니다.</h1>
			</div>
		)
	}

	const deletePost = ()=>{
		//삭제 확인 알림을 띄움
		const isDel = window.confirm("진짜삭제합니까?");
		//취소를 누르면 종료
		if(!isDel){
			alert("삭제취소했습니다.")
			return;
		}
		sendDelPost();
		}
		//확인을 누르면 삭제
		//비동기 통신으로 게시글을 삭제하는 함수를 선언
		const sendDelPost = async()=>{
			
			try{
				const response = await fetch("/api/v1/posts/"+ num, {
					method : "DELETE"
				});
				if(response.status === 200){
					const result = await response.json();
					if(result){
						
						alert("게시글을 삭제했습니다")
						navigator("/posts");
					}else{
						alert("게시글을 삭제하지 못했습니다")
					}
				}
			}catch(e){
				console.error(e);
			}
		}


	return (
		<div>
			<h1>게시글 상세</h1>
			<div>제목 : {post.title}</div>
			<div>작성자 : {post.writer}</div>
			<div>작성일 : {post.date}</div>
			<div>내용</div>
			<div>{post.content}</div>
			<button onClick={deletePost}>삭제</button>
		</div>
	)
}

export default PostDetail;