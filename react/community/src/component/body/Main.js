import { authFetch } from "../../api/authFech";

function Main(){

	//로그인 했으면 로그인했습니다로 뜨고, 아니면 안했습니다로 뜨도록 작업
	const fetchHandler = async ()=>{
		
		try{
			const response = await authFetch("/api/v1/auth/test");
			
			if(!response.ok){
				alert("로그인 인증 실패!");
				return;
			}
			const res = await response.json();
			if(res){
				alert("로그인 인증 확인!");
			}
		}catch(e){
			console.error(e);
		}
	}

	const fetchHandler2 = async ()=>{
		try{
			const response = await authFetch("/api/v1/auth/me");

			if(!response.ok){
				alert("로그인 인증 실패!");
				return;
			}
			const res = await response.json();
			console.log(res);
		}catch(e){
			console.error(e);
		}
	}
	return(
		<div>
			<h1>메인</h1>
			<button onClick={fetchHandler}>인증 테스트</button>
			<button onClick={fetchHandler2}>회원 정보 테스트</button>
		</div>
	)
}

export default Main;