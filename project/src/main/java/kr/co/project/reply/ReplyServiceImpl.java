package kr.co.project.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReplyServiceImpl implements ReplyService {
	
	
	@Autowired
	ReplyMapper mapper;
	
	@Override
	public Map index(ReplyVO vo) { // model로 add해서 받아도 가능하다. -> 해봐..?
		// 총 게시물 수
		int totalCount = mapper.count(vo); 
		// 총 페이지 수
		int totalPage = totalCount / vo.getPageRow(); // 10은 바뀔 수 있으니까 vo에 재정의 
		if (totalCount % vo.getPageRow() > 0) totalPage++;
		
		// 시작 인덱스
		int startIdx = (vo.getPage() - 1) * vo.getPageRow();
		vo.setStartIdx(startIdx); // sql문 startIdx에 나오게 하려면 set해야함
		List<ReplyVO> list = mapper.list(vo);
		
		
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
	public ReplyVO view(int no) {
		return mapper.view(no);
	}
	

	@Override
	public ReplyVO edit(int no) {
		return mapper.view(no);
	}

	@Override
	public boolean update(ReplyVO vo) {
		return mapper.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean delete(int no) {
		return mapper.delete(no) > 0 ? true : false;
	}


	@Override 
	public boolean insert(ReplyVO vo) {
		boolean r = mapper.insert(vo) > 0 ? true : false; // 22.08.02 추가 답변처리
		if (r) mapper.gnoUpdate(vo.getNo());
		return r;
	}
	@Override
	public boolean reply(ReplyVO vo) { // 답변등록 처리
		mapper.onoUpdate(vo); // 부모의 gno와 같고, 부모의 ono보다 큰 ono+1 처리
		vo.setOno(vo.getOno()+1);
		vo.setNested(vo.getNested()+1);
		return mapper.reply(vo)> 0 ? true: false;
	}


}
