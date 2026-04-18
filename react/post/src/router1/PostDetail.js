import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function PostDetail() {
  let { num } = useParams();
  let [post, setPost] = useState({});
  const navigator = useNavigate();

  // 댓글 상태
  let [comments, setComments] = useState([]);
  let [page, setPage] = useState(1); // 기본 페이지 1

  useEffect(() => {
    // 게시글 가져오기
    const getPost = async () => {
      console.log("게시글 가져오는 중...");
      try {
        const response = await fetch("/api/v1/post/detail/" + num);
        if (response.status === 200) {
          const result = await response.json();
          setPost(result);
        }
      } catch (e) {
        console.error(e);
      }
    };
  }, [num]);

  return (
    <div>
      <h1>게시글 상세</h1>
      <div>제목 : {post.title}</div>
      <div>작성자 : {post.writer}</div>
      <div>작성일 : {post.date}</div>
      <div>내용</div>
      <div>{post.content}</div>
    </div>
  );
}

export default PostDetail;
