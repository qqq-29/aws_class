package kr.hi.community.dao;

import org.apache.ibatis.annotations.Param;

import kr.hi.community.model.dto.MemberDTO;
import kr.hi.community.model.vo.MemberVO;

//구현한것이 map돼기때문에 interface로 됐다.
public interface MemberDAO {

	MemberVO selectMember(@Param("id")String id);

	boolean insertMember(@Param("obj")MemberDTO member);

}
