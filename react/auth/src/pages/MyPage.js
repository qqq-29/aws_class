import { useState } from "react";
import { useAuth } from "../AuthContext";

export function MyPage() {
  const {setUser} = useAuth();
  const btnLogout=(e)=>{
    logout();
  }

  const logout =async()=>{
    //accessToken 삭제
    localStorage.removeItem("accessToken");
    try{
    const response = await fetch("/api/v1/auth/logout",{
      method : "POST",
    })

    if(!response.ok) return;
      alert("로그아웃 성공")
    //user를 null 변경
    setUser(null);
    }catch(e){
      console.error(e);
    }
  }

  return (
    <div>
      <h1>마이 페이지</h1>
      <button onClick={btnLogout}>로그아웃</button>
    </div>
  );
}

export default MyPage;