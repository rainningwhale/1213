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
        <c:choose >
            <c:when test="${empty plist}">
                没有满足条件的数据！
            </c:when>
            <c:otherwise>
                <table border="1" align="center">
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>密码</th>
                        <th>性别</th>
                    </tr>
                    <c:forEach var="p" items="${plist}">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.name}</td>
                            <td>${p.pwd}</td>
                            <td>${p.gender}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>
