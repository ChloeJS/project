package kr.co.project.comment;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CommentVO {
	private int no;
	private String content;
	private int member_no;
	private int board_no;
	private Timestamp regdate;
	private String tablename;
	private String member_name; // 22.07.28 추가> 작성자 확인을 위해
	
	
	private int page; // 현재page번호
	private int startIdx; // 페이지의 개수(시작)
	private int pageRow; // 페이지당 row갯수 (기본: 10)
	
	
	
	//기본은 10
	public CommentVO() {
		this.page = 1;
		this.pageRow = 10;
		
	}
	

}
