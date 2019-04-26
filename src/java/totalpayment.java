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
public class totalpayment extends HttpServlet {

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
             double sum=0;
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
                application.setAttribute("sum",sum);
               out.println(sum);
                
              out.println(" <html>"+
    "<head>"+
       
        <title>JSP Page</title>
        out.println("<script>"+
            "function ShowHideDiv() {"+
        "var chkYes = document.getElementById('chkYes');"+
        "var dvPassport = document.getElementById('dvPassport');"+
        "dvPassport.style.display = chkYes.checked ? 'block' : 'none';}"+
    
            "</script>"+
    "</head>"+
    "<body style=' background-image: url(pict8.jpg)'><center>"+
      
        "<h1>PAYMENT MODE</h1>"+
        
       " <form action ='deletetotal'>"+
            // "<%= request.getAttribute("sum") %>."+
          " <label for='chkNo'>"+
               "<input type="radio" id="chkNo" name="chkPassPort" onclick="ShowHideDiv()" />CASH ON DELIVERY</label>"+
           "<label for="chkYes">"+
               "<input type="radio" id="chkYes" name="chkPassPort" onclick="ShowHideDiv()" />CREDIT CARD</label>"+
            
            "<div id="dvPassport" style="display: none">"+
            
              "  <label id="c1" >CARD NUMBER</label>"+
            "<input type="text"  name="cno" id="cno">"+
           " <br><br>"+
          " <label id="c2">CVC NUMBER</label>"+
            "<input type="text"  name="cno" id="cvc" >"+
           " </div>"+
           " <input type='submit' value='payment'>"+
       " </form></center> </body></html>");
    
   

               
               /* request.setAttribute("sum",sum);
request.getRequestDispatcher("pay.jsp").forward(request, response); 

               /* out.println("MODE OF PAYMENT");
                out.println("<script>"
                        +
                        
                         "</script>");
                out.println("<form>"
                        + "<input type='radio' name='pay' value='CASH ON DELIVERY'>CASH ON DELIVERY </input>"
                        +"<input type='radio' name='pay' value='CREDIT CARD'>CREDIT CARD</input>"
                        +"</form>");
                
                out.println("<form action ='deletetotal'><input type='submit' value='payment'></form>");*/
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
