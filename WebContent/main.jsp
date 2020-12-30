<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <table>
        <tr>
            <td>
                안녕하세요. ${loginUser.name}(${loginUser.userid})님
            </td>
        </tr>
        <tr>
            <td>
                email: ${loginUser.email}
            </td>
        </tr>
        <tr>
            <td>
                전화번호 : ${loginUser.phone}
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="logout" onclick="location.href='logout.do'">
                <input type="button" value="modify" onclick="location.href='update.do?userid=${loginUser.userid}'">
                <input type="button" value="quit" onclick="location.href='delete.do?userid=${loginUser.userid}'">
                <c:if test="${loginUser.admin == 1}">
                    <input type="button" value="manage" onclick="location.href='select.do'">
                    <!-- memberselect.do를 거쳐서 멤버리스트와 함께 member/memberSelect.jsp로 이동 -->
                </c:if>
            </td>
        </tr>
        <tr>
        	<td>
        		<span style="font-size:90%;">${message}</span>
        	</td>
        </tr>
    </table>
</body>
</html>