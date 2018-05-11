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
        <% Empresa empresa= (Empresa) session.getAttribute("Login_Empresa"); %>
              
        <div class="Menu"> 
            <ul>
            <%  if (empresa != null) { %> 
                <li id="Titulo"> ImaJobs </li>
                <li><%= empresa.getNombreEmp() %> </li>          
                <li> <a href="registroempresa.jsp"> Publicar puesto </a> </li>
                <li> <a href="registroempresa.jsp"> Buscar candidatos </a> </li>
                <li> <a href="Logout"> Cerrar Sesión </a> </li>
            <% } %>
             <%  if (empresa == null) { %> 
                <li id="Titulo"> ImaJobs </li>
                <li> <a href="Top5"> Cerrar Sesión </a> </li>
            <% } %>
            </ul>
        </div>
        
        <div id="formu_Publ_Puestos">
            <form>
                
            </form>
        </div>
        
    <script>

    </script>

    </body>
</html>
