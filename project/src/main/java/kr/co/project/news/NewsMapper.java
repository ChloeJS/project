package kr.co.project.news;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper //interface가 하나 더 있어서 
public interface NewsMapper {
	public int insert(Map map); // insert는 리턴타입이 정수!!! 잊지마!!!!!!!
	public int count(NewsVO vo); // interface는 무조건 public // map으로 구현한 클래스 객체
	public List<NewsVO> list(NewsVO vo); // 매개변수 타입을 map으로 사용해서 결과값을 list에 넣는 것
	public NewsVO view(int no); // 상세 페이지 조회하는 기능
	public int updateViewcount(int no); // 조회수 1 증가
	public int update(NewsVO vo); // 왜 매개변수가 vo인지 알아야 함
	public int delete(int no); // 왜 매개변수가 int no인지 알아야 함

}
