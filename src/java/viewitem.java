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
public class viewitem extends HttpServlet {

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
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet viewitem</title>");            
            out.println("</head>");
            out.println("<body style=' background-image: url(pict8.jpg)'>");
            
            try
            {
           
               
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","");
                
                PreparedStatement ps1=con.prepareStatement("SELECT  * FROM original_product;");
               
                ResultSet rs = ps1.executeQuery();
                    
               out.println("<table id=\"myTable\"style=\"border: 1px solid black;border-collapse: collapse;\" width=100% height=100% align=center>"
                        +"<tr>"
                            +"<th>IMAGE </th>"
                            + "<th>PRODUCT ID</th>"
                            + "<th>PRODUCT NAME</th>"
                            + "<th>PRICE</th>"
                            +"<th>QUANTITY</th>" 
                            +"<th>DESCRIPTION</th>"
                        + "</tr>");
                 
                 while(rs.next())
                 {
                        
                     /*out.println("<img src=\""+rs.getString(8)+ "\" height=\"100\" width=\"100\"><br>"+
                             "<input  value=\""+rs.getString(1)+"\"  style=\"background-color: transparent; border-color: transparent\"><br>"+
                             "<input  value=\""+rs.getString(2)+"\"  style=\"background-color: transparent; border-color: transparent\"><br>"+
                             "<input  value=\""+rs.getString(3)+"\"  style=\"background-color: transparent; border-color: transparent\"><br>"+ 
                             "<input  value=\""+rs.getString(4)+"\"  style=\"background-color: transparent; border-color: transparent\"><br>"+         
                             "<input  value=\""+rs.getString(5)+"\"  style=\"background-color: transparent; border-color: transparent\">");*/
                       out.println("<tr align=center>"
                        +"<td><img src=\"" + rs.getString(8) + "\" alt='Girl in a jacket' width=\"150\" height=\"150\"> </td>"
                        +"<td>"+rs.getString(1)+"</td>"
                        +"<td>"+rs.getString(2)+"</td>"
                        +"<td>"+rs.getString(3)+"</td>"
                        +"<td>"+rs.getString(4)+"</td>"
                        +"<td>"+rs.getString(5)+"</td>"
                        +"</tr>");
                        
                       
                    }
                 
                 out.println("/<table>");
                 out.println( "<form action='addtocart'><br><center> "+
"<h3> <label style='color: skyblue'> ENTER THE ID OF PRODUCT TO BE ADDED TO CART : </label></h3><br><input type='text' name='cid' required>"
                         + "<h3> <label style='color: skyblue'> ENTER THE QUANTITY OF PRODUCT TO BE ADDED TO CART : </label></h3><br><input type='number' name='qty' min=\"1\" max=\"1000\" required>" +
"<button type=\"submit\">ADD</button><br></center></form>");
                 
            }
            catch(Exception e)
            {
                out.println(e);
            }
             //out.println("<center><input type='text' name='id'><a href='viewcart' style='text-decoration: none;color:skyblue'>ADD</a></center>");
            
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
