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
    
        <div class="div_tabla_H" >
            <table class="Tabla_hab">
                <caption> <i> PUESTOS </i> </caption>
                <thead> 
                    <tr> 
                        <td> Nombre </td>
                        <td> Puesto </td>
                        <td> Estado </td>
                        <td> Caracteristica </td>
                        <td> % </td>
                    </tr>
                </thead>
                <tbody id="prueba"> <% /* la lista es traida se sesion  */%>
                    <% for (Puestos p: lista_Puestos) {  %>
                    <% if (  p.getCaracteristicasPuestos().size() == 0 ) { %> 
                        <tr>
                            <td> <%= p.getNombrePuesto() %> </td>
                            <td> <%= p.getSalario()  %> </td>
                            <td onclick="desactivar( <%= p.getActivo() %>,<%= p.getIdPuesto() %> )"> <%= p.getActivo()  %> </td>
                            <td> Por agregar </td>
                            <td> Por agregar </td>
                        </tr>
                    <% } else %>
                    <% for (CaracteristicasPuestos CP: p.getCaracteristicasPuestos()) { %>
                        <tr> 
                            <td> <%= p.getNombrePuesto() %> </td>
                            <td> <%= p.getSalario()  %> </td>
                            <td onclick="desactivar( <%= p.getActivo() %>,<%= p.getIdPuesto() %> )"> <%= p.getActivo()  %> </td>
                            <td> <%= CP.getCaracteristicas().getHabilidad()  %> </td>
                            <td> <%= CP.getValor()  %> </td>
                        </tr> <% } %> <% } %>   
                </tbody>
            </table>
        </div>        
                
    </body>
</html>
