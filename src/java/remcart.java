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
public class remcart extends HttpServlet {

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
             ArrayList allValues = new ArrayList();
             ArrayList allValues1 = new ArrayList();
             int val=0;
             int val1=0;
             String id=request.getParameter("rid");
             out.println(id);
             ArrayList allValues2 = new ArrayList();
             String val2=" ";
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet remcart</title>");            
            out.println("</head>");
            out.println("<body>");
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","");
         //   PreparedStatement ps6=con.prepareStatement("SELECT  * FROM cart");
         
         for (int i = 0; i < id.length(); i++) {
                char charAt2 = id.charAt(i);
                if (Character.isLetter(charAt2)) {
                    out.println("<html><head><script>window.alert('NUMBERS ONLY');window.location.assign('viewcustomercart');</script></head></html>");
                    
                }
                }
               
                    if( id.equals(""))
                    {
                        out.println("<html><head><script>window.alert('CANNOT BE NULL');window.location.assign('viewcustomercart');</script></head></html>");
                    }
               
             
                    PreparedStatement ps1=con.prepareStatement("SELECT * FROM cart WHERE p_id = ? ");
                     ps1.setString(1, id);
                    ResultSet rs = ps1.executeQuery();
                    while(rs.next())
                    {
                        int totqty=rs.getInt(4);
                        allValues.add(totqty);
                    }
                    for(int j=0; j < allValues.size();j++)
                    {
                        val=(int)allValues.get(j);
                    }
                    //out.println(val);
                    PreparedStatement ps3=con.prepareStatement("SELECT * FROM original_product WHERE p_id = ? ");
                    ps3.setString(1, id);
                    ResultSet rs2 = ps3.executeQuery();
                    while(rs2.next())
                    {
                        int qty=rs2.getInt(4);
                        allValues1.add(qty);
                    }
                    for(int k=0; k < allValues1.size();k++)
                    {
                        val1=(int)allValues1.get(k);
                    }
                    //out.println(val1);

                    int curval=val+val1;
                    out.println(curval);
                    PreparedStatement ps4=con.prepareStatement("update original_product set quantity=? where p_id=?");
                    ps4.setInt(1, curval);
                    ps4.setString(2,id);
                    ps4.executeUpdate();
                
                    PreparedStatement ps5=con.prepareStatement("DELETE FROM cart WHERE p_id = ?;");
                    ps5.setString(1, id);
                    ps5.executeUpdate();
                    out.println("<html><head><script>window.alert('ITEM REMOVED FROM CART');window.location.assign('viewcustomercart');</script></head></html>");
                  
                
                
                
               
            
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
