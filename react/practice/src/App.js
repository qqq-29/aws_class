//npm install axios

import React, { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false); // 로그인 상태 관리

  const handleProtectedFeature = () => {
  // 방법 1: isLoggedIn 변수로 확인하기
  if (!isLoggedIn) {
    alert("🛑 로그인이 필요한 기능입니다. 먼저 로그인해 주세요!");
    return; // 여기서 함수를 끝내버림 (기능 실행 안 됨)
  }

  // 로그인 된 경우에만 아래 코드가 실행됨
  alert("✅ 회원 인증 성공! 비밀 기능을 실행합니다.");
  // 여기에 진짜 하고 싶은 기능(AI 상담 열기 등)을 넣으세요.
};

  const fetchUserInfo = async () => {
  const token = localStorage.getItem('login_token'); // 저장된 토큰 꺼내기
  
  try {
    const response = await axios.get('http://localhost:8080/api/user/info', {
      headers: {
        Authorization: `Bearer ${token}` // 핵심: 헤더에 '나 토큰 가졌어!'라고 증명
      }
    });
    alert(`서버 응답: ${response.data.username}님, 환영합니다!`);
  } catch (error) {
    alert("인증에 실패했습니다. 다시 로그인하세요.");
  }
};

  // 페이지가 새로고침되어도 토큰이 있으면 로그인 유지
  useEffect(() => {
    const token = localStorage.getItem('login_token');
    if (token) {
      setIsLoggedIn(true);
    }
  }, []);

  const handleLogin = async () => {
    try {
      //axios.
      //status: 서버 응답 코드 (예: 200, 404)
      //headers: 서버가 보낸 헤더 정보
      //data: 서버가 진짜로 보내준 핵심 내용물 (JSON)
      //config: 요청 설정 정보
      const response = await axios.post('http://localhost:8080/api/login', {
        username: username,
        password: password
      });

      const token = response.data.token;
      
      // 1. 받은 토큰을 브라우저에 저장
      localStorage.setItem('login_token', token);
      // 2. 로그인 상태를 '참'으로 변경
      setIsLoggedIn(true);
      alert("로그인 성공!");
      fetchUserInfo();
    } catch (error) {
      alert("로그인 실패! 아이디와 비밀번호를 확인하세요.");
    }
  };

  const handleLogout = () => {
    // 로그아웃 시 토큰 삭제 및 상태 변경
    localStorage.removeItem('login_token');
    setIsLoggedIn(false);
  };

  return (
    <div style={{ padding: '50px', textAlign: 'center' }}>
      <h1>토큰 기반 로그인 연습</h1>

      {/* --- 1. 로그인을 했건 안 했건 무조건 보이는 버튼 영역 --- */}
      <div style={{ marginBottom: '20px', border: '1px solid #ccc', padding: '10px' }}>
        <button onClick={handleProtectedFeature}>회원 전용 기능 테스트</button>
        <button onClick={() => alert("누구나 누를 수 있는 버튼")}>일반 기능</button>
      </div>

      {isLoggedIn ? (
        // 로그인 성공 시 보여줄 화면
        <div>
          <h2>🎉 환영합니다! 로그인 상태입니다.</h2>
          <p>브라우저 LocalStorage에 토큰이 안전하게 저장되었습니다.</p>
          <button onClick={handleLogout} style={{ padding: '10px 20px' }}>로그아웃</button>
        </div>
      ) : (
        // 로그인 전 보여줄 화면
        <div>
          <input 
            type="text" 
            placeholder="ID (admin)" 
            onChange={(e) => setUsername(e.target.value)} 
          /><br/><br/>
          <input 
            type="password" 
            placeholder="PW (1234)" 
            onChange={(e) => setPassword(e.target.value)} 
          /><br/><br/>
          <button onClick={handleLogin} style={{ padding: '10px 20px' }}>로그인</button>
        </div>
      )}
    </div>
  );
}

export default App;




// 토큰:다른 화면에서 토큰을 꺼내 쓰는 코드 예시
// // 새로운 화면에서의 코드
// const fetchMyData = async () => {
//   // 1. 이미 주머니에 저장된 토큰을 꺼낸다 (로그인 함수 다시 부를 필요 없음!)
//   const token = localStorage.getItem('login_token');

//   if (!token) {
//     alert("로그인 정보가 없어요!");
//     return;
//   }

//   try {
//     // 2. 꺼낸 토큰을 헤더에 실어서 서버에 보낸다
//     const response = await axios.get('http://localhost:8080/api/some-data', {
//       headers: { Authorization: `Bearer ${token}` }
//     });
//     console.log("데이터 가져오기 성공!", response.data);
//   } catch (error) {
//     console.error("토큰이 만료되었거나 틀렸어요.");
//   }
// };


//////////////////////////////////////////////////////////////////////////////////////////////////

// localStorage 사용하나요? (사용법)
// 사용법은 딱 세 가지만 알면 됩니다. 이름표(Key)와 물건(Value) 세트예요.

// 물건 넣기 (저장):

// JavaScript
// localStorage.setItem('이름표', '데이터');
// // 예: localStorage.setItem('login_token', 'abc1234...');
// 물건 꺼내기 (읽기):

// JavaScript
// const data = localStorage.getItem('이름표');
// // 예: const token = localStorage.getItem('login_token');
// 물건 버리기 (삭제):

// JavaScript
// localStorage.removeItem('이름표');
// // 예: 로그아웃 할 때 사용!






// CREATE TABLE users (
//     id INT AUTO_INCREMENT PRIMARY KEY,
//     username VARCHAR(50) NOT NULL UNIQUE,
//     password VARCHAR(100) NOT NULL -- 실제로는 암호화해서 저장해야 합니다!
// );

// INSERT INTO users (username, password) VALUES ('admin', '1234');users



// # 1. XML 위치 알려주기 (src/main/resources/mapper 폴더 안의 모든 xml)
// mybatis.mapper-locations=classpath:mappers/*.xml