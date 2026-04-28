package kr.hi.practice.domain;

import lombok.Data;

@Data
public class UserDTO {
	private int Id;
    private String Username;
    private String Password;
    private String Text;
    private String Email;  // 추가
    private String Gender;
}
