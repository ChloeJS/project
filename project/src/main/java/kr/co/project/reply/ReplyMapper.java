package kr.co.project.reply;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper //interface가 하나 더 있어서 
public interface ReplyMapper {
	public int insert(ReplyVO vo); // insert는 리턴타입이 정수!!! 잊지마!!!!!!!
	public int count(ReplyVO vo); // interface는 무조건 public // map으로 구현한 클래스 객체
	public List<ReplyVO> list(ReplyVO vo); // 매개변수 타입을 map으로 사용해서 결과값을 list에 넣는 것
	public ReplyVO view(int no); // 상세 페이지 조회하는 기능
	public int updateViewcount(int no); // 조회수 1 증가
	public int update(ReplyVO vo); // 왜 매개변수가 vo인지 알아야 함
	public int gnoUpdate(int gno); // 답변처리
	public int onoUpdate(ReplyVO vo); // 답변처리
	public int reply(ReplyVO vo); //답변처리
	public int delete(int no); // 왜 매개변수가 int no인지 알아야 함

}
