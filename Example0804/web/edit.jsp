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
            <c:when test="${empty person}">
                没有满足条件的数据！
            </c:when>
            <c:otherwise>
                <form action="SaveServlet">
                    学号：${person.id}<br/>
                    <input type="hidden" name="id" value="${person.id}" />

                    姓名：<input type="text" name="name" value="${person.name}" /><br/>
                    密码：<input type="password" name="pwd" value="${person.pwd}" /><br/>
                    性别：
                    <input type="radio" name="gender" value="男" ${person.gender=="男"?"checked":""} />男
                    <input type="radio" name="gender" value="女" ${person.gender=="女"?"checked":""} />女<br/>
                    部门：
                    <select name="department">
                        <c:forEach items="${department}" var="dep">
                            <option value="${dep}" ${dep==person.department?"selected":""} >${dep}</option>
                        </c:forEach>
                    </select>

<%--                    <c:if test="${person.gender!=\"女\"}" >--%>
<%--                        <input type="radio" name="gender" value="男" checked />男--%>
<%--                        <input type="radio" name="gender" value="女"  />女--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${person.gender==\"女\"}" >--%>
<%--                    <input type="radio" name="gender" value="男"  />男--%>
<%--                    <input type="radio" name="gender" value="女" checked />女--%>
<%--                    </c:if>--%>

                        <%--                    <c:choose>--%>
<%--                        <c:when test="${person.gender==\"男\"}">--%>
<%--                            <input type="radio" name="gender" value="男" checked />男--%>
<%--                            <input type="radio" name="gender" value="女" />女--%>

<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <input type="radio" name="gender" value="男"  />男--%>
<%--                            <input type="radio" name="gender" value="女" checked />女--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
<%--                    <input type="radio" name="gender" value="男" <%= ((Person)request.getAttribute("person")).getGender().equals("男")?"checked":"" %>/>男--%>
<%--                    <input type="radio" name="gender" value="女" <%= ((Person)request.getAttribute("person")).getGender().equals("男")?"":"checked" %> />女--%>

                <%--<input type="text" name="gender" value="${person.gender}" />--%>
<br/>
                    <input type="submit" value="保存" />

                </form>
                <a href="QueryServlet" >返回</a>
            </c:otherwise>
        </c:choose>
    </body>
</html>
