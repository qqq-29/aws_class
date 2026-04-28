package kr.hi.practice.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.hi.practice.dao.UserDao;
import kr.hi.practice.domain.UserDTO;

@Service
public class UserService {
    @Autowired
    private UserDao userDAO; // XML과 연결된 인터페이스

    public Map<String, String> authenticate(String username, String password) {
        // XML에 적은 쿼리가 실행됩니다!
        Map<String, String> user = userDAO.findByUsername(username);

        if (user != null &&passwordEncoder.matches(password, user.get("password"))) {
            return user;
        }
        return null;
    }
    
    @Autowired
    private PasswordEncoder passwordEncoder;//SecurityConfig에서 만든 암호화 도구
    
    public void register(UserDTO user) {
    	// 1. 암호화 하기 전의 생 비밀번호 길이를 체크해야 합니다!
        if (user.getPassword().length() > 10) {
            throw new RuntimeException("비밀번호가 너무 깁니다. (최대 10자)");
        }
    	
    	// 2. 암호화합니다! (예: "1234" -> "$2a$10$asdf...")
    	String encodedPassword = passwordEncoder.encode(user.getPassword());
    	
    	// 3. 암호화된 비밀번호를 다시 세팅하고 DB에 저장합니다.
    	user.setPassword(encodedPassword);
    	userDAO.insertUser(user);
    }
    
}