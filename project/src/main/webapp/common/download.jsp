<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%
	String saveDirectory = application.getRealPath("/upload/"); // 실제 파일이 저장된 경로
	String saveFilename = request.getParameter("sName"); // 실제 서버에 저장된 파일명
	String originalFilename = request.getParameter("oName"); // 사용자가 첨부한 원본파일명

	try {
		// 파일을 찾아 입력 스트림 생성
		File file = new File(saveDirectory, saveFilename); // 경로와 서버의 파일명으로 File객체 생성
		InputStream inStream = new FileInputStream(file); // 입력 스트림 객체 생성
		//System.out.println("입력스트림");
		// 한글 파일명 깨짐 방지
		String client = request.getHeader("User-Agent"); // 사용자의 브라우저 정보
		if (client.indexOf("WOW64")	== -1) { // WOW64 문자열이 포함되지 않은 경우 -> IE 제외 브라우저
			originalFilename = new String(originalFilename.getBytes("UTF-8"),"ISO-8859-1");
		} else { 										 // 포함된 경우
			originalFilename = new String(originalFilename.getBytes("KSC5601"),"ISO-8859-1");
		}
		//System.out.println("헤더");
		// 파일 다운로드용 응답 헤더 설정
		// 브라우저 헤더정보 저장
		response.reset(); // 객체 초기화
		response.setContentType("application/octet-stream"); 
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");
		response.setHeader("Content-Length", "" + file.length() );
		
		// 출력 스트림 초기화
		out.clear();
		out=pageContext.pushBody();
		//System.out.println("출력스트림초기화");
		// response 내장 객체로부터 새로운 출력 스트림 생성
		// 출력 스트림 객체 생성
		OutputStream outStream = response.getOutputStream();
		//System.out.println("출력스트림 객체 생성");
		// 출력 스트림에 파일 내용 출력
		byte b[] = new byte[(int)file.length()];
		int readBuffer = 0;
		while ((readBuffer = inStream.read(b)) > 0) {
			outStream.write(b, 0, readBuffer);
		}
		//System.out.println("while");
		// 입/출력 스트림 닫음
		inStream.close();
		outStream.close();
		//System.out.println("끝");
	}
	catch (Exception e) {System.out.println(e.toString());} 
	
%>
