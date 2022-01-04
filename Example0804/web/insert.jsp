<%-- 
    Document   : show
    Created on : 2014-4-28, 11:41:01
    Author     : Administrator
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="cn.edu.djtu.vo.Person"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

                <form action="SaveServlet">
                    学号：
                    <input type="hidden" name="op" value="insert" />

                    <input type="text" name="id"  /><br/>

                    姓名：<input type="text" name="name"  /><br/>
                    密码：<input type="password" name="pwd"  /><br/>
                    性别：

                            <input type="radio" name="gender" value="男"  />男
                            <input type="radio" name="gender" value="女" checked />女

                    <input type="submit" value="保存" />

                </form>
                <a href="QueryServlet" >返回</a>

    </body>
</html>
