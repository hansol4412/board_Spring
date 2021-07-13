<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <SCRIPT LANGUAGE="JavaScript">
        function submitForm(mode) {
            fm.action = "write?key=INSERT";
            fm.submit();
        }
    </SCRIPT>
</head>
<body>
<div class="container">
<div class="row-fluid">
    <h1 class="display-2 text-center">Insert</h1>
    <FORM METHOD=POST name='fm'>
        <table class="table">
        	 <tr>
                <td width ="100"></td>
                <td width ="100"><b>게시판</b></td>
                <td>
                	<c:choose>
						<c:when test="${boardId eq 1}">
							공지게시판<input type=hidden name=boardId value='1'>
						</c:when>
						<c:when test="${boardId eq 2}">
							과제게시판<input type=hidden name=boardId value='2'>
						</c:when>
						<c:otherwise>
							자유게시판<input type=hidden name=boardId value='3'>
						</c:otherwise>
					</c:choose>
				</td>
            </tr>
            <tr>
                <td width ="100"></td>
                <td width ="100"><b>번호</b></td>
                <td>신규(insert)<input type=hidden name=id value='0'></td>
            </tr>
            <tr>
                <td width ="100"></td>
                <td width ="100">제목</b></td>
                <td><input type=text name=title class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" size=70 maxlength=70></td>
            </tr>
            <tr>
                <td width ="100"></td>
                <td width ="100"><b>일자</b></td>
                <td>${date}<input type=hidden name=date  value='${date}'></td>
            </tr>
            <tr>
                <td width ="100"></td>
                <td width ="100"><b>내용</b></td>
                <td><textarea style='height: 250px;' name=content class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" ></textarea></td>
        </table>
        <div class="float-end">
                    <input type=button value="취소" class="btn btn-primary" OnClick="location.href='list?boardId=${boardId}'">
                    <input type=button value="쓰기" class="btn btn-primary" OnClick="submitForm('write')">
        </div>
    </FORM>
</div>
</div>
</body>
</html>