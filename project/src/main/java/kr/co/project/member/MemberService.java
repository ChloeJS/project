package kr.co.project.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
	int insert(MemberVO vo);
	int emailDupCheck(String email);
	boolean loginCheck(MemberVO vo, HttpSession sess); // 로그인이 됐는지 안됐는지 확인
	MemberVO findEmail(MemberVO vo);
	MemberVO findPwd(MemberVO vo);
}
