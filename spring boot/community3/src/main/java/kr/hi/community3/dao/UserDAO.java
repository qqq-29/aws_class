package kr.hi.community3.dao;

import kr.hi.community3.model.dto.LoginDTO;
import kr.hi.community3.model.vo.UserVO;

public interface UserDAO {

	boolean insertUser(LoginDTO user);

	UserVO selectUser(String id);

}
