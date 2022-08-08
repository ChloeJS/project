package util;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	// 매개변수 : 발신자, 수신자, 제목, 내용
	public static void sendMail(String from, String to, String subject, String content) {
		// 1. 메일 서버 정보 설정(Property객체 -MAP 인터페이스의 하위)
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.naver.com");
		prop.put("mail.smtp.port", "465"); //포트번호
		prop.put("mail.smtp.auth", "true"); //인증방식
		prop.put("mail.smtp.ssl.enable", "true"); //ssl설정
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2"); //no appropriate protocol 에러 		
		// 2. 인증처리를 위해 Session 객체 생성
//		Authenticator a = new Authenticator(); // 추상클래스는 객체생성 안됨
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("colormain", "rkeowltn15");
			}	
		});
		session.setDebug(true);
		
		// 3. MimeMessage 객체 생성(발신자, 수신자, 제목, 내용 설정)
		try {
			MimeMessage mm = new MimeMessage(session);
			mm.setFrom(new InternetAddress(from)); // 발신자
			mm.setRecipient(Message.RecipientType.TO, new InternetAddress(to)); // 수신자
			mm.setSubject(subject); // 제목
//			mm.setText(content); // 내용
			mm.setContent(content, "text/html; charset=utf-8"); // 내용(html)
			
		// 4. 메일 발송
			Transport.send(mm);
			} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
		public static void main(String[] args) {
			sendMail("colormain@naver.com", "etcs0603@gmail.com", "test", "<b>회원가입을 축하합니다.</b>testtest");
		}
}
