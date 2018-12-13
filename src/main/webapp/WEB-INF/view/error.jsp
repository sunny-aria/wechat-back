<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2018/6/4
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>警告</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="alert alert-danger alert-dismissable">
    <button type="button" class="close" data-dismiss="alert"
            aria-hidden="true">
        &times;
    </button>
    <p>错误！请进行一些更改。
        错误链接：${url}
        错误信息：${exception}</p>
</div>

</body>
</html>
