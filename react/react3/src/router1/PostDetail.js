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
        const response = await fetch("/api/v1/posts/" + num);
        if (response.status === 200) {
          const result = await response.json();
          setPost(result);
        }
      } catch (e) {
        console.error(e);
      }
    };

    // 댓글 가져오기
    const getComments = async (po_num, page) => {
      try {
        const url = `/api/v2/posts/${po_num}/comments?page=${page}`;
        const response = await fetch(url, { method: "GET" });
        if (response.ok) {
          const data = await response.json();
          console.log("댓글 데이터:", data); // 콘솔 출력
          setComments(data); // 상태로 저장해서 화면에 사용 가능
        } else {
          console.error("댓글 조회 실패:", response.status);
        }
      } catch (e) {
        console.error("댓글 조회 중 오류 발생:", e);
      }
    };

    getPost();
    getComments(num, page); // 페이지 1부터 댓글 가져오기
  }, [num, page]);

  // 게시글 없으면
  if (Object.keys(post).length === 0) {
    return (
      <div>
        <h1>등록되지 않거나 삭제된 게시글입니다.</h1>
      </div>
    );
  }

  const deletePost = () => {
    const isDel = window.confirm("진짜삭제합니까?");
    if (!isDel) {
      alert("삭제취소했습니다.");
      return;
    }
    sendDelPost();
  };

  const sendDelPost = async () => {
    try {
      const response = await fetch("/api/v1/posts/" + num, {
        method: "DELETE",
      });
      if (response.status === 200) {
        const result = await response.json();
        if (result) {
          alert("게시글을 삭제했습니다");
          navigator("/posts");
        } else {
          alert("게시글을 삭제하지 못했습니다");
        }
      }
    } catch (e) {
      console.error(e);
    }
  };

  return (
    <div>
      <h1>게시글 상세</h1>
      <div>제목 : {post.title}</div>
      <div>작성자 : {post.writer}</div>
      <div>작성일 : {post.date}</div>
      <div>내용</div>
      <div>{post.content}</div>
      <button onClick={deletePost}>삭제</button>
{/* 
      <h2>댓글 목록 (페이지 {page})</h2>
      <ul>
        {comments.length > 0
          ? comments.map((c, idx) => <li key={idx}>{c.content}</li>)
          : "댓글이 없습니다."}
      </ul>

      <button onClick={() => setPage(page - 1)} disabled={page <= 1}>
        이전
      </button>
      <button onClick={() => setPage(page + 1)}>다음</button> */}
    </div>
  );
}

export default PostDetail;
