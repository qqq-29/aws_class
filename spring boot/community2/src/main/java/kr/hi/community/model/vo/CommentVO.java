package kr.hi.community.model.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class CommentVO {

	int co_num; 
	String co_content; 
	@JsonFormat(
		shape = Shape.STRING, 
		//yyyy : 연도, MM : 월, dd : 일, HH : 시(오후2시 14시)
		//mm : 분, ss: 초, hh : 시(오후 2시 02)
		pattern="yyyy.MM.dd. HH:mm",
		timezone = "Asia/Seoul")
	Date co_date; 
	int co_ori_num; 
	String co_del; 
	String co_me_id; 
	int co_po_num;
	
}
