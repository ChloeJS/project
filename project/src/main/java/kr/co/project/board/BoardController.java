package kr.co.project.board;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.project.comment.CommentService;
import kr.co.project.comment.CommentVO;
import kr.co.project.member.MemberVO;

@Controller
//@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService service;
	
	@Autowired // 22.07.27 commentService 주입 추가
	CommentService cService;

	@GetMapping("/board/index.do")
	public String index(Model model, BoardVO vo) { // 저장해야 하니까 model , 화면에 보여야 하니까 커맨드객체 , httpservletRequest req는 하나의 요청 저장
		model.addAttribute("data", service.index(vo));
		return "board/index"; // 이 "board/index.jsp"로 포워딩
	}

	@GetMapping("/board/view.do")
	public String view(Model model, BoardVO vo) {
		service.viewCount(vo.getNo());
		model.addAttribute("view", service.view(vo.getNo()));
		return "board/view";
	}

	@GetMapping("/board/write.do")
	public String write(Model model) {
		return "board/write";
	}

	@PostMapping("/board/insert.do")
	public String insert(BoardVO vo, Model model, @RequestParam MultipartFile filename, HttpServletRequest req) { // model이 필요한 이유? // multipartfile이라는 타입의 filename 매개변수(파일업로드)
		// 첨부파일 처리
		if (!filename.isEmpty()) {
			// 파일명 새로 지정 -> 서버에 저장
			 String org = filename.getOriginalFilename(); // 사용자가 첨부한 원본파일의 파일명
			 String ext = org.substring(org.lastIndexOf(".")); // 확장자 ... 이게모야ㅠㅠ .위치를 찾아서 끝까지를 확장자
			 String real = new Date().getTime()+ext;
			 
			 // 파일 저장
			 String path = req.getRealPath("/upload/");//경로 불러오기
			 try {
				 filename.transferTo(new File(path+real));
			 } catch (Exception e) {}
			 vo.setFilename_org(org);
			 vo.setFilename_real(real);
		}
		 
		// member_no 저장(22.07.26 추가)
		HttpSession sess = req.getSession(); // 세션 객체가 sess에 들어감
		MemberVO mv = (MemberVO)sess.getAttribute("loginInfo");
		vo.setMember_no(mv.getNo()); // mv에 loginInfo이름의 MemberVO vo객체의 한 행 중 no가 BoardVO vo에 있는 member_no에 저장됨
		if (service.insert(vo)) { // 로그인한 사람의 pk 값을 넣어주기 위해 (=vo에 값이 들어가도록 하기 위해서 62,63,64열)
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "저장 실패했습니다.");
			return "common/alert";
		}
	}

	@GetMapping("/board/edit.do")
	public String edit(BoardVO vo, Model model) {
		model.addAttribute("view", service.edit(vo.getNo()));
		return "board/edit";
	}

	@PostMapping("/board/update.do")
	public String update(BoardVO vo, Model model) {
		if (service.update(vo)) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?no=" + vo.getNo());
			return "common/alert";
		} else {
			model.addAttribute("msg", "수정 실패했습니다.");
			return "common/alert";
		}
	}

	@GetMapping("/board/delete.do")
	public String delete(BoardVO vo, Model model) {
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
