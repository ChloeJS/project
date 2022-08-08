<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 22.07.27 추가 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

		<p><span><strong>총 ${comment.totalCount}개</strong>  |  ${commentVO.page }/${comment.totalPage }페이지</span></p>
                    <table class="list">
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="200px" />
                        </colgroup>
                        <tbody>
						<c:if test="${empty comment.list}">
                            <tr>
                                <td class="first" colspan="8">등록된 댓글이 없습니다.</td>
                            </tr>
						</c:if>
                        <c:if test="${!empty comment.list}">
	                        <c:forEach var="vo" items="${comment.list}">
	                            <tr>
	                                <td>${vo.no}</td>
		                                <td class="txt_l" style="text-align:left;">
		                                    ${vo.content } <c:if test="${loginInfo.no == vo.member_no }"> <a href="javascript:commentDel();">[삭제]</a></c:if> 
		                                     <!-- 25열 = 세션에 저장한 loginInfo의 no와  var로 설정한 vo에 있는 no가 같으면  : 내가 쓴 글 -->                             
		                                </td>
	                                <td class="writer"></td>
	                                <td>${vo.member_name}</td>
	                                <td class="date"><fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                            </tr>
	                        </c:forEach>
	                           
                        </c:if>
                        </tbody>
                    </table>
                    
                    <div class="pagenate clear"> <!-- data를 comment로 바꾸는 이유? commentController에서 list.do로 ajax요청하는데 map에 담아 꺼내쓸 수 있게 해놨기 때문에! -->
					<ul class='paging'>
					<!-- 이전페이지 -->
					<c:if test="${comment.prev == true }">
						<li><a href="javascript:getComment(${comment.startPage - 1})"><</a></li>
						<!-- index.do?page=${comment.startPage-1 }&stype=${param.stype}&sword=${param.sword}"을 지운 이유: ajax로 할거니까? -->
					</c:if>
					<!-- 페이지별 -->
						<c:forEach var="p" begin="${comment.startPage}" end="${comment.endPage }">
							<li><a href='javascript:getComment(${p});' <c:if test="${commentVO.page == p }"> class='current'</c:if>>${p }</a></li>
						</c:forEach>
					<!-- 다음페이지 -->
					<c:if test="${comment.next == true }">
						<li><a href="javascript:getComment(${comment.endPage + 1})">></a></li> <!-- 핵심:검색했던 값까지 전달 -->
					</c:if>
					</ul>
				</div>
  	