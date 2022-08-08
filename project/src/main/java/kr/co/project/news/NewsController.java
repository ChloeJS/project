package kr.co.project.news;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsController {

	@Autowired
	NewsService service;

	@GetMapping("/news/index.do")
	public String index(Model model, NewsVO vo) { // 저장해야 하니까 model , 화면에 보여야 하니까 커맨드객체
		model.addAttribute("data", service.index(vo));
		return "news/index"; // 이 "news/index.jsp"로 포워딩
	}

	@GetMapping("/news/view.do")
	public String view(Model model, NewsVO vo) {
		service.viewCount(vo.getNo());
		model.addAttribute("view", service.view(vo.getNo()));
		return "news/view";
	}

	@GetMapping("/news/write.do")
	public String write(Model model) {
		return "news/write";
	}

	@PostMapping("/news/insert.do")
	public String insert(@RequestParam Map map, Model model) { // model이 필요한 이유?
		if (service.insert(map)) {
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "저장 실패했습니다.");
			return "common/alert";
		}
	}

	@GetMapping("/news/edit.do")
	public String edit(NewsVO vo, Model model) {
		model.addAttribute("view", service.edit(vo.getNo()));
		return "news/edit";
	}

	@PostMapping("/news/update.do")
	public String update(NewsVO vo, Model model) {
		if (service.update(vo)) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?no=" + vo.getNo());
			return "common/alert";
		} else {
			model.addAttribute("msg", "수정 실패했습니다.");
			return "common/alert";
		}
	}

	@GetMapping("/news/delete.do")
	public String delete(NewsVO vo, Model model) {
		if (service.delete(vo.getNo())) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "삭제 실패했습니다.");
			return "common/alert";
		}
	}
}
