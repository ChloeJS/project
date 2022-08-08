package kr.co.project.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NewsServiceImpl implements NewsService {
	
	
	@Autowired
	NewsMapper mapper;
	
	@Override
	public Map index(NewsVO vo) { // model로 add해서 받아도 가능하다. -> 해봐..?
		// 총 게시물 수
		int totalCount = mapper.count(vo); 
		// 총 페이지 수
		int totalPage = totalCount / vo.getPageRow(); // 10은 바뀔 수 있으니까 vo에 재정의 
		if (totalCount % vo.getPageRow() > 0) totalPage++;
		
		// 시작 인덱스
		int startIdx = (vo.getPage() - 1) * vo.getPageRow();
		vo.setStartIdx(startIdx); // sql문 startIdx에 나오게 하려면 set해야함
		List<NewsVO> list = mapper.list(vo);
		
		
		// 페이징 처리 22.07.21
		int endPage = (int)(Math.ceil(vo.getPage()/10.0) * 10);
		int startPage = endPage - 9;
		
		if (endPage > totalPage) endPage = totalPage;
		
		boolean prev = startPage > 1 ? true : false ;
		boolean next = endPage < totalPage ? true : false; //endPage랑 totalPage가 같으면 다음페이지로 넘어갈 일이 없으니까!
		
		Map map = new HashMap();
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("prev", prev);
		map.put("next", next);
		map.put("list", list);
		
		return map;
	}

	@Override
	public NewsVO view(int no) {
		return mapper.view(no);
	}
	

	@Override
	public NewsVO edit(int no) {
		return mapper.view(no);
	}

	@Override
	public boolean update(NewsVO vo) {
		return mapper.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean delete(int no) {
		return mapper.delete(no) > 0 ? true : false;
	}

	@Override
	public boolean insert(Map map) {
		return mapper.insert(map) > 0 ? true : false;
	}

	@Override
	public int viewCount(int no) {
		return mapper.updateViewcount(no);
	}




}
