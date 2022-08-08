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
    <title>게시판 상세</title>
    <link rel="stylesheet" href="/project/css/reset.css"/>
    <link rel="stylesheet" href="/project/css/contents.css"/>


	<script>
		function del(no){
			if (confirm('삭제하시겠습니까?')){
				location.href='delete.do?no='+no;
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
                            <a href="#" 
                            target="_blank">첨부파일.pptx [38.07KB] </a></dd>
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

                        <p><span><strong>총 2개</strong>  |  1/1페이지</span></p>
                    <table class="list">
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="100px" />
                        </colgroup>
                        <tbody>

                            <tr>
                                <td class="first" colspan="8">등록된 글이 없습니다.</td>
                            </tr>

                                    
                            <tr>
                                <td>10</td>
                                <td class="txt_l">
                                    <a href="board_view.html">게시글 제목</a>
                                </td>
                                <td class="writer">
                                    홍길동
                                </td>
                                <td class="date">2021-01-01</td>
                            </tr>
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>
        
</body>
</html>