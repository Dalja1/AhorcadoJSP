<%-- 
    Document   : mensaje
    Created on : 25/09/2017, 08:25:17 PM
    Author     : Juan Benitez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje</title>
    </head>
    <body>
        <center>
            <h1>
                <%= request.getParameter("mensaje") %>
            </h1>
            </hr>
            <a href="../index.html"> <<< VOLVER::: </a>
        </center>
    </body>
</html>
