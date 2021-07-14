<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*, javax.sql.*, java.net.*, java.io.*,java.text.*, java.util.Date, java.util.List" %>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <SCRIPT>
        function findContent(){
            var findc = document.getElementById("findc").value;
            if((findc == null) || (findc == "")){
                return;
            } else {
                document.getElementById("find").action = "list";
                document.getElementById("find").submit();
            }
        }

        function encodeURI(obj){
             var uri = $(obj).attr('href');
             var encoded = encodeURI(uri);
             $(obj).attr("href", encoded);
        }
    </SCRIPT>
    <style>
    	#noBlue{
    		text-decoration:none;
    		color: black;
    	}
    </style>
</head>
<body>
    <div class="container">
    <div class="row-fluid">
    <%
    	Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                        
                        /*
                        String criteria = request.getParameter( "criteria" );
                        String find = request.getParameter("find");
                        String boardId = request.getParameter("boardId");
                        
                        String pageno = request.getParameter("pageno");
                        
                        BoardItemService crud = new BoardItemServiceImpl();
                    	ReplyService crudR = new ReplyServiceImpl();

                        Page pageParameter = crud.pagination(pageno, criteria, find, boardId);
                    	
                    	List<BoardItem> boardList;
                    	if(criteria == null || find == null){
                    		boardList = crud.getListWithPaging(pageno, boardId);
                    	} else{
                    		boardList = crud.findListWithPaging(pageno, criteria, find, boardId);
                    	}
                    	
                    	for(BoardItem board: boardList) {
                    		board.setCommentcnt(crudR.getTotalCount(board.getId()));
                    	}
                    	*/
                    	
                        //request.setAttribute("boardList", boardList);
                        //request.setAttribute("date", sd.format(date));
                        //request.setAttribute("criteria", criteria);
                        //request.setAttribute("find", find);
                        //request.setAttribute("pageParameter", pageParameter);
                        //request.setAttribute("Itemtotalcount", crud.getTotalCount(boardId));
                        //request.setAttribute("boardId", boardId);
    %>
    
		<h1 class="display-2 text-center">Board</h1>
         <ul class="nav nav-tabs">
	    	<li class="nav-item active">
	            <a class="nav-link" href="/SpringBoard1/main">HOME</a>
	        </li>
	        <li class="nav-item active">
	            <a class="nav-link" href="/SpringBoard1/list?boardId=1">공지게시판</a>
	        </li>
	        <li class="nav-item ">
	            <a class="nav-link" href="/SpringBoard1/list?boardId=2">과제게시판</a>
	        </li>
	        <li class="nav-item">
	            <a class="nav-link" href="/SpringBoard1/list?boardId=3">자유게시판</a>
	        </li>
		</ul>
        <br><br>
    <table class="table table-hover" width=600 >
        <thead>
        <tr class="table-secondary">
            <td width=50>
                <p align=center>번호</p>
            </td>
            <td width=500>
                <p align=center>제목</p>
            </td>
            <td width=100>
                <p align=center>조회수</p>
            </td>
            <td width=100>
                <p align=center>등록일</p>
            </td>
        </tr> 
        </thead>
        <c:forEach var="boardItem" items="${boardList}">
        	<tr>
	        <td width=50>
	        	<p align=center>${boardItem.id}</p>
	        </td>
	        <td width=500>
	        	<c:choose> 
					<c:when test="${boardItem.date eq date}">
						<p align=center><a id='noBlue' href='view.jsp?key=${boardItem.id}'>${boardItem.title}  (${boardItem.commentcnt})  [New]</a></p>
					</c:when>  
					<c:otherwise>
						<p align=center><a id='noBlue' href='view.jsp?key=${boardItem.id}'>${boardItem.title} (${boardItem.commentcnt})  </a></p>
					</c:otherwise> 
				</c:choose>
	        </td>
	        <td width=100>
	        <p align=center>${boardItem.viewcnt}</p>
	        </td>
	        <td width=100>
	        <p align=center>${boardItem.date}</p>
	        </td>
	        </tr>
         </c:forEach>
    </table>
	<div class="float-end">
                <form id='find' class="row row-cols-lg-auto g-3 align-items-center">
                    <div class="col-12">
	                    <select class="form-select form-select-default mb-1" aria-label=".form-select-lg example" name="criteria">
	                        <option value="title" />제목</option>
	                        <option value="content" />내용</option>
	                    </select>
                    </div>
                    <div>
	                    <input type=text name="find" id ='findc' class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
	                    <input type=hidden name="boardId" value=${boardId}> 
	                </div>
	                <div class="col-12">
	                    <button class="btn btn-primary" OnClick ="findContent()">찾기 </button>
                    </div>
                <form>
                <div class="float-end">
                    <input type=button  class="btn btn-success" value=" 신규" OnClick="window.location='insert?boardId=${boardId}'">
       			</div>
    </div>
    <div>
    <div>

</body>
</html>