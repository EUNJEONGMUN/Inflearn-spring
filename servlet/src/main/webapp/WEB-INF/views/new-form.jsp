<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<!-- 예를 들어
     http://localhost:8080/servlet-mvc/members/new-form 에서
     http://localhost:8080/servlet-mvc/members/save 가 됨
     만약 상대경로가 아니라 절대경로 (/save)로 했다면
     http://localhost:8080/save 로 이동하게 됐을 것임-->

<form action="save" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>