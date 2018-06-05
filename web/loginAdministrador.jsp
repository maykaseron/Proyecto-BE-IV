<%-- 
    Document   : loginAdministrador
    Created on : 04/06/2018, 09:38:23 PM
    Author     : anderson
--%>

<%@page import="entidades.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" href = "css/Login.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  
        <title>Perfil</title>
    </head>
    <body>
        <% Administrador administrador= (Administrador) session.getAttribute("Login_Administrador"); %>
         <nav>          
            <div class="container-fluid"> 
                <ul  class="nav navbar-nav">
                <%@ include file="encabezado.jsp" %>
                 <%  if (administrador == null) { %> 
                    <li id="Titulo"> ImaJobs </li>
                    <li> <a href="Top5"> Principal  </a> </li>
                <% } %>
                </ul>
            </div>
        </nav>
    
                
                
    </body>
</html>
