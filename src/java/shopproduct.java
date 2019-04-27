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
public class shopproduct extends HttpServlet {

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
            String id = request.getParameter("sid");
             ArrayList allValues = new ArrayList();
             String val=" ";
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet shopproduct</title>");            
            out.println("</head>");
            out.println("<body style=' background-image: url(pict8.jpg)'>");
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","");
                
                PreparedStatement ps=con.prepareStatement("SELECT  * FROM cart");
               
                ResultSet rs1 = ps.executeQuery();
                while(rs1.next())
                {
                    String pid = rs1.getString(1);
                    allValues.add(pid);
                    
                    
                }
                for(int i=0; i < allValues.size();i++){
                    val=(String) allValues.get(i);
                  
                  if(val==id)
                  {
           
                
                PreparedStatement ps1=con.prepareStatement("insert into shopproduct SELECT * FROM cart WHERE p_id = ? ");
                ps1.setString(1, id);
                ps1.executeUpdate();
                PreparedStatement ps3=con.prepareStatement("DELETE FROM cart WHERE p_id = ?;");
                ps3.setString(1, id);
                ps3.executeUpdate();
                out.println("<html><head><script>window.alert('PURCHASED');</script></head></html>");
                  }
                }
                 for (int j = 0; j < id.length(); j++) {
                char charAt2 = id.charAt(j);
                if (Character.isLetter(charAt2)) {
                    out.println("<html><head><script>window.alert('NUMBERS ONLY');window.location.assign('viewcustomercart');</script></head></html>");
                    
                }
                }
               
                  
                            if( id.equals(""))
                    {
                        out.println("<html><head><script>window.alert('CANNOT BE NULL');window.location.assign('viewcustomercart');</script></head></html>");
                    }
                    
              
                 
                PreparedStatement ps2=con.prepareStatement("SELECT * FROM shopproduct  WHERE p_id = ? ");
                ps2.setString(1, id);
                ResultSet rs = ps2.executeQuery();
                out.println("<form action='payment' method='post'>");
                while(rs.next())
                {
                    String vmail = rs.getString(6);
                    int pid = rs.getInt(1);
                    String name = rs.getString(2);
                    int price = rs.getInt(3);
                    int qty = rs.getInt(4);
                   
                    
                    out.println("<center><h1>PRODUCT DETIALS</h1><br><label>ID :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label><input type='text'value=\"" + pid  +"\"name ='id' ><br>"+
                            "<label>ITEM : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type='text'value=\"" + name +"\"name ='name' ><br>"+
                            "<label>PRICE :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type='text'value=\"" + price +"\"name ='pr' ><br>"+
                            "<label>QUANTITY:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label><input type='text'value=\"" + qty +"\"name ='qty'>"+
                            "<input type='text' name='mail' value=\""+ vmail +"\" style='visibility: hidden; display: none;'><br> ");

                    int total = price * qty;
                    out.println("<label>TOTAL AMOUNT : </label><input type='text'value=\"" + total +"\"name ='total'><br><br>");
                }
          out.println("<button type=\"submit\">GO</button>");
                out.println("</center></form>");

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
