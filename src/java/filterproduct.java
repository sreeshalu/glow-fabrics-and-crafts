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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHSCI5MCA16076
 */
public class filterproduct extends HttpServlet {

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
          
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet filterproduct</title>");            
            out.println("</head>");
            out.println("<body>");
            try
            {
           
               
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","");
                
                PreparedStatement ps1=con.prepareStatement("SELECT  * FROM product_list;");
               
                ResultSet rs = ps1.executeQuery();
                    
                out.println("<form action='update_product'><br><center><table id=\"myTable\" border=1 width=50% height=50% align=center>"
                        +"<tr>"
                            + "<th>PRODUCT ID</th>"
                            + "<th>PRODUCT NAME</th>"
                            + "<th>PRICE</th>"
                            +"<th>QUANTITY</th>" 
                            +"<th>DESCRIPTION</th>"
                            +"<th>VENDOR MAIL</th>"
                            + "</tr>");
                 
                 while(rs.next())
                 {
                        
                        out.println("<tr align=center>"
                        +"<td>"+rs.getString(1)+"</td>"
                        +"<td>"+rs.getString(2)+"</td>"
                        +"<td>"+rs.getString(3)+"</td>"
                        +"<td>"+rs.getString(4)+"</td>"
                        +"<td>"+rs.getString(5)+"</td>"
                        +"<td>"+rs.getString(6)+"</td>"
                        +"</tr>");
                        
                    }
                 out.println("/<table>");
                 
                /* out.println("<form action='org_product'><br><center>" +
"<h3> <label style='color: skyblue'> ENTER THE ID OF PRODUCT TO BE REMOVED : </label></h3><input type='text' name='aid'>\n" +
"<button type=\"submit\">REMOVE</button></center></form>");*/
                 out.println("<form action='rem_product'><br><center>" +
"<h3> <label style='color: skyblue'> ENTER THE ID OF PRODUCT TO BE REMOVED : </label></h3><input type='text' name='rid'>\n" +
"      <button type=\"submit\">REMOVE</button></center></form>");
                 out.println("<form action='itemadd' method='post'><center>"+
                         "<h3> <label style='color: skyblue'> ENTER THE E-MAIL OF VENDOR TO BE ADDED : </label></h3><input type=\"text\" name=\"aid\">\n" +
"<button type=\"submit\">ADD</button><br><br>\n</form>" );
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
