<%-- 
    Document   : payment
    Created on : Apr 26, 2019, 3:27:45 PM
    Author     : KHSCI5MCA16076
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function ShowHideDiv() {
        var chkYes = document.getElementById("chkYes");
        var dvPassport = document.getElementById("dvPassport");
        dvPassport.style.display = chkYes.checked ? "block" : "none";
    }
            </script>
    </head>
    <body style=' background-image: url(pict8.jpg)'><center>
      
        <h1>PAYMENT MODE</h1>
        
        <form action ="deletetotal">
             <%= request.getAttribute("sum") %>.
           <label for="chkNo">
               <input type="radio" id="chkNo" name="chkPassPort" onclick="ShowHideDiv()" />CASH ON DELIVERY</label>
           <label for="chkYes">
               <input type="radio" id="chkYes" name="chkPassPort" onclick="ShowHideDiv()" />CREDIT CARD</label>
            
            <div id="dvPassport" style="display: none">
            
                <label id="c1" >CARD NUMBER</label>
            <input type="text"  name="cno" id="cno">
            <br><br>
           <label id="c2">CVC NUMBER</label>
            <input type="text"  name="cno" id="cvc" >
            </div>
           <label>POINTS???</label>
           <label for="yes"> <input type="radio" id="yes" name="point" onclick="pointuse()"/>yes</label>
               <label for="no"> <input type="radio" id="no" name="point" />no</label>
           
               <script>
                   function pointuse()
                   {
                       
                   }
                   </script>
            <input type='submit' value='payment'>
        </form>
    </center>
    </body>
</html>
