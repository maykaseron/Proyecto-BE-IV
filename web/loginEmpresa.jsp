<%-- 
    Document   : loginEmpresa
    Created on : 01/05/2018, 08:02:06 PM
    Author     : anderson
--%>

<%@page import="entidades.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" href = "css/Login.css">
        <title>LoginEmpresa</title>
    </head>
    
    
    <body>
        <% Empresa empresa= (Empresa) session.getAttribute("Login_Empresa");%>
        <div class="Menu"> 
            <ul>
                <li id="Titulo"> ImaJobs </li>
                <li><%= empresa.getNombreEmp() %> </li>
                <li> <a href="registroempresa.jsp"> Publicar puestos </a> </li>
                <li> <a href="registroempresa.jsp"> Buscar candidatos </a> </li>
                <li> <a href="registroempresa.jsp"> Salir </a> </li>
            </ul>
        </div>
        
    </body>
</html>
