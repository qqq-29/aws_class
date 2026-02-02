import { Link } from "react-router-dom";
import "./Navbar.css"
import { useAuth } from "../../AuthContext";



const NAV_ITEMS = [
	{ label : '메인', path : '/'},
	{ label : '회원가입', path : '/signup'},
	{ label : '로그인', path : '/login'},
];


function Navbar(){
	const {user} = useAuth();
	return (
		<nav className="navbar">
			<ul className="menu-list">
				{
				NAV_ITEMS.map(item=>{
					return (
						<li className="menu-item" key={item.path}>
							<Link className="menu-link" to={item.path}>{item.label}</Link>
						</li>
					);
				})
				}
			</ul>
			<h2>{user.id}님 반갑습니다.</h2>
		</nav>
	);
}

export default Navbar;