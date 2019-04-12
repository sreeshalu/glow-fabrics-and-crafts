<%-- 
    Document   : customer
    Created on : Mar 27, 2019, 8:13:46 PM
    Author     : KHSCI5MCA16076
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
            .center {
                text-align: center;
                border: 0px;
                font-size: 35px;
                color:skyblue;
}
        </style>
    </head>
    <body > 
           <div>
               <div style=" float:left">
                   <img src="logo.png">
               </div>
        <div style="width:300px;float: right">
           <br><br><br>
           <a href="index.html" style="text-decoration: none;color: skyblue" target="_top" >HOME</a>&nbsp;&nbsp;
            <a href="login.html" style="text-decoration: none;color: skyblue" target="_top" >LOGOUT</a>
        &nbsp;&nbsp;<a href="changepasscustomer.html" style="text-decoration: none;color: skyblue" target="_top">CHANGE PASSWORD</a><br><br>
        </div>
        <div class="center">
         <br>
         WELCOME <label name="name">${cusname}</label>
        </div>
        </div>
        <br><br>
        <iframe src="customer2.html" height="480" width="1350"></iframe>
    </body>
</html>