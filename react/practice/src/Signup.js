import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; // 페이지 이동을 위한 도구

function Signup() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [text, setText] = useState('');
  const [gender, setGender] = useState(''); // 성별 상태
  const [email, setEmail] = useState('');
  const navigate = useNavigate(); // 페이지 이동 함수

  const handleSignup = async () => {

    if (password.length > 10) {
    alert("비밀번호는 10자 이하만 가능합니다.");
    return;
    }

    // 1. 이메일 형식 검사 (정규표현식)
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      alert("올바른 이메일 형식이 아닙니다! (예: test@naver.com)");
      return;
    }

    // 2. 성별 선택 확인
    if (!gender) {
      alert("성별을 선택해 주세요.");
      return;
    }
    try {
      await axios.post('http://localhost:8080/api/signup', {
        Username: username,
        Password: password,
        Text: text,
        Gender: gender,
        Email: email,
      });
      alert("회원가입 성공! 로그인 페이지로 이동합니다.");
      navigate('/login'); // 가입 성공하면 자동으로 로그인 페이지로 슝!
    } catch (error) {
      alert("가입 실패! 이미 있는 아이디일 수 있어요.");
    }
  };

  return (
    <div style={{ padding: '50px' }}>
      <h2>회원가입</h2>
      <input placeholder="아이디" onChange={(e) => setUsername(e.target.value)} /><br/><br/>
      <input type="password" placeholder="비밀번호" onChange={(e) => setPassword(e.target.value)} /><br/><br/>
      
      {/* 이메일 입력창 */}
      <input 
        type="email" 
        placeholder="이메일 (abc@test.com)" 
        onChange={(e) => setEmail(e.target.value)} 
      /><br/><br/>

      {/* 성별 체크박스 (하나만 선택되도록 처리) */}
      <div style={{ marginBottom: '20px' }}>
        <label>성별: </label>
        <input 
          type="checkbox" 
          checked={gender === '남성'} 
          onChange={() => setGender('남성')} 
        /> 남성
        <input 
          type="checkbox" 
          checked={gender === '여성'} 
          onChange={() => setGender('여성')} 
        /> 여성
      </div>

      <textarea placeholder="자기소개" onChange={(e) => setText(e.target.value)} /><br/><br/>
      
      <button onClick={handleSignup}>가입하기</button>
    </div>
  );
}

export default Signup;