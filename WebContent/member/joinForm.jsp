<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
    function joinCheck() {
        if (document.frm.name.value.length == 0) {
            alert("이름을 써주세요");
            frm.name.focus();
            return false;
        }
        if (document.frm.userid.value.length < 4) {
            alert("아이디는 네글자 이상이어야 합니다.");
            frm.userid.focus();
            return false;
        }
        if (document.frm.pwd.value.length == "") {
            alert("암호는 반드시 입력되어야합니다.");
            frm.pwd.focus();
            return false;
        }
        if (document.frm.pwd.value != document.frm.pwd_check.value) {
            alert("암호가 일치하지 않습니다.");
            frm.pwd_check.focus();
            return false;
        }
        if (document.frm.reid.value != document.frm.userid.value) {
            alert("중복체크 오류입니다.");
            frm.userid.focus();
            return false;
        }

        return true;
    };

    function idCheck() {
        /*
            userid란에 사용하고자 하는 아이디를 먼저 입력하고 중복체크 버튼을 누르게 하기 위해
            userid란에 내용이 없으면 아이디를 먼저 입력하라고 메시지를 출력합니다.
        */
       if (document.frm.userid.value.length == 0) {
           alert("아이디를 입력하세요");
           frm.userid.focus();
           return false;
       }

       var inputid = document.frm.userid.value; // 입력한 아이디 추출
       var opt = "toolbar=no, scrollbars=no, resizable=no, width=500, height=200";
       // idcheck.do 에 먼저 갔다가 포워딩되어 온 페이지가 팝업창에 나타납니다.
       window.open("idcheck.do?userid=" + inputid, "idcheck", opt);
    };
</script>
</head>
<body>
    <h2>회원 가입</h2>
    '*' 표시 항목은 필수 입력 항목입니다.
    <form method="POST" action="join.do" name="frm">
	    <table>
	        <tr>
	            <td>name</td>
	            <td>
	                <input type="text" name="name" id="name" size="20">
	                *
	            </td>
	        </tr>
	        <tr>
	            <td>id</td>
	            <td>
	                <input type="text" name="userid" id="userid" size="20">
	                *
	                <input type="button" value="duplicate" onclick="idCheck();">
	                <input type="hidden" name="reid" value="">
	            </td>
	        </tr>
	        <tr>
	            <td>pwd</td>
	            <td>
	                <input type="password" name="pwd" id="pwd" size="20">
	                *
	            </td>
	        </tr>
	        <tr>
	            <td>re pwd</td>
	            <td>
	                <input type="password" name="pwd_check" id="pwd_check" size="20">
	                *
	            </td>
	        </tr>
	        <tr>
	            <td>email</td>
	            <td>
	                <input type="text" name="email" id="email" size="20">
	            </td>
	        </tr>
	        <tr>
	            <td>phone</td>
	            <td>
	                <input type="text" name="phone" id="phone" size="20">
	            </td>
	        </tr>
	        <tr>
	            <td>grade</td>
	            <td>
	                <label for="admin_0">
	                    <input type="radio" name="admin" value="0" id="admin_0" checked> 일반회원
	                </label>
	                <label for="admin_1">
	                    <input type="radio" name="admin" value="1" id="admin_1"> 관리자
	                </label>
	            </td>
	        </tr>
	        <tr>
	            <td colspan="2">
	                <input type="submit" value="send" onclick="return joinCheck();">
	                <input type="reset" value="cancle">
	            </td>
	        </tr>
	    </table>
    </form>
</body>
</html>