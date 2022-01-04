/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.djtu.servlet;

import cn.edu.djtu.vo.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "QueryServlet", urlPatterns = {"/QueryServlet"})
public class QueryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String gender = request.getParameter("gender");

        String pno = request.getParameter("pno");
        HttpSession sess=request.getSession();
        int ino=1;
        if(pno==null) {
            if(sess.getAttribute("ino")!=null)
                ino = Integer.parseInt(sess.getAttribute("ino").toString());
            else
                ino = 1;
        }
        else {
            sess.setAttribute("ino", pno);
            ino= Integer.parseInt(pno);
        }
//        if(pno!=null)
//         ino= Integer.parseInt(pno);

        if(gender==null)
        {
            if(sess.getAttribute("gender")!=null)
                gender=sess.getAttribute("gender").toString();
            else
                gender="男";

        }
        else
        {
            sess.setAttribute("gender",gender);
        }
        request.setAttribute("ino",ino);
        int ipcount=3;
        int imin=(ino-1)*ipcount+1;
        int imax=(ino)*ipcount;

        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String driver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String password = "daaiALICE";

//        String url = "jdbc:derby://localhost:1527/sample";
//        String driver = "org.apache.derby.jdbc.ClientDriver";
//        String user = "app";
//        String password = "app";
        try {
            List<Person> plist = new ArrayList<Person>();
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select count(*) as num from person where gender=?";
            prst = conn.prepareStatement(sql);
            prst.setString(1, gender);
            rs = prst.executeQuery();

            int i=0;
            if(rs.next())
            {
                i=rs.getInt("num");
            }
            rs.close();
            prst.close();

            sql = "select * from person where gender=? limit ?,?";
            prst = conn.prepareStatement(sql);
            prst.setString(1, gender);
            prst.setInt(2, (ino-1)*ipcount);
            prst.setInt(3, ipcount);
            rs = prst.executeQuery();
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setPwd(rs.getString("pwd"));
                person.setGender(rs.getString("gender"));
                person.setDepartment(rs.getString("department"));
                plist.add(person);
            }

            request.setAttribute ("pnum",(i+ipcount-1)/ipcount);
            //if (plist.isEmpty()) {
                //out.print("该人不存在！");
            //} else {
                request.setAttribute("plist", plist);
                request.getRequestDispatcher("show.jsp").forward(request, response);
            //}

        } catch (SQLException ex) {
            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (prst != null) {
                try {
                    prst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
