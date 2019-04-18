<%-- 
    Document   : vendor1
    Created on : Mar 27, 2019, 1:55:57 PM
    Author     : KHSCI5MCA16076
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
           
            <a href="login.html" style="text-decoration: none;color: skyblue" target="_top" >LOGOUT</a>
        &nbsp;&nbsp;<a href="changepassvendor.html" style="text-decoration: none;color: skyblue" target="_top">CHANGE PASSWORD</a><br><br>
        </div>
        <div class="center">
         <br>
      WELCOME ${vendorname}
           
        </div>
        </div>
        <br><br><br><br>
      <iframe src="vendor2.html" height="600" width="1500" style="border:none;" scrolling="yes"></iframe>
    </body>
</html>
