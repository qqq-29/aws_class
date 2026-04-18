import { Link } from 'react-router-dom';
import { useAuth } from '../AuthContext';

function Navbar(){
const NAV_ITEMS = [
	{ label : '메인', path : '/', auth : "any"},
	{ label : '회원가입', path : '/signup', auth : "guest"},
	{ label : '로그인', path : '/login', auth : "guest"},
	{ label : '마이페이지', path : '/mypage', auth : "user"}
]

	const {user} = useAuth();
	return ( 
	<nav>
				<ul>
          {
						NAV_ITEMS
						//나중에 로그인 여부에 따라 보여줄 메뉴를 다르게 하기 위한 filter를 미리 추가
						.filter(item=>{
							if(item.auth === "any") return true;
							if(item.auth === "guest") return !user;
							if(item.auth === "user") return user;
							return true;
						})
						.map(item=> <li><Link to={item.path}>{item.label}</Link></li>)
					}
        </ul>
	</nav>
)
}
export default Navbar;