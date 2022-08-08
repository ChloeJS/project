package kr.co.project.news;



import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class NewsVO {
	private int no;
	private String title;
	private String content;
	private Timestamp regdate;
	private int viewcount;
	private int member_no;
	private String filename_org;
	private String filename_real;
	
	private int page; // page번호
	private String stype; // 검색 타입
	private String sword; // 검색어
	private int startIdx;
	private int pageRow;
	private String tableName; // 22.07.22 추가
	

	
	public NewsVO () {
//		this.page = 1; //페이지 번호가 없으면 1이 들어가도록
//		this.pageRow = 10; //페이지 개수가 따로 없으면 10이 들어가도록
		this(1, 10);
	}
	
	public NewsVO(int page, int pageRow) {
		this.page = page;
		this.pageRow = pageRow;
		this.tableName = "news"; // 22.07.22 추가
		
	}
	
}
