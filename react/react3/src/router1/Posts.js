import { useEffect, useState} from "react";
import { Link } from "react-router-dom";

function Posts(){
	let [posts, setPosts] = useState([]);
	useEffect(()=>{
		console.log("게시글 로딩 중....")

		const getPosts = async ()=>{
			const response =await fetch("/api/v1/posts");

			if(response.ok){
				const result = await response.json();
				setPosts(result);
			}
		}

		getPosts();
	}, []);

	return(
		<div>
			<h1>게시글 목록</h1>
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					{
						posts.map(post=>{
							return(
								<tr key={"post" + post.num}>
									<td>{post.num}</td>
									<td>
										<Link to={"/posts/detail/" + post.num}>{post.title}</Link>
									</td>
									<td>{post.writer}</td>
									<td>{post.date}</td>
								</tr>
							)
						})
					}
				</tbody>
			</table>
		</div>
	)
}

export default Posts;