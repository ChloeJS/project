package kr.co.project.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

	@Autowired
	MemberService service;

	// 등록(회원가입)폼
	@GetMapping("/member/join.do")
	public String join() {
		return "member/join";
	}

	// 등록(회원가입) 처리
	@PostMapping("/member/join.do")
	public String join(MemberVO vo, Model model) {
		if (service.insert(vo) > 0) {
			model.addAttribute("msg", "정상적으로 회원가입되었습니다. 반갑습니다.");
			model.addAttribute("url", "login.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "회원가입 오류입니다.");
			return "common/alert";
		}
	}

	// 이메일 중복체크 (22.07.25)
	// post로 하면 브라우저에서 테스트 불가 -> api test로는 가능
	@GetMapping("/member/emailDupCheck.do")
	@ResponseBody
	public void emailDupCheck(@RequestParam String email, HttpServletResponse res) throws Exception {
		int count = service.emailDupCheck(email); // 갯수가 count에 담긴다.
		boolean r = false;
		if (count == 1)
			r = true;
		PrintWriter out = res.getWriter(); // 출력
		out.print(r);
		out.flush(); // ?
	}

	// 로그인 폼 (22.07.25)
	@GetMapping("/member/login.do")
	public String login() {
		return "member/login";
	}

	// 로그인 확인 처리 (22.07.25)
	@PostMapping("/member/login.do")
	public String login(MemberVO vo, HttpSession sess, Model model) {
		if (service.loginCheck(vo, sess)) {
			return "redirect:/board/index.do";
		} else {
			model.addAttribute("msg", "이메일 비밀번호를 확인해주세요.");
			return "common/alert";
		}
	}

	// 로그인 확인 처리 (22.07.26)
	@GetMapping("/member/logout.do")
	public String logout(Model model, HttpServletRequest req) { // alert에 메세지 띄울거니까 model,
		HttpSession sess = req.getSession();
		sess.invalidate(); // 세션 초기화(세션 객체에 있는 모든 값들이 삭제)
		// sess.removeAttribute("loginInfo"); // 세션 객체의 해당값만 삭제
		model.addAttribute("msg", "로그아웃되었습니다.");
		model.addAttribute("url", "/project/board/index.do");
		return "common/alert";
	}


	@GetMapping("/member/findEmail.do") public String findEmail() {
		  return "member/findEmail"; 
		  }
	
	@PostMapping("/member/findEmail.do")
	public String findEmail(Model model, MemberVO param) {
		MemberVO vo = service.findEmail(param);
		if (vo != null) {
			model.addAttribute("result", vo.getEmail());
		}
		return "common/return";
	}
	@GetMapping("/member/findPwd.do") 
	public String findPwd() {
		return "member/findPwd"; 
	}
	
	@PostMapping("/member/findPwd.do")
	public String findPwd(Model model, MemberVO param) {
		MemberVO vo = service.findPwd(param);
		if (vo != null) {
			model.addAttribute("result", vo.getEmail());
		}
		return "common/return";
	}
}
