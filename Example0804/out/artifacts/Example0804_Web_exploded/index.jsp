<%-- 
    Document   : index
    Created on : 2014-4-28, 11:40:46
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="QueryServlet" method="POST">
            按性别查询：
            <input type="radio" name="gender" value="男" checked/>男
            <input type="radio" name="gender" value="女" />女
            <input type="submit" value="提交" />
        </form>
        <a href="insert.jsp" >新增</a>
    </body>
</html>
