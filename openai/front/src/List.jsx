import { Link } from 'react-router-dom';

function List(){
	return(
		<div>
			<h1>기능모임</h1>
			<ul>
				<li>
					<Link to={"/ask"}>기본 ai테스트</Link>
				</li>
				<li>
					<Link to={"/translate"}>번역 ai테스트</Link>
				</li>
				<li>
					<Link to={"/ad-copy"}>광고 ai테스트</Link>
					{/**/}
				</li>
				<li>
					<Link to={"/summarize"}>번역 ai테스트</Link>
				</li>
			</ul>
		</div>
	)
}

export default List;