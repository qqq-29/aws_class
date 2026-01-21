package kr.hi.community.model.vo;

import lombok.Data;

@Data
public class PostVO {
	
	int po_num; 
	String po_title; 
	String po_content; 
	String po_date; 
	int po_view; 
	int po_up; 
	int po_down; 
	String po_me_id; 
	int po_bo_num; 
	String po_del;
	//게시판 명을 가져오기 위한 변수를 추가
	String po_bo_name;
	//댓글 수(삭제된 댓글 포함)
	int po_comment_count;
}
