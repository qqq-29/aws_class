import { createContext, useContext, useEffect, useState } from "react";
import { authFetch } from "./api/authFech";

//전역으로 사용할 수 있는 통로를 생성
const AuthContext = createContext(null);

//여기서 children은 props가 아닌 자식 컴포넌트
export function AuthProvider({children}){

	const [user, setUser] = useState({});
	
	useEffect(()=>{

		getMe();

	}, []);

	const getMe = async () =>{
		try{
			const response = await authFetch("/api/v1/auth/me");
			if(!response.ok){
				return;
			}
			const res = await response.json();
			const {id, email, role} = res;
			setUser({id, email, role});
		}catch(e){
			console.error(e);
			setUser(null);
		}
	}
	
	return (
		<AuthContext.Provider value={{user}} >
			{children}
		</AuthContext.Provider>
	)
}
//가독성, import 문제
export const useAuth = ()=>useContext(AuthContext);