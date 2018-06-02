<%-- 
    Document   : AddHabilidades
    Created on : 02/06/2018, 04:27:48 PM
    Author     : anderson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" href = "css/Login.css"> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>       
        <title>JSP Page</title>
    </head>
    <body>
        
        <nav>          
            <div class="container-fluid"> 
                <ul  class="nav navbar-nav">    
                <%@ include file="encabezado.jsp" %>
                <%  if (oferente2 == null) { %> 
                    <li><a href="LoginOferente">Iniciar-Sesión</a></li>
                <% } %>
                </ul>
            </div>
        </nav> 
                
    </body>
</html>
