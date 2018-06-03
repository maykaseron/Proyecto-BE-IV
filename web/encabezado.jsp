<%@page import="entidades.Empresa"%>
<%@page import="entidades.Oferente"%>


    <% Empresa empresa2= (Empresa) session.getAttribute("Login_Empresa"); %>
    <% Oferente oferente2= (Oferente) session.getAttribute("Login_Oferente");%>
    
    <!-- CUIDADO SO LO PUEDE ESTAR ABIERTA UNA SESION  -->
    <%  if (empresa2 != null ) { %> 
        <li id="Titulo"> ImaJobs </li>
        <li>  <%= empresa2.getNombreEmp() %> </li>    
        <li> <a href="Top5"> Principal </a> </li>
        <li> <a href="registroempresa.jsp"> Publicar puesto </a> </li>
        <li> <a href="registroempresa.jsp"> Buscar candidatos </a> </li>
        <li> <a href="Logout"> Cerrar Sesión </a> </li>
    <% } %>
    <%  if (oferente2 != null) { %> 
        <li id="Titulo"> ImaJobs </li>
        <li> <a href="loginOferente.jsp"> <%= oferente2.getNombreOferente() %> </a> </li>
        <li> <a href="Top5"> Principal </a> </li>
        <li> <a href="Lista_Habilidades_Add"> Habilidades </a> </li>
        <li> <a href="Logout"> Salir </a> </li>
    <% } %>
    