package kr.co.project.reply;

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
//@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	ReplyService service;
	
	@Autowired // 22.07.27 commentService 주입 추가
	CommentService cService;

	@GetMapping("/reply/index.do")
	public String index(Model model, ReplyVO vo) { // 저장해야 하니까 model , 화면에 보여야 하니까 커맨드객체 , httpservletRequest req는 하나의 요청 저장
		model.addAttribute("data", service.index(vo));
		return "reply/index"; // 이 "reply/index.jsp"로 포워딩
	}



	@GetMapping("/reply/write.do")
	public String write(Model model) {
		return "reply/write";
	}

	@PostMapping("/reply/insert.do")
	public String insert(ReplyVO vo, Model model, @RequestParam MultipartFile filename, HttpServletRequest req) { // model이 필요한 이유? // multipartfile이라는 타입의 filename 매개변수(파일업로드)
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
		if (mv != null) vo.setMember_no(mv.getNo()); 
		if(service.reply(vo)){ 
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "저장 실패했습니다.");
			return "common/alert";
		}
	}
	
	@GetMapping("/reply/view.do")
	public String view(ReplyVO vo, Model model) {
		ReplyVO data = service.view(vo.getNo());
		model.addAttribute("data", data);
		return "reply/view";
	}
	
	// 답변 폼
	@GetMapping("/reply/reply.do")
	public String reply(ReplyVO vo, Model model) {
		ReplyVO data = service.edit(vo.getNo());
		model.addAttribute("data", data);
		return "reply/reply";
	}
	 // 오버로드
	
	@PostMapping("/reply/reply.do")
	public String reply(ReplyVO vo, Model model, @RequestParam MultipartFile filename, HttpServletRequest req) {
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
		if (mv != null) vo.setMember_no(mv.getNo()); 
		if (service.reply(vo)) { 
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "저장 실패했습니다.");
			return "common/alert";
		}
	}
	
	
	@GetMapping("/reply/edit.do")
	public String edit(ReplyVO vo, Model model) {
		model.addAttribute("view", service.edit(vo.getNo()));
		return "reply/edit";
	}

	@PostMapping("/reply/update.do")
	public String update(ReplyVO vo, Model model) {
		if (service.update(vo)) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?no=" + vo.getNo());
			return "common/alert";
		} else {
			model.addAttribute("msg", "수정 실패했습니다.");
			return "common/alert";
		}
	}

	@GetMapping("/reply/delete.do")
	public String delete(ReplyVO vo, Model model) {
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
