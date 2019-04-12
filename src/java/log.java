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
import javax.servlet.RequestDispatcher;
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
@WebServlet(urlPatterns = {"/log"})
public class log extends HttpServlet {

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
            String name = request.getParameter("uname");
            String pass = request.getParameter("psw");
            int count =0;
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/shoppingcart", "root", "");
                
                PreparedStatement stmt1=con.prepareStatement("select * from original_vendor ");
                ResultSet rst1=stmt1.executeQuery(); 
                
                while(rst1.next())
                {
                    count = 0;
                    String vname = rst1.getString(1);
                    String vpass = rst1.getString(3);
                    
                    
                        if(name.equals(vname) && pass.equals(vpass))
                        {
                            ServletContext application = getServletConfig().getServletContext();
                            String uservendorname = rst1.getString(4);
                            String uservendormail = rst1.getString(1);
                            application.setAttribute("vendorname",uservendorname);
                            application.setAttribute("vendormail",uservendormail);
                            RequestDispatcher rs1 = request.getRequestDispatcher("/vendor1.jsp");
                            
                            rs1.forward(request, response);
                            count++;
                            
                       
                        }
                            
                             
                } 
                
                if(count == 0)
                        {
                            
                            out.println("<html><head><script>window.alert('ERROR IN USERNAME AND PASSWORD');window.location.assign('login.html');</script></head></html>");
                     
                            RequestDispatcher rd1=request.getRequestDispatcher("/index.html");
                            rd1.include(request, response);
                        }
            }
            catch(Exception e1)
            {
                out.println(e1);
            }
            try
            {
                        count =0;
                        if(name.equals("admin@gmail.com") && pass.equals("amma123"))
                        {
                            RequestDispatcher rs4 = request.getRequestDispatcher("/admin.html");
                            rs4.forward(request, response);
                            count++;
                        }
                        
                        if(count == 0)
                        {
                            
                            out.println("<html><head><script>window.alert('ERROR IN USERNAME AND PASSWORD');window.location.assign('login.html');</script></head></html>");
                     
                            RequestDispatcher rd4=request.getRequestDispatcher("/login.html");
                            rd4.include(request, response);
                        } 
                        
                        
            }
            
            catch(Exception e4)
            {
                out.println(e4);
            }
           
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/shoppingcart", "root", "");
                
                PreparedStatement stmt3=con.prepareStatement("select * from customer");
                ResultSet rst3=stmt3.executeQuery(); 
                
                while(rst3.next())
                {
                     count =0;
                    String cname = rst3.getString(1);
                    String cpass = rst3.getString(3);

                        if(name.equals(cname) && pass.equals(cpass))
                        {
                            ServletContext application = getServletConfig().getServletContext();
                            String customername =rst3.getString(4);
                            String customermail = rst3.getString(1);
                            application.setAttribute("cusname",customername);
                            application.setAttribute("cusmail",customermail);
                            RequestDispatcher rs3 = request.getRequestDispatcher("/customer.jsp");
                            rs3.forward(request, response);
                            count++;
                        }
                        
                       
                }
                 if(count == 0)
                        {
                            
                            out.println("<html><head><script>window.alert('ERROR IN USERNAME AND PASSWORD');window.location.assign('login.html');</script></head></html>");
                     
                            RequestDispatcher rd3=request.getRequestDispatcher("/index.html");
                            rd3.include(request, response);
                        }
            }
            
            catch(Exception e3)
            {
                out.println(e3);
            }
            
            
            
             try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/shoppingcart", "root", "");
                
                PreparedStatement stmt2=con.prepareStatement("select * from employee");
                ResultSet rst2=stmt2.executeQuery(); 
                
                while(rst2.next())
                {
                     count =0;
                    String ename = rst2.getString(1);
                    String epass = rst2.getString(3);
                    
                    
                        if(name.equals(ename) && pass.equals(epass))
                        {
                            RequestDispatcher rs2 = request.getRequestDispatcher("/employee.html");
                            rs2.forward(request, response);
                            count++;
                        }
                        
                        
                        
                       }
                       
               
                
                
               if(count == 0)
                        {
                            
                            out.println("<html><head><script>window.alert('ERROR IN USERNAME AND PASSWORD');window.location.assign('login.html');</script></head></html>");
                     
                            RequestDispatcher rd2=request.getRequestDispatcher("/index.html");
                            rd2.include(request, response);
                        }
                
            }
            
            
            catch(Exception e2)
            {
                out.println(e2);
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet log</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet log at " + request.getContextPath() + "</h1>");
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
