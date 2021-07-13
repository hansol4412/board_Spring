<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.*, javax.sql.*, java.net.*, java.io.* ,java.util.List" %>
<html>
<head> 
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<div class="container">
<div class="row-fluid">
    <SCRIPT LANGUAGE="JavaScript">
        function submitForm(mode) {
            if (mode == "insert") {
                document.getElementById("fmI").action = "reply_write.jsp?key=insert";
                document.getElementById("fmI").submit();
            } 
        }
        function checkReply(mode, gongjiId, replyId, replyContent){
            document.write("<FORM METHOD=POST id='fmC'>");
            document.write("<input type=hidden id=reply name= replyContent value="+replyContent+">");
            document.write("<input type=hidden name= gongjiId value="+gongjiId+">");
            document.write("<input type=hidden name= replyId value="+replyId+">");
            document.write("</FORM>");
            
            var fmC = document.getElementById("fmC");
            if(mode=='delete'){
                if (confirm("정말 삭제하시겠습니까??") == true){ 
                    fmC.action = "reply_write.jsp?key=delete";
                    fmC.submit();
                } else {   //취소
                    return;
                }
            } else if(mode =='update'){
                 var text = prompt("댓글을 수정하시겠습니까",replyContent);
                 if(text==null || text==""){
                     return;
                 } else{
                    document.getElementById("reply").value = text;
                    fmC.action = "reply_write.jsp?key=update";
                    fmC.submit();
                 }
            }
        }
    </SCRIPT>
</head>

<body>

    <%	
    	/*
    	int bno = Integer.parseInt(request.getParameter( "key" ));
        BoardItemService crud = new BoardItemServiceImpl();
        BoardItem board = crud.read(bno);
        ReplyService crudR = new ReplyServiceImpl();
        List<Reply> replyList = crudR.getList(bno);
                	    
        int cnt = board.getViewcnt(); //조회수 증가
        crud.viewCnt(board.getId(), cnt);
                	    
        String replyId = null;
        replyId = crudR.getLastReplyId(board.getId()) +"";

        request.setAttribute("board", board);
        request.setAttribute("bno", bno);
        request.setAttribute("replyList", replyList);
        request.setAttribute("replyId", replyId);
        */
    %>
    
			<h1 class="display-2 text-center">View</h1>
            <table class="table">
                    <tr>
                    <td><b>게시판</b></td>
                    <td>
                    <c:choose>
						<c:when test="${board.board.id eq 1}">
							공지게시판
						</c:when>
						<c:when test="${board.board.id eq 2}">
							과제게시판
						</c:when>
						<c:otherwise>
							자유게시판
						</c:otherwise>
					</c:choose>
					</td>
                    </tr>
                    
                    <tr>
                    <td><b>번호</b></td>
                    <td>${board.id}</td>
                    </tr>

                   	<tr>
                    <td><b>제목</b></td>
                    <td>${board.title}</td>
                    </tr>

                    <tr>
                    <td><b>일자</b></td>
                    <td>${board.date}</td>
                    </tr>

                    <tr>
                    <td><b>내용</b></td>
                    <td>${board.content}</td>
                    </tr>

                    <tr> 
                    <td><b>댓글</b></td>
                    <td>
                    <table>
                    <c:forEach var="replyItem" items="${replyList}">
			        	<tr>
                        <td>${replyItem.content}</td>
                        <td><button class='btn btn-success btn-sm' OnClick=checkReply('update',${board.id},${replyItem.replyId},'${replyItem.content}')>수정</button></td>
                        <td><button class='btn btn-success btn-sm' OnClick=checkReply('delete',${board.id},${replyItem.replyId},'${replyItem.content}')>삭제</button></td>
                        <td>(${replyItem.date})</td>
                        </tr>
         			</c:forEach>
         			</table>
         			<FORM METHOD=POST id='fmI'>
                    <input type=text name='replyContent'>
                    <input type=hidden name=gongjiId value=${board.id}>
                    <c:choose> 
						<c:when test="${replyId eq 0}">
							<input type=hidden name=replyId value=1>
						</c:when>  
						<c:otherwise>
							<fmt:parseNumber var="replyIdInt" integerOnly="true" type="number" value="${replyId}" />
							<c:set var="replyIdInt" value="${replyIdInt+ 1}"/>
                            <input type=hidden name=replyId value=${replyIdInt}>
						</c:otherwise> 
					</c:choose>
            	<input type=button value=댓글등록 class='btn btn-secondary' OnClick=submitForm('insert')>
            	</FORM>
			</td>
            </tr>
        </table>
        </table>
        <table width=650>
            <td width=600></td>
            <td><input type=button value="목록"  class="btn btn-primary" OnClick="location.href='list?boardId=${board.board.id}'"></td>
            <td><input type=button value='수정'  class='btn btn-primary' OnClick=location.href='update?key=${board.id}'></td>
        </table>
    </div>
    </div>
</body>
</html>