<%-- 
    Document   : loginOferente
    Created on : 02/05/2018, 01:44:40 AM
    Author     : anderson
--%>

<%@page import="entidades.Oferente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" href = "css/Login.css">
        <title>LoginOferente </title>
    </head>
    <body>
        <% Oferente oferente= (Oferente) session.getAttribute("Login_Oferente");%>
        <div class="Menu"> 
            <ul>
                <li id="Titulo"> ImaJobs </li>
                <li><%= oferente.getNombreOferente() %> </li>
                <li> <a href="registroempresa.jsp"> Habilidades </a> </li>
                <li> <a href="registroempresa.jsp"> Salir </a> </li>
            </ul>
        </div>
                
    </body>
</html>
