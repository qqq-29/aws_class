package kr.hi.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.hi.auth.dao.UserDAO;
import kr.hi.auth.domain.vo.UserVO;
import kr.hi.auth.util.CustomUser;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberDetailService implements UserDetailsService{

	@Autowired
	UserDAO userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO user = userDao.selectUser(username);

		return user == null ? null : new CustomUser(user);
	}

}
