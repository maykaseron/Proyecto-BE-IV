<%@page import="entidades.Empresa"%>
<%@page import="entidades.Oferente"%>
<%@page import="java.util.List"%>
<%@page import="entidades.Caracteristicas"%>
<%@page import="entidades.Oferente"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <% Empresa empresa= (Empresa) session.getAttribute("Login_Empresa"); %>
    <% Oferente oferente= (Oferente) session.getAttribute("Login_Oferente");%>
    
        <div class="Menu"> 
            <ul>
            <%  if (empresa != null) { %> 
                <li id="Titulo"> ImaJobs </li>
                <li><%= empresa.getNombreEmp() %> </li>   
                <li> <a href="Top5"> Principal </a> </li>
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
        
        
    </body>
</html>
