package kr.hi.community.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.hi.community.dao.MemberDAO;
import kr.hi.community.model.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	public boolean signup(MemberDTO member) {
		//회원 정보가 없으면 가입 실패
		if(member == null) {
			return false;
		}
		//아이디는 6~13자이며, 영문,숫자,특수문자(!@#$)만 가능
		String idRegex = "^[a-zA-Z0-9!@#$]{6,13}$";
		String id = member.getId();

		//아이디가 아이디 형식에 맞지 않으면
		if(id == null || !Pattern.matches(idRegex, id)) {
			return false;
		}
		
		//비번은 8~13자이며, 영문,숫자,특수문자(!@#$)만 가능
		String pwRegex = "^[a-zA-Z0-9!@#$]{8,13}$";
		String pw = member.getPw();
		//비번 형식에 맞지 않으면
		if(pw == null || !Pattern.matches(pwRegex, pw)) {
			return false;
		}
		
		//이메일 체크
		if(member.getEmail() == null) {
			return false;
		}
		
		try {
			//비밀번호 암호화
			String encodedPw = pwEncoder.encode(pw);
			//넘겨줄 객체의 비번을 암호화된 비번으로 수정
			member.setPw(encodedPw);
			//아이디 중복이거나(아이디는 기본키)
			//이메일 중복이면(이메일은 unique 설정) 예외 발생 => 가입 실패
			return memberDAO.insertMember(member);
		}catch(Exception e) {
			//아이디 중복 또는 이메일 중복
			e.printStackTrace();
			return false;
		}
	}

}
