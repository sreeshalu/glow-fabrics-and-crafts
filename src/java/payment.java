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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHSCI5MCA16076
 */
@WebServlet(urlPatterns = {"/payment"})
public class payment extends HttpServlet {

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
            ServletContext application = getServletConfig().getServletContext();
            String cumail = (String) application.getAttribute("cusmail");
            String vmail = request.getParameter("mail");
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("pr"));
            int quant = Integer.parseInt(request.getParameter("qty"));
            int total = Integer.parseInt(request.getParameter("total"));
            
            double  point = 0.05*total;
            double ad_amt = 0.35*total;
            double ve_amt = 0.65*total;
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet payment</title>");            
            out.println("</head>");
            out.println("<body>");
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","");
                /*PreparedStatement ps2=con.prepareStatement("SELECT * FROM cart WHERE p_id = ? ");
                ps2.setInt(1, id);
                ResultSet rs = ps2.executeQuery();
                while(rs.next())
                {
                   
                    
                }
                //out.println(vmail);*/
                
                PreparedStatement ps=con.prepareStatement("insert into payment values(?,?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setInt(3,price);
                ps.setInt(4,quant);
                ps.setInt(5,total);
                ps.setDouble(6,point);
                
                ps.setString(7, cumail);
                ps.setString(8, vmail);
                
                ps.setDouble(9, ad_amt);
                ps.setDouble(10, ve_amt);
                
                ps.executeUpdate();
                
                PreparedStatement ps1=con.prepareStatement("insert into totalpayment values(?,?)");
                ps1.setString(1, cumail);
                ps1.setInt(2,total);
                ps1.executeUpdate();
                
                 out.println("<html><head><script>window.alert('PURCHASED');window.location.assign('totalpayment');</script></head></html>");
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
 