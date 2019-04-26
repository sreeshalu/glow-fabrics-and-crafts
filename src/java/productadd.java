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
public class productadd extends HttpServlet {

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
            //String id = request.getParameter("id");
            int id = 0;
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String qty = request.getParameter("qty");
            String des = request.getParameter("des");
            PreparedStatement ps1 = null;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet productadd</title>");            
            out.println("</head>");
            out.println("<body>");
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","");
                
                String vemail = (String) application.getAttribute("vendormail");
                //this.stmt.executeUpdate("CREATE TABLE testBug3873 (keyField INT NOT NULL PRIMARY KEY AUTO_INCREMENT, dataField VARCHAR(32))");

                 ps1=con.prepareStatement("INSERT INTO product_list values(?,?,?,?,?,?,?,?) ",PreparedStatement.RETURN_GENERATED_KEYS);
                
                
                ps1.setInt(1, id);
                ps1.setString(2, name);
                ps1.setString(3, price);
                ps1.setString(4, qty);
                ps1.setString(5, des);
                ps1.setString(6, vemail);
                ps1.setString(7, des);
                ps1.setString(8, name);
                id++;
                int row = ps1.executeUpdate();
            if (row > 0) {
               out.println("<html><head><script>window.alert('RECORD ADDED');window.location.assign('addproduct.html');</script></head></html>");
               out.println(row);
            }
            
                
                
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
