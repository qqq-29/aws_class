import logo from './logo.svg';
import './Main.css';
import { Link } from 'react-router-dom';
function Main(){
	return (
		<div className="App">
			<header className="App-header">
				<img src={logo} className="App-logo" alt="logo" />
				<Link className="App-link" to={"/list"}>
					기능모음
				</Link>
				<Link className="App-link" to="/ask">
					대답
				</Link>
			</header>
		</div>
	);
}

export default Main;