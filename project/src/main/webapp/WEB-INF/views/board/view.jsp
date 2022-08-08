<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.*"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 22.07.27 추가 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <meta name="format-detection" content="telephone=no, address=no, email=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>게시판 상세</title>
    <link rel="stylesheet" href="/project/css/reset.css"/>
    <link rel="stylesheet" href="/project/css/contents.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

	<script>
		function del(no){
			if (confirm('삭제하시겠습니까?')){
				location.href='delete.do?no='+no;
			}
		}
		function getComment(page){
				$.ajax({
					url : "/project/comment/list.do",
					data : {
						board_no : ${view.no},
						tablename : 'board',
						page : page
					},
					success : function(res){
						$("#commentArea").html(res); //컨트롤러에서 아닌 jsp에서 js를 호출
					}
				});
		}
		$(function(){
			getComment(1);	
		});

		function goSave(){
			<c:if test="${empty loginInfo}">
				alert("댓글은 로그인 후 작성 가능합니다.")
			</c:if>
			<c:if test="${!empty loginInfo}">
			if (confirm('댓글을 작성하시겠습니까?')){
				$.ajax({
					url : "/project/comment/insert.do",
					data : {
						board_no : ${view.no}, // el ${param.no} 
						tablename : 'board',
						content : $("#contents").val(), //?????????????????? // jquery
						member_no : ${loginInfo.no} // el
					},
					success : function(res){
						if (res.trim() == "1"){
							alert("정상적으로 등록되었습니다."); //컨트롤러에서 아닌 jsp에서 js를 호출
							$("#contents").val('');
							getComment(1);	
						}
					}
				});
			}
			</c:if>
		}
		
		//댓글 삭제 함수 (함수 재정의 - 이 자체가 실행되는 것이 아님!!)
		function commentDel(no){
			if (confirm ("정말 삭제하시겠습니까?")){
				$.ajax({
					url : "/project/comment/delete.do?no="+no,
					success : function(res){
						if (res.trim() == '1'){ // 처음과 끝의 공백제거 후 값이 1이면 
							alert("정상적으로 삭제되었습니다.");
							getComment(1); // 다시 빈칸으로 만들어주기
						}
					}
				});
			}
		}
	</script>
</head>
<body>
    
        <div class="sub">
            <div class="size">
                <h3 class="sub_title">게시판</h3>
                <div class="bbs">
                    <div class="view">
                        <div class="title">
                            <dl>
                                <dt>${view.title } </dt>
                                <dd class="date">작성일 : ${view.regdate} </dd>
                            </dl>
                        </div>
                        <div class="cont"><p>${view.content}</p> </div>
                        <dl class="file">
                            <dt>첨부파일 </dt>
                            <dd>
                            <a href="/project/common/download.jsp?oName=${URLEncoder.encode(view.filename_org , 'UTF-8')}&sName=${view.filename_real}" 
                            target="_blank">${view.filename_org} </a></dd>
                        </dl>
                                    
                        <div class="btnSet clear">
                            <div class="fl_l">
                            <a href="/project/board/index.do" class="btn">목록으로</a>
                            <a href="edit.do?no=${view.no}" class="btn">수정</a>
                            <a href="javascript:del(${view.no});" class="btn">삭제</a> <!-- 삭제버튼은 조금 특이함 잘 알아두기 -->
                            </div>
                        </div>
                
                    </div>

                    <div>
                    <form method="post" name="frm" id="frm" action="" enctype="multipart/form-data" >
                        <table class="board_write">
                            <colgroup>
                                <col width="*" />
                                <col width="100px" />
                            </colgroup>
                            <tbody>
                            <tr>
                                <td>
                                    <textarea name="contents" id="contents" style="height:50px;"></textarea>
                                </td>
                                <td>
                                    <div class="btnSet"  style="text-align:right;">
                                        <a class="btn" href="javascript:goSave();">저장 </a>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        </form>
							
							<div id="commentArea"></div> <!-- 댓글 목록 출력을 위해 -->
				
                    </div>
                </div>
            </div>
        </div>
        
</body>
</html>