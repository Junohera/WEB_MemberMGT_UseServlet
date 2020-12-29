<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<script>
    function loginCheck() {
        if (document.frm.userid.value.length <= 3) {
            alert("아이디는 네글자 이상이어야 합니다.");
            frm.userid.focus();
            return false;
        }
        if (document.frm.pwd.value == "") {
            alert("암호는 반드시 입력해야 합니다.");
            frm.pwd.focus();
            return false;
        }
        return true;
    }
</script>
<body>
    <h2>login</h2>
    <form action="login.do" method="POST" name="frm">
        <table>
            <tr>
                <td>id</td>
                <td><input type="text" name="userid" id="userid"></td>
            </tr>
            <tr>
                <td>pass</td>
                <td><input type="password" name="pwd" id="pwd"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="sign in" onClick="return loginCheck();">
                    <input type="button" value="sign up" onclick="location.href='join.do'">
                    <input type="reset" value="cancel">
                </td>
            </tr>
            <tr>
            	<td colspan="2">${message}</td>
            </tr>
        </table>
    </form>
</body>
</html>