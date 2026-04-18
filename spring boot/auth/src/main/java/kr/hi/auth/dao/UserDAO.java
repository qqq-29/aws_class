package kr.hi.auth.dao;

import org.apache.ibatis.annotations.Param;

import kr.hi.auth.domain.dto.UserDTO;
import kr.hi.auth.domain.vo.UserVO;

public interface UserDAO {

	boolean postSignup(UserDTO userDTO);

	UserVO selectUser(@Param("id")String id);

}
