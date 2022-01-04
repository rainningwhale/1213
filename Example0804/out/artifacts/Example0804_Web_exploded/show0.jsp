<%-- 
    Document   : show
    Created on : 2014-4-28, 11:41:01
    Author     : Administrator
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="cn.edu.djtu.vo.Person"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MYJSP Page</title>
        <style>
            table {
                border-collapse: collapse;
            }

            table, td, th {
                border: 1px solid black;
            }
        </style>
    </head>
    <body>
                <%
                    List<Person> plist=(ArrayList<Person>)request.getAttribute("plist");

                    if(plist==null || plist.size()==0) {%>
                没有满足条件的数据！
                <%
                }
                else
                    {
                %>
                <table border="0" align="center">
                    <tr>
                        <th>No.</th>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>密码</th>
                        <th>性别</th>
                        <th>操作</th>
                    </tr>
                    <% int i=1; %>
                    <%
                        for(Person p: plist)
                        {
                    %>

                        <tr>
                            <td><%=i++%></td>
                            <td><%=p.getId()%></td>
                            <td><%=p.getName()%></td>
                            <td><%=p.getPwd()%></td>
                            <td><%=p.getGender()%></td>
                            <td><a href="EditServlet?id=<%=p.getId()%>">修改</a></td>
                        </tr>
                    <% } %>
                </table>
            <% }
            %>
        <a href="index.jsp" >返回</a>

    </body>
</html>
