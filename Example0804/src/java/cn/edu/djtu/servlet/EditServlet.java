package cn.edu.djtu.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "EditServlet", urlPatterns = {"/EditServlet"})
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "root";

//        String url = "jdbc:derby://localhost:1527/sample";
//        String driver = "org.apache.derby.jdbc.ClientDriver";
//        String user = "app";
//        String password = "app";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select name from department order by id";
            prst = conn.prepareStatement(sql);
            //prst.setString(1, id);
            rs = prst.executeQuery();
            String sresult="";
            while (rs.next()) {
                sresult+=","+rs.getString("name");
            }
            request.setAttribute("department", sresult);
            rs.close();
            prst.close();
            sql = "select * from person where id=?";
            prst = conn.prepareStatement(sql);
            prst.setString(1, id);
            rs = prst.executeQuery();
            if (rs.next()) {
                cn.edu.djtu.vo.Person person = new cn.edu.djtu.vo.Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setPwd(rs.getString("pwd"));
                person.setGender(rs.getString("gender"));
                person.setDepartment(rs.getString("department"));

                request.setAttribute("person", person);
                request.getRequestDispatcher("edit.jsp").forward(request, response);

            }
            else {
                out.print("该人不存在！");
            }


        } catch (SQLException ex) {
            Logger.getLogger(cn.edu.djtu.servlet.QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cn.edu.djtu.servlet.QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cn.edu.djtu.servlet.QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (prst != null) {
                try {
                    prst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cn.edu.djtu.servlet.QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(cn.edu.djtu.servlet.QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
