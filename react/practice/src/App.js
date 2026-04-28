import { BrowserRouter as Router, Routes, Route, Link, Navigate } from 'react-router-dom';
import Login from './Login';   // 새로 만들 파일
import Signup from './Signup'; // 새로 만들 파일

function App() {
  return (
    <Router>
      <div style={{ textAlign: 'center' }}>
        <nav style={{ padding: '20px', backgroundColor: '#eee' }}>
          <Link to="/login" style={{ marginRight: '10px' }}>로그인</Link>
          <Link to="/signup">회원가입</Link>
        </nav>

        <Routes>
          {/* 주소가 /login 이면 로그인 컴포넌트를 보여줘! */}
          <Route path="/login" element={<Login />} />
          
          {/* 주소가 /signup 이면 회원가입 컴포넌트를 보여줘! */}
          <Route path="/signup" element={<Signup />} />
          
          {/* 처음 접속했을 때 바로 로그인 페이지로 가도록 설정 */}
          <Route path="/" element={<Navigate to="/login" />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;