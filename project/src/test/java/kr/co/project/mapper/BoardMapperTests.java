package kr.co.project.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.project.board.BoardMapper;
import kr.co.project.board.BoardVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/config/servlet-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper mapper; // BoardMapper가 담기는게 아님, 타입만 BoardMapper임

	//@Test
	public void testObj() {
		log.info(mapper);
	}
	
	//@Test
	public void insert() {
		BoardVO vo = new BoardVO();	
		 vo.setTitle("제목오");
		 vo.setContent("내용오");
		 //vo.setMember_no(2);
		 vo.setFilename_org("원본파일");
		 vo.setFilename_real("실제파일");
		 int r = 0;
		 for (int i = 0; i < 120; i++) {
			 r += mapper.insert(vo);
		 }
		 log.info("등록 개수 : " + r);
	}
	
	//@Test
	public void count() {
//		Map map = new HashMap();
//		map.put("stype", "content");
//		map.put("sword", "내용");
		int totalCount = mapper.count(vo);
		System.out.println("총 카운트: "+totalCount);
	}
	
	//@Test
	public void list() {
		Map map = new HashMap();
		map.put("startIdx", 20);
		map.put("pageRow", 10);
		List<BoardVO> list = mapper.list(map);
		list.forEach(vo -> log.info(list));
	}
	
	//@Test
	public void view() {
		mapper.updateViewcount(1);
		log.info(mapper.view(1));
	}
	
	//@Test
	public void update() {
		BoardVO vo = new BoardVO();
		vo.setTitle("바뀐 제목");
		vo.setContent("바뀐 내용");
		vo.setNo(20);
		log.info("update :" +mapper.update(vo));
	}
	
	//@Test
	public void delete() {
		log.info("delete: " + mapper.delete(21));
	}
	
	//@Test
		public void list2() {
		mapper.view(1);
		}
}
