<%-- 
    Document   : show
    Created on : 2014-4-28, 11:41:01
    Author     : Administrator
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="cn.edu.djtu.vo.Person"%>
<%@page import="java.cn.edu.djtu.vo" %>
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
                        <th>No</th>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>密码</th>
                        <th>性别</th>
                        <th>部门</th>
                        <th>操作</th>
                    </tr>
<%--                    <% int i=1; %>--%>
                    <c:forEach var="p" varStatus="no" items="${plist}">
                        <tr>
<%--                            <td><%=i++%></td>--%>
                            <td>${no.count}</td>
                            <td>${p.id}</td>
                            <td>${p.name}</td>
                            <td>${p.pwd}</td>
                            <td>${p.gender}</td>
                            <td>${p.department}</td>
                            <td><a href="EditServlet?id=${p.id}">修改</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <c:forEach var="s" begin="1" end="${pnum}" >

            <c:if test="${s==ino}">
                <b>${s}</b>
            </c:if>
            <c:if test="${s!=ino}">
                 <a href="QueryServlet?pno=${s}" > ${s}</a>
            </c:if>

        </c:forEach>
        <a href="index.jsp" >返回</a>

    </body>
</html>
