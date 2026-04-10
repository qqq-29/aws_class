import { Link } from 'react-router-dom';
function ChatBot(){
	return(
		<div>
			{/* 메인페이지로 이동하는 링크 추가 */}
			<Link to="/">
        메인으로 돌아가기
      </Link>
		</div>
	)
}

export default ChatBot