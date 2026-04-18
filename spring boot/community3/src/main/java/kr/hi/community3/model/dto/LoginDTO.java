package kr.hi.community3.model.dto;

/* record는 값을 객체의 값을 수정할 수 없는 클래스
 * - 선언된 모든 필드가 private final로 지정
 * - setter는 없고, getter만 추가
 * - 상속 불가
 * - 모든 필드를 매개변수로 받는 생성자를 추가
 * - 모든 필드가 같으면 같다고 판별하는 equals 추가
 * toString 추가
 * */
public record LoginDTO (

	String id,
	String pw,
	String email
) {}
