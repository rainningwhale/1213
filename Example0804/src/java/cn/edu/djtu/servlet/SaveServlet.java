package cn.edu.djtu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SaveServlet", urlPatterns = {"/SaveServlet"})
public class SaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //String id = request.getParameter("id");
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
            String op=request.getParameter("op");
            if(op==null) {
                String sql = "update person set name=?,pwd=?,gender=? where id=?";
                prst = conn.prepareStatement(sql);
                prst.setString(1, request.getParameter("name"));
                prst.setString(2, request.getParameter("pwd"));
                prst.setString(3, request.getParameter("gender"));
                prst.setInt(4, Integer.parseInt(request.getParameter("id")));
                int iret = prst.executeUpdate();
                if (iret == 1) {
                    response.sendRedirect("QueryServlet");
                } else {
                    out.print("保存失败！");
                }
            }
            else {
                String sql = "insert into person values(?,?,?,?)";
                prst = conn.prepareStatement(sql);
                prst.setInt(1, Integer.parseInt(request.getParameter("id")));
                prst.setString(2, request.getParameter("name"));
                prst.setString(3, request.getParameter("pwd"));
                prst.setString(4, request.getParameter("gender"));
                int iret = prst.executeUpdate();
                if (iret == 1) {
                    response.sendRedirect("QueryServlet");
                } else {
                    out.print("保存失败！");
                }
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
