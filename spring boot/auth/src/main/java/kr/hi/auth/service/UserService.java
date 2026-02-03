package kr.hi.auth.service;

import org.springframework.stereotype.Service;

import kr.hi.auth.dao.UserDAO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserDAO userDAO;
	
}
