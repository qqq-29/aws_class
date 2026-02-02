import { useState } from "react";


function Login(){

	let [info, setInfo] = useState({id : "", pw : ""})

	const changeHandler = e =>{
		const {name, value} = e.target;
		setInfo({...info, [name] : value});
	}
	const submitHandler = e =>{
		e.preventDefault();

		login(info);
	}

	const login = async (info)=>{
		try{
			const response = await fetch("/api/v1/auth/login", {
				method : "POST",
				headers : {
					"Content-Type" : "application/json",
				},
				body : JSON.stringify(info)
			});
			if(!response.ok){
				alert("로그인 실패!");
				return;
			}
			const res = await response.json();
			localStorage.setItem("accessToken", res.accessToken);
			alert("로그인 성공!");

		}catch(e){
			console.error(e);
		}
	}

	return(
		<div>
			<h1>로그인</h1>
			<form onSubmit={submitHandler}>
				<div>
					<label htmlFor="id">아이디 : </label>
					<input type="text" name="id" id="id" onChange={changeHandler}/>
				</div>
				<div>
					<label htmlFor="pw">비번 : </label>
					<input type="text" name="pw" id="pw" onChange={changeHandler}/>
				</div>
				<button>로그인</button>
			</form>
		</div>
	)
}

export default Login;