import { useState } from "react";
import { useNavigate } from "react-router-dom";

export function Signup() {

  const navigate = useNavigate();

  	let [info, setInfo] = useState({id : "", pw : "", pw2 : "", email : ""})

  const changeHandler=(e)=>{
    //이벤트가 발생한 요소의 표준속성 name 과 value를 가져옴
    const {name, value} = e.target
    //{...info} : info 객체에 있는 속성과 값들을 복사해서 넣어줌
    //{...info, [name] : value} : info 객체에 있는 속성과 값들을 복사한 후
    //  name변수에 있는 값을 속성명으로, value 변수에 있는 값을 값으로 덮어쓰기
    setInfo({...info,[name] : value});
  }

  const mansubmit =(e)=>{
    e.preventDefault();

    signup(info)
  }

  const signup=async(info)=>{
    try{
      const response = await fetch("/api/v1/auth/signup",{
        method : "POST",
        headers : {
          "Content-Type": "application/json"
        },
        body : JSON.stringify(info)
      })
      if(!response){
        alert("가입실패")
      }
      const res = await response.json();
      if(res){
        alert("회원가입성공");
        navigate("/login");
      }else{
        alert("아이디 또는 이메일이 중복됬습");
      }
    }catch(e){
      console.error(e);
    }
  }

  return (

    <div>
      <h1>회원가입</h1>
      <form onSubmit={mansubmit}>
      <div>
        <label>아이디</label>
      </div>
      <div>
        <input type="text" name="id" id="id" onChange={changeHandler}/>
      </div>
      <br/>
      <div>
        <label>비밀번호</label>
      </div>
      <div>
        <input type="password" name="pw" id="pw" onChange={changeHandler}/>
      </div>
      <br/>
      <div>
        <label>비밀번호</label>
      </div>
      <div>
        <input type="password" name="pw2" id="pw2" onChange={changeHandler}/>
      </div>
      <br/>
      <div>
        <label>이메일</label>
      </div>
      <div>
      <input type="email" name="email" id="email" onChange={changeHandler}/>
      </div>
        <button>회원가입</button>
      </form>
    </div>
  );
}

export default Signup;