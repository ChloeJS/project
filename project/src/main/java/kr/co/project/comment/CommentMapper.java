package kr.co.project.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
	 int insert(CommentVO vo);
	 int count(CommentVO vo); //목록에서 같이 실행 -> 따로 리턴할 수 없으니 map에 담아서 map객체로 리턴
	 List<CommentVO> list(CommentVO vo); //목록에서 같이 실행 -> 따로 리턴할 수 없으니 map에 담아서 map객체로 리턴
	 int delete(int no);
	 
	 
}
