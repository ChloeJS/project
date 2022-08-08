package kr.co.project.comment;

import java.util.Map;

public interface CommentService {
	Map index(CommentVO vo); // 목록 보기 (로직은 count, list 2개) 담아서 map객체로
	int insert(CommentVO vo);
	int delete(CommentVO vo);
}
