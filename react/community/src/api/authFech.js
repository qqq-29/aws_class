
//인증 정보를 header에 자동으로 추가하는 함수
export async function authFetch(url, otions = {}) {
	
	const token = localStorage.getItem("accessToken");

	const headers = {
		"Content-type" : "application/json",
		...(otions.headers || {})
	};
	//token이 있으면 
	if(token){
		headers.Authorization = `Bearer ${token}`
	}

	const response = await fetch(url, {
		...otions, 
		headers
	});
	if(response.status == 401){
		console.log("인증 만료 또는 로그인이 필요합니다.");
		//refresh Token 또는 로그아웃 처리 예정

	}
	return response;
}