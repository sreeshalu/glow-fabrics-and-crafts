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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHSCI5MCA16076
 */
@WebServlet(urlPatterns = {"/changepassemployee"})
public class changepassemployee extends HttpServlet {

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
             String mail = request.getParameter("mail");
            String curpass = request.getParameter("curpass");
            String newpass = request.getParameter("newpass");
            String conpass = request.getParameter("conpass");
            String pass="";
            String cpass="";
            String email="";
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet changepassemployee</title>");            
            out.println("</head>");
            out.println("<body>");
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","");
                //out.print("connected");
                
                PreparedStatement ps1 = con.prepareStatement("select * from employee where e_pass=? and e_email=?");
                ps1.setString(1, curpass);
                ps1.setString(2, mail);
                
                ResultSet rs = ps1.executeQuery();
                
                while(rs.next())
                {
                    pass = rs.getString(2);
                    email = rs.getString(1);
                    
                }
              
                if(pass.equals(curpass))
                {
                    if(newpass.equals(conpass))
                    {
                        PreparedStatement ps2 = con.prepareStatement("update employee set e_pass=? where e_email=?");
                        ps2.setString(1,newpass);
                       
                        ps2.setString(2,mail);
                        
                        ps2.executeUpdate();
                        
                        PreparedStatement ps3 = con.prepareStatement("update employee set e_cpass=? where e_pass=?");
                        ps3.setString(1,newpass);
                       
                        ps3.setString(2,newpass);
                        
                        ps3.executeUpdate();
                        
                        out.println("<html><head><script>alert('PASSWORD CHANGED SUCCESSFULY');window.location.assign('login.html');</script></head></html>");
                    }
                    else
                    {
                        out.println("<html><head><script>alert('NEW PASSWORD AND CONFIRM PASSWORD DOES NOT MATCH');window.location.assign('changepass.html');</script></head></html>");
                    }
                }
                else
                {
                    out.println("<html><head><script>alert('INVALID CURRENT PASSWORD');window.location.assign('changepass.html');</script></head></html>");
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
