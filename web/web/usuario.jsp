<%-- 
    Document   : usuario
    Created on : 25/09/2017, 08:05:27 PM
    Author     : Juan Benitez
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario") == null) {
        response.sendRedirect("../index.html");
    }

    Usuario alguien = (Usuario) request.getSession().getAttribute("usuario");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="../css/estilos.css" type="text/css">

        <title>Usuario</title>
    </head>

    <body>
        <header>
            <nav class="menu">
                <ul>
                    <li>
                        <a href="../juego">Nuevo Juego</a>
                    </li>
                    <li>
                        <a href="../usuario?accion=salir">Cerrar Sesion</a>
                    </li>
                </ul>
            </nav>
        </header>
        <div id="informacion">
            <label>ID: <%= alguien.getId() %></label>
            <label>Nombre: <%= alguien.getNombre() %></label>
            <label>User: <%= alguien.getUser() %></label>
            <label>Partidas ganadas: <%= alguien.getP_ganados()%></label>
            <label>Partidas perdidas: <%= alguien.getP_perdidos()%></label>
        </div> 
    </body>
</html>
