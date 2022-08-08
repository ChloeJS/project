package kr.co.project.comment;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommentController {
	
	@Autowired
	CommentService service;
	
	// 3가지 기능 전부 ajax로 할거야
	
	@GetMapping("/comment/list.do")
	public String list(CommentVO vo, Model model) {
		Map map = service.index(vo);
		model.addAttribute("comment", map);
		return "common/comment"; // 공통 jsp // responsebody는 문자열 자체를 출력하는 것
	}
	// 등록
	@GetMapping("/comment/insert.do")
	public String insert(CommentVO vo, Model model) {
		// responsebody 안쓰고 단순 출력으로 ajax에서 
		model.addAttribute("result", service.insert(vo));
		return "common/return"; // 무조건 0 또는 1이 나올 것
	}
	// 삭제
	@GetMapping("/comment/delete.do")
	public String delete(CommentVO vo, Model model) {
		model.addAttribute("result", service.delete(vo));
		return "common/return"; // 무조건 0 또는 1이 나올 것
	}
	
	
}
