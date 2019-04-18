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
public class listvendor extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet listvendor</title>");            
            out.println("</head>");
            out.println("<body  style=' background-image: url(pict1.jpg)'>");
            try
            {
          
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","");
                
                PreparedStatement ps1=con.prepareStatement("SELECT  * FROM original_vendor;");
               
                ResultSet rs = ps1.executeQuery();
                    
                out.println("<table id=\"myTable\" border=1 width=50% height=50% align=center>"
                        +"<tr>"
                            + "<th>E-Mail </th>"
                            + "<th>Username</th>"
                            +"<th>Address</th>" 
                            +"<th>Phone</th>"
                            +"<th>City</th>"
                            +"<th>State</th>"
                            +"<th>Product Type</th>"
                            +"<th>Liscense No</th>"
                            +"<th>Aadhar Id</th>"
                        + "</tr>");
                 
                 while(rs.next())
                 {
                        
                        out.println("<tr id='add' align=center>"
                        +"<td>"+rs.getString(1)+"</td>"
                        +"<td>"+rs.getString(4)+"</td>"
                        +"<td>"+rs.getString(5)+"</td>"
                        +"<td>"+rs.getInt(6)+"</td>"
                        +"<td>"+rs.getString(7)+"</td>"
                        +"<td>"+rs.getString(8)+"</td>"
                        +"<td>"+rs.getString(9)+"</td>"
                        +"<td>"+rs.getString(10)+"</td>"
                        +"<td>"+rs.getString(11)+"</td>"
                        +"</tr>");
                        
                        /*String mail = rs.getString(1);
                        request.setAttribute("mail",mail);
                        
                        RequestDispatcher rd=request.getRequestDispatcher("vendoradd");
                        rd.forward(request, response);*/
                    }
                 out.println("/<table>");
                 out.println();
                /* out.println("<input type='text' name='name' id='t1'> <button onsubmit='vendor.html' style=\"text-decoration: none;color:skyblue\">ADD</button>");
                 out.println("<script>\n" +
                "function adding() {\n" +
                "  var row = document.getElementById('t1'); window.alert(row.value);" +  "}" +
                "</script>");     
                 
                 out.println("<button><a href='vendor.html'>ADD</a></button>");*/
            
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
