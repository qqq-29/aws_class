package kr.hi.practice.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hi.practice.dao.UserDao;

@Service
public class UserService {
    @Autowired
    private UserDao userDAO; // XML과 연결된 인터페이스

    public Map<String, String> authenticate(String username, String password) {
        // XML에 적은 쿼리가 실행됩니다!
        Map<String, String> user = userDAO.findByUsername(username);

        if (user != null && password.equals(user.get("password"))) {
            return user;
        }
        return null;
    }
}