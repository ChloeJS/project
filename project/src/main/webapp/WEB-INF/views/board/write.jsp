<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <meta name="format-detection" content="telephone=no, address=no, email=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>게시판 등록</title>
    
    <!-- 아래부분 공통이니까 include -->
    <link rel="stylesheet" href="/project/css/reset.css"/>
    <link rel="stylesheet" href="/project/css/contents.css"/>
    
    <script src="/project/smarteditor/js/HuskyEZCreator.js"></script><!-- 22.08.03 추가 -->
    <script src="/project/js/function.js"></script><!-- 22.08.03 추가 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script><!-- 22.08.03 추가 -->
    
    <script>
    	function goSave(){
    		editor.getById['content'].exec('UPDATE_CONTENTS_FIELD',[]); // getById -> jindo라는 프레임워크에서 사용하는 것	
    		frm.submit();
    	}
    	var editor; // 변수를 밖에 쓴 이유: 전역변수로 사용하기 위해서
    	$(function(){
    		editor = setEditor('content'); // textarea id= content
    	});
    </script>
    
</head>
<body>
    
        <div class="sub">
            <div class="size">
                <h3 class="sub_title">게시판</h3>
    
                <div class="bbs">
                <form method="post" name="frm" id="frm" action="insert.do"  enctype="multipart/form-data" > 
                <!--  <input type="hidden" name="member_no" value="${loginInfo.no}"> -->
                <!-- 작성자가 누구인지 저장/전송하기 위한 처리
                session에 loginInfo라는 이름으로 저장했기 때문에 loginInfo이름으로 꺼내야 한다. 
                그리고 loginInfo안에 MemberVO vo객체가 들어있는 것이고 member테이블에서는 member_no가 아니라 no!!!-->
                    <table class="board_write">
                        <tbody>
                        <tr>
                            <th>제목</th>
                            <td>
                                <input type="text" name="title" id="title" class="wid100" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea name="content" id="content" style="width:90%"></textarea>
                            </td>
                        </tr>
                        <tr>
                        	<th>첨부파일</th>
                        	<td>
                        		<input type="file" name="filename">
                        	</td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="btnSet"  style="text-align:right;">
                        <a class="btn" href="javascript:goSave();">저장 </a>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        
</body>
</html>