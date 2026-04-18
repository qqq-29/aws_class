package kr.hi.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.hi.auth.dao.UserDAO;
import kr.hi.auth.domain.dto.UserDTO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	
    private final BCryptPasswordEncoder encoder;
	
	private final UserDAO userDAO;

	public boolean postSignup(UserDTO userDTO) {
		
		//기존 비밀번호를 가져와서 암호화
		//=> encode(비밀번호)를 이용해서
		
		UserDTO user = new UserDTO(userDTO.id(),encoder.encode(userDTO.pw()),userDTO.email());
		try {
		return userDAO.postSignup(user);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
