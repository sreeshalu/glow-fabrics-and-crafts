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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHSCI5MCA16076
 */
public class details extends HttpServlet {

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
            String mail=request.getParameter("email");
            String pass=request.getParameter("pass");
            String cpass=request.getParameter("cpass");
            String name=request.getParameter("uname");
            String address=request.getParameter("add");
            int pno=Integer.parseInt(request.getParameter("pno"));
            String city=request.getParameter("city");
            String state=request.getParameter("state");
            String utype=request.getParameter("s1");
            
            String eadhar = request.getParameter("eaid");
            String wrkexp = request.getParameter("wexp");
            String time = request.getParameter("tm");
            
            String protype = request.getParameter("ptype");
            String lno = request.getParameter("lnum");
            int vadhar=Integer.parseInt(request.getParameter("vaid"));
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet adddetails</title>");            
            out.println("</head>");
            out.println("<body>");
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/shoppingcart", "root", "");
                
                if("01".equals(utype))
                {
                     PreparedStatement ps1=con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?) ");
                     ps1.setString(1,mail);
                     ps1.setString(2,pass);
                     ps1.setString(3,cpass);
                     ps1.setString(4,name);
                     ps1.setString(5,address);
                     ps1.setInt(6,pno);
                     ps1.setString(7,city);
                     ps1.setString(8,state);
                    
                     ps1.executeUpdate();
                    
                    out.println("<html><body><script>window.alert('ONE ROW INSERTED');window.location.assign('login.html');</script></body></html>");
                }
                else
                    if("02".equals(utype))
                    {
                        PreparedStatement ps2=con.prepareStatement("insert into vendor values(?,?,?,?,?,?,?,?,?,?,?) ");
                        ps2.setString(1,mail);
                        ps2.setString(2,pass);
                        ps2.setString(3,cpass);
                        ps2.setString(4,name);
                        ps2.setString(5,address);
                        ps2.setInt(6,pno);
                        ps2.setString(7,city);
                        ps2.setString(8,state);
                        ps2.setString(9,protype);
                        ps2.setString(10,lno);
                        ps2.setInt(11,vadhar);
                        
                        ps2.executeUpdate();
                    
                        out.println("<html><body><script>window.alert('ONE ROW INSERTED');window.location.assign('login.html');</script></body></html>");
                    }
                
                else
                    if("03".equals(utype))
                    {
                        PreparedStatement ps3=con.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?,?,?) ");
                        ps3.setString(1,mail);
                        ps3.setString(2,pass);
                        ps3.setString(3,cpass);
                        ps3.setString(4,name);
                        ps3.setString(5,address);
                        ps3.setInt(6,pno);
                        ps3.setString(7,city);
                        ps3.setString(8,state);
                        ps3.setString(9,eadhar);
                        ps3.setString(10,wrkexp);
                        ps3.setString(11,time);
                        
                        ps3.executeUpdate();
                    
                        out.println("<html><body><script>window.alert('ONE ROW INSERTED');window.location.assign('login.html');</script></body></html>");
                    }
                
                else
                    {
                        //out.println("<html><body><script>window.alert('PLEASE SELECT USER TYPE!!');window.location.assign('signup.html');</script></body></html>");
                    }
                
                
            }
             catch(Exception ex)
            {
                out.println(ex);
                 //out.println("<html><body><script>window.alert('VALUE ALREADY EXISTS');</script></body></html>");
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
