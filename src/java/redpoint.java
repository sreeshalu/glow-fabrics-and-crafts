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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHSCI5MCA16076
 */
public class redpoint extends HttpServlet {

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
            ArrayList allValues = new ArrayList();
            ArrayList allValues1 = new ArrayList();
             double sum=0,point=0,newcash=0;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet totalpayment</title>");            
            out.println("</head>");
            out.println("<body style=' background-image: url(pict8.jpg)'>");
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","");
                
                PreparedStatement ps2=con.prepareStatement("SELECT * FROM totalpayment  WHERE cmail = ? ");
                ps2.setString(1, cumail);
                ResultSet rs = ps2.executeQuery();
                
                while(rs.next())
                {
                    double total=rs.getDouble(2);
                    allValues.add(total);
                }
                for(int i=0; i < allValues.size();i++){
                    double val=(double) allValues.get(i);
                  sum=sum+val;
           
                }
                
               out.println(sum);
               
               
                  PreparedStatement ps3=con.prepareStatement("SELECT * FROM payment  WHERE custmail = ? ");
                ps3.setString(1, cumail);
                ResultSet rs1 = ps3.executeQuery();
                
                 while(rs1.next())
                {
                    double pot=rs1.getDouble(6);
                    allValues1.add(pot);
                }
                for(int i=0; i < allValues1.size();i++){
                    double val1=(double) allValues1.get(i);
                  point=point+val1;
           
                }
                out.println(point);
                
                if(sum>point)
                {
                    newcash=0;
                    newcash=sum-point;
                    out.println(newcash);
                    
                PreparedStatement ps=con.prepareStatement("update  payment set point=0 ,total=? WHERE custmail = ? ");
                ps.setString(2, cumail);
                ps.setDouble(1, newcash);
                  ps.executeUpdate();  
                }
                else
                {
                    newcash=0;
                    newcash=point-sum;
                    point=point-newcash;
                    
                   PreparedStatement ps1=con.prepareStatement("update  payment set point=?,total=?  WHERE custmail = ? ");
                   ps1.setDouble(1,point);
                   ps1.setDouble(2, newcash);
                ps1.setString(3, cumail);
                  ps1.executeUpdate();  
                    
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
