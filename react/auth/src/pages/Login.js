import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../AuthContext";

export function Login() {
  const navigate = useNavigate();
  const [info,setInfo]=useState({id : "", pw : ""})

  const {setUser, getMe} = useAuth();

  const textChange=(e)=>{
    const{name,value} = e.target;
    setInfo({...info,[name] : value})
  }

  const loginSubmit=(e)=>{
    e.preventDefault();
    login(info);
  }

  const login=async(info)=>{
    const response = await fetch("/api/v1/auth/login",{
      method : "POST",
      headers : {
        "Content-Type" : "application/json"
      },
      body : JSON.stringify(info)
    })
    if(!response){
      alert("nonono")
      return;
    }
    const res = await response.json(info);
    if(res.accessToken){
      alert("로그인성공")
      localStorage.setItem("accessToken", res.accessToken);
      navigate("/");
      const me = await getMe();
      setUser(me);
    }else{
      alert("로그인실패")
    }
  }

  return (
    <div>
      <h1>로그인</h1>
      <form onSubmit={loginSubmit}>
      <div>
        <label>아이디</label>
      </div>
      <div>
        <input type="text" name="id" id="id" onChange={textChange}/>
      </div>
      <div>
        <label>비밀번호</label>
      </div>
      <div>
        <input type="password" name="pw" id="pw" onChange={textChange}/>
      </div>
      <button>로그인</button>
      </form>
    </div>
  );
}

export default Login;