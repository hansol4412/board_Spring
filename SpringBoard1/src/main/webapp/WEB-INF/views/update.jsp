<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <SCRIPT LANGUAGE="JavaScript">
        function submitForm(mode) {
            if (mode == "update") {
                fm.action = "write?key=UPDATE";
            } else if (mode == "delete") {
                fm.action = "write?key=DELETE";
            }
            fm.submit();
        }
    </SCRIPT>
</head>

<body>
    <div class="container">
    <div class="row-fluid">    
    <h1 class="display-2 text-center">Update</h1><br>
    <FORM METHOD=POST name='fm'>
        <table class="table">
        	<tr><td><b>게시판</b></td>
            <td>
            	<c:choose>
						<c:when test="${board.board.id eq 1}">
							공지게시판<input type='hidden' name='boardId' value=${board.board.id} readonly>
						</c:when>
						<c:when test="${board.board.id eq 2}">
							과제게시판<input type='hidden' name='boardId'  value=${board.board.id} readonly>
						</c:when>
						<c:otherwise>
							자유게시판<input type='hidden' name='boardId'  value=${board.board.id} readonly>
						</c:otherwise>
				</c:choose>
            </td>
            </tr>
        	<tr><td><b>번호</b></td>
            <td><input type='text' name='id' class='form-control' aria-label='Sizing example input' aria-describedby='inputGroup-sizing-sm' value=${board.id} readonly></td>
            </tr>
            <tr><td><b>제목</b></td>
            <td><input type=text name=title class='form-control' aria-label='Sizing example input' aria-describedby='inputGroup-sizing-sm'  maxlength=70 value=${board.title}></td>
            </tr>
            <tr><td><b>일자</b></td>
            <td><input type=hidden name=date value=${board.date}>${board.date}</td>
            </tr>
            <tr><td><b>내용</b></td>
            <td><textarea class='form-control' aria-label='Sizing example input' aria-describedby='inputGroup-sizing-sm' style=' height: 250px;' name=content>${board.content}</textarea></td>
            </tr>
        </table>
        <div class="float-end">
                   <input type=button value="취소" class="btn btn-primary btn-sm" OnClick="location.href='list?boardId=${board.board.id}'">
                   <input type=button value="수정" class="btn btn-secondary btn-sm" OnClick="submitForm('update')">
                   <input type=button value="삭제" class="btn btn-warning btn-sm" OnClick="submitForm('delete')">
        </div>
    </FORM>
    </div>
    </div>
</body>
</html>