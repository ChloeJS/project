package kr.co.project.reply;



import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ReplyVO {
	private int no;
	private String title;
	private String content;
	private Timestamp regdate;
	private int viewcount;
	private int member_no;
	private String filename_org;
	private String filename_real;
	
	// 댓글 처리 위한 컬럼  
	private int gno;
	private int ono;
	private int nested;
	
	
	private String member_name; // 22.07.28 추가> 작성자 확인을 위해
	private int comment_count; // 22.07.28 추가> 작성자 확인을 위해
	
	private int page; // page번호
	private String stype; // 검색 타입
	private String sword; // 검색어
	private int startIdx;
	private int pageRow;
	

	
	public ReplyVO () {
//		this.page = 1; //페이지 번호가 없으면 1이 들어가도록
//		this.pageRow = 10; //페이지 개수가 따로 없으면 10이 들어가도록
		this(1, 10);
	}
	
	public ReplyVO(int page, int pageRow) {
		this.page = page;
		this.pageRow = pageRow;
	}
	
}
