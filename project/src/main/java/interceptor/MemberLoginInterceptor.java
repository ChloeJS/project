package interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class MemberLoginInterceptor implements HandlerInterceptor {
	/*
	 * preHandle : 컨트롤러 실행전
	 * postHandle : 컨트롤러 실행 후 (뷰 리턴 전)
	 * afterCompletion : 뷰 실행 후
	 * */
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		// httpSession은 넣어주면 에러/ req 객체를 가지고 session객체를 꺼내와야 한다.
		// 세션 객체에서 loginInfo이름으로 꺼내서 있으면 (-> 로그인 상태) return true
		// 아니면 (-> 로그아웃 상태) return false
		HttpSession	sess = req.getSession(); // 세션 객체 받아오기
		if ( sess.getAttribute("loginInfo") == null) { // 꺼내오기 // 로그아웃 상태 
			res.setContentType("text/html;charset=utf-8"); // 한글깨짐 방지
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 사용 가능합니다.')");
			out.println("location.href='/project/member/login.do';");
			out.println("</script>");
			out.flush();
			return false;
					
		}
		return true;
	}
}
