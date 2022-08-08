package kr.co.project.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.project.board.BoardVO;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentMapper mapper;
	
	@Override
	public Map index(CommentVO vo) {
		int totalCount = mapper.count(vo); // 총 게시물 수 
		int totalPage = totalCount / vo.getPageRow(); //
		if (totalCount % vo.getPageRow() > 0) totalPage++;
		
		// 시작 인덱스
		int startIdx = (vo.getPage() - 1) * vo.getPageRow();
		vo.setStartIdx(startIdx); // sql문에서 startIdx가 나오게 하려면 set해야함
		List<CommentVO> list = mapper.list(vo);
		
		
		// 페이징 처리 
		int endPage = (int)(Math.ceil(vo.getPage()/10.0) * 10);
		int startPage = endPage - 9;
		
		if (endPage > totalPage) endPage = totalPage;
		
		boolean prev = startPage > 1 ? true : false ; // 시작페이지가 2,3,4... 일리가 없지!
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
	public int insert(CommentVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int delete(CommentVO vo) {
		return mapper.delete(vo.getNo());
	}



}
