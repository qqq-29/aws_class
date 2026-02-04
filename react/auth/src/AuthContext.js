import { createContext, useContext, useEffect, useState } from "react";
import { authFetch } from "./api/authFetch";

const AuthContext = createContext(null);

function AuthProvider({children}){
	const [user, setUser] = useState(null);

	const getMe = async ()=>{
		try{
		const response = await authFetch("/api/v1/auth/me");

		if(response.ok){
		const res = await response.json()
		return res;
	}
}catch(e){
	console.error(e);
}
return null;
	}

	return (
		<AuthContext.Provider value={{user, setUser, getMe}} >
			{children}
		</AuthContext.Provider>
	)
}

const useAuth = ()=>useContext(AuthContext);

export {useAuth, AuthProvider};