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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHSCI5MCA16076
 */
public class itemadd extends HttpServlet {

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
            String id = request.getParameter("aid");
            String img=request.getParameter("img");
             ArrayList allValues = new ArrayList();
             String val=" ";

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet itemadd</title>");            
            out.println("</head>");
            out.println("<body>");
            try
            {
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","");
                
                 for (int i = 0; i < id.length(); i++) {
                char charAt2 = id.charAt(i);
                if (Character.isLetter(charAt2)) {
                    out.println("<html><head><script>window.alert('NUMBERS ONLY');window.location.assign('filterproduct');</script></head></html>");
                    
                }
                }
               
                    if( id.equals(""))
                    {
                        out.println("<html><head><script>window.alert('CANNOT BE NULL');window.location.assign('filterproduct');</script></head></html>");
                    }
               

                
                PreparedStatement ps3=con.prepareStatement("SELECT  * FROM product_list");
               
                ResultSet rs = ps3.executeQuery();
                while(rs.next())
                {
                    String pid = rs.getString(1);
                    allValues.add(pid);
                    
                    
                }
                for(int i=0; i < allValues.size();i++){
                    val=(String) allValues.get(i);
                  
                  
                
                PreparedStatement ps1=con.prepareStatement("INSERT INTO original_product  SELECT * FROM product_list WHERE p_id = ?;");
                ps1.setString(1, id);
                
                ps1.executeUpdate();
   
                PreparedStatement ps=con.prepareStatement("update original_product  set image=? where p_id=?");
                ps.setString(1, img);
                ps.setString(2, id);
                ps.executeUpdate();
                
                PreparedStatement ps2=con.prepareStatement("DELETE FROM product_list WHERE p_id = ?;");
                ps2.setString(1, id);
                
                ps2.executeUpdate();
                out.println("<html><head><script>window.alert('RECORD ADDED');window.location.assign('filterproduct');</script></head></html>");
                  
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
