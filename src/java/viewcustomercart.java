/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHSCI5MCA16076
 */
public class viewcustomercart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ServletContext application = getServletConfig().getServletContext();
            String cumail = (String) application.getAttribute("cusmail");             
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet viewcustomercart</title>");            
            out.println("</head>");
            out.println("<body style=' background-image: url(pict8.jpg)'>");
            try
            {

                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","");
                
                PreparedStatement ps1=con.prepareStatement("SELECT  * FROM cart where cmail = ?;");
                ps1.setString(1,cumail );
                ResultSet rs = ps1.executeQuery();
                    
                out.println("<table id=\"myTable\" border=1 width=50% height=50% align=center>"
                        +"<tr>"
                            + "<th>IMAGE</th>"
                            + "<th>PRODUCT ID</th>"
                            + "<th>PRODUCT NAME</th>"
                            + "<th>PRICE</th>"
                            +"<th>QUANTITY</th>" 
                            +"<th>DESCRIPTION</th>"
                        + "</tr>");
                 
                 while(rs.next())
                 {
                        
                        out.println("<tr align=center>"
                        +"<td><img src=\"" + rs.getString(8) + "\" alt='Girl in a jacket' width=\"100\" height=\"100\"> </td>"
                        +"<td>"+rs.getString(1)+"</td>"
                        +"<td>"+rs.getString(2)+"</td>"
                        +"<td>"+rs.getString(3)+"</td>"
                        +"<td>"+rs.getString(4)+"</td>"
                        +"<td>"+rs.getString(5)+"</td>"
                       +"</tr>");
                        
                    }
                 
                 out.println("/<table>");
                 out.println("<form action='shopproduct'><br><center>  \n" +
               "<h3> <label style='color: skyblue'> ENTER THE ID OF PRODUCT TO SHOP : </label></h3><input type='text' name='sid'>\n" +
             "<button type=\"submit\">ADD</button><br></center></form>"
                         + "<form action='remcart'><br><center><br>  \n" +
               "<h3> <label style='color: skyblue'> ENTER THE ID OF PRODUCT TO REMOVE FROM CART : </label></h3><input type='text' name='rid'>   \n" +
                    "<button type=\"submit\">ADD</button><br><br>");
            }
            catch(Exception e)
            {
                out.println(e);
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
