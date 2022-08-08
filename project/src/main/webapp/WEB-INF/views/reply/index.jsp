<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>게시판목록</title>
<link rel="stylesheet" href="/project/css/reset.css" />
<link rel="stylesheet" href="/project/css/contents.css" />

<script>
	function goWrite(){
		<c:if test="${empty loginInfo}">
			alert('로그인 후 작성 가능합니다.');
		</c:if>
		<c:if test="${!empty loginInfo}">
			location.href='write.do';
		</c:if>	
	}
</script>
</head>


<body>

	<div class="sub">
		<div class="size">
			<h3 class="sub_title">게시판${loginInfo.email}</h3>

			<div class="bbs">
				<table class="list">
					<p>
						<span><strong>총 ${data.totalCount}개</strong> | ${BoardVO.page}/${data.totalPage}페이지</span> 
						<!-- 컨트롤러에서 boardVO라는 커맨드객체 -> 소문자사용 -->
					</p>
					<caption>게시판 목록</caption>
					<colgroup>
						<col width="80px" />
						<col width="*" />
						<col width="100px" />
						<col width="200px" />
						<col width="200px" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>조회수</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						
						<c:if test="${empty data}">
							<tr>
								<td class="first" colspan="8">등록된 글이 없습니다.</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty data.list}"><!-- 안써도됨 -->
							<c:forEach items="${data.list }" var="vo" varStatus="status"> <!-- status의 인덱스를 사용하기 위해 -->
									 <tr>
										<td>${data.totalCount - status.index - ((boardVO.page-1)*boardVO.pageRow)}<!-- 계산식 = "총개수 - 인덱스 - (현재 페이지 번호 - 1) * 페이지당 개수" --></td>
										<td class="txt_l" style="text-align:left;">
											<c:forEach begin="1" end="${vo.nested}">&nbsp;&nbsp;&nbsp;</c:forEach><!-- 들여쓰기 -->
											<c:if test="${vo.nested >0 }"><img src="/project/img/answer_icon.gif"></c:if>
											<a href="/project/reply/view.do?no=${vo.no}">${vo.title} [${vo.comment_count}]</a></td>
										<td>${vo.viewcount}</td>
										<td class="writer">${vo.member_name}</td>
										<td class="date"> <fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
									</tr>
							</c:forEach>	
						</c:if>
						
					<!-- 
						<tr>
							<td>9</td>
							<td class="txt_l"><a href="board_view.html">게시글 제목</a></td>
							<td class="writer">홍길동</td>
							<td class="date">2021-01-01</td>
						</tr>
						<tr>
							<td>8</td>
							<td class="txt_l"><a href="board_view.html">게시글 제목</a></td>
							<td class="writer">홍길동</td>
							<td class="date">2021-01-01</td>
						</tr>  
					-->
						
					</tbody>
				</table>
				<div class="btnSet" style="text-align: right;">
				<c:if test="${!empty sessionScope.loginInfo }">
					<a class="btn" href="javascript:goWrite();">글작성 </a>
					</c:if>
				</div>
				<div class="pagenate clear">
					<ul class='paging'>
					<!-- 이전페이지 -->
					<c:if test="${data.prev == true }">
						<li><a href="index.do?page=${data.startPage-1 }&stype=${param.stype}&sword=${param.sword}"><</a></li><!-- 핵심:검색했던 값까지 전달 -->
					</c:if>
					<!-- 페이지별 -->
						<c:forEach var="p" begin="${data.startPage}" end="${data.endPage }">
							<li><a href='index.do?page=${p }' <c:if test="${boardVO.page == p }"> class='current'</c:if>>${p }</a></li>
						</c:forEach>
					<!-- 다음페이지 -->
					<c:if test="${data.next == true }">
						<li><a href="index.do?page=${data.endPage+1 }&stype=${param.stype}&sword=${param.sword}">></a></li> <!-- 핵심:검색했던 값까지 전달 -->
					</c:if>
					</ul>
				</div>

				<!-- 페이지처리 -->
				<div class="bbsSearch">
					<form method="get" name="searchForm" id="searchForm" action="">
						<span class="srchSelect"> <select id="stype" name="stype" class="dSelect" title="검색분류 선택">
								<option value="all">전체</option>
								<option value="title">제목</option>
								<option value="contents">내용</option>
						</select>
						</span> 
						<span class="searchWord"> <input type="text" id="sword" name="sword" value="" title="검색어 입력"> 
						<input type="button" id="" value="검색" title="검색">
						</span>
					</form>

				</div>
			</div>
		</div>
	</div>

</body>
</html>