/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHSCI5MCA16076
 */
public class addtocart extends HttpServlet {

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
            String id =request.getParameter("cid");
           int qty = Integer.parseInt(request.getParameter("qty"));
            int qty1 = Integer.parseInt(request.getParameter("qty"));
            String qt = request.getParameter("qty");
            ArrayList allValues = new ArrayList();
            int val=0;
            
         
             ArrayList allValues2 = new ArrayList();
          
             int val2=0;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addtocart</title>");            
            out.println("</head>");
            out.println("<body>");
            try
            {
                
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","");
                
                
                 for (int j= 0; j < id.length(); j++) {
                char charAt2 = id.charAt(j);
                if (Character.isLetter(charAt2)) {
                    out.println("<html><head><script>window.alert('NUMBERS ONLY');window.location.assign('viewitem');</script></head></html>");
                }
                 }
                 for (int j= 0; j < qt.length(); j++) {
                char charAt2 = qt.charAt(j);
                if (Character.isLetter(charAt2)) {
                    out.println("<html><head><script>window.alert('NUMBERS ONLY');window.location.assign('viewitem');</script></head></html>");
                }
                 }
                 
                 /*for (int j= 0; j < id.length(); j++) {
                     char charAt2 = id.charAt(j); 
                     for (int k= 0; k < qt.length(); k++) {
                          
                           char charAt1 = qt.charAt(k);
                           if((Character.isLetter(charAt2))&&(Character.isLetter(charAt1)))
                           {
                               out.println("<html><head><script>window.alert('NUMBERS ONLY');window.location.assign('viewitem');</script></head></html>");
                }
                         
                     }
                 }*/
                     
                 
                 
                PreparedStatement ps5=con.prepareStatement("SELECT  * FROM original_product");
               
                ResultSet rs1 = ps5.executeQuery();
                while(rs1.next())
                {

                    int pqty = rs1.getInt(4);
                    
                    allValues2.add(pqty);
                }
                
              
                     for(int k=0; k < allValues2.size();k++){
                   
                    val2=(Integer) allValues2.get(k);

                     }
                    out.println(val2);
                    out.println(qty1);
                 
            
                PreparedStatement ps1=con.prepareStatement("insert into cart SELECT * FROM original_product WHERE p_id = ? ");
                ps1.setString(1, id);
                ps1.executeUpdate();
                
                PreparedStatement ps3=con.prepareStatement("SELECT * FROM original_product WHERE p_id = ? ");
                ps3.setString(1, id);
                ResultSet rs = ps3.executeQuery();
                while(rs.next())
                {
                    int totqty=rs.getInt(4);
                    allValues.add(totqty);
                }
                for(int c=0; c < allValues.size();c++)
                {
                    val=(int)allValues.get(c);
                }
                
                
                PreparedStatement ps=con.prepareStatement("update cart set cmail=? , quantity=? where p_id=?");
                ps.setString(1, cumail);
                ps.setInt(2, qty);
                ps.setString(3, id);
                ps.executeUpdate();
                
                int curval=0;
                curval=val-qty;
                PreparedStatement ps4=con.prepareStatement("update original_product set quantity=? where p_id=?");
                ps4.setInt(1, curval);
                ps4.setString(2,id);
                ps4.executeUpdate();
                
                if(curval<0)
                {
                    PreparedStatement ps6=con.prepareStatement("update original_product set quantity=? where p_id=?");
                ps6.setInt(1,0);
                ps6.setString(2,id);
                ps6.executeUpdate();
                }
                out.println("<html><head><script>window.alert('ADDED TO CART');window.location.assign('viewitem');</script></head></html>");
              
            
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