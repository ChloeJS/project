package kr.co.project.reply;

import java.util.Map;

public interface ReplyService {
	// 비즈니스 로직
	// 컨트롤러 입장에서 무엇을 리턴하면 좋을지 생각해보기
	// 목록 --> 여러 개라도 리턴은 하나 -> Map은 모든 타입이 가능하니까 // list는 같은 타입만 가능하니까 비효율적
	Map index(ReplyVO vo); 
	// 상세
	ReplyVO view(int no); 
	// 수정 폼
	ReplyVO edit(int no); 
	// 수정 처리
	boolean update(ReplyVO vo);
	// 삭제 처리
	boolean delete(int no); 
	// 등록 처리
	boolean insert(ReplyVO vo); // gno 업데이트 하는 부분
	// 답변 처리 (논리적 단위)(22.08.02)
	boolean reply(ReplyVO vo);
}
