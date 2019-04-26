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
    </head>
    <body style=' background-image: url(pict8.jpg)'>
        ${sum}
        <form>
            <input type="radio" name="pay" value="cash">CASH ON DELIVERY
            <input type="radio" name="pay" value="card">CREDIT CARD
        </form>
    </body>
</html>
