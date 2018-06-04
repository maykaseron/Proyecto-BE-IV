<%-- 
    Document   : loginEmpresa
    Created on : 01/05/2018, 08:02:06 PM
    Author     : anderson
--%>

<%@page import="entidades.Puestos"%>
<%@page import="java.util.List"%>
<%@page import="entidades.CaracteristicasPuestos"%>
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
        <nav>          
            <div class="container-fluid"> 
                <ul  class="nav navbar-nav">
                <%@ include file="encabezado.jsp" %>
                 <%  if (empresa == null) { %> 
                    <li id="Titulo"> ImaJobs </li>
                    <li> <a href="Top5"> Principal  </a> </li>
                <% } %>
                </ul>
            </div>
        </nav> 
        <div id="formu_Publ_Puestos">
            <form>
                
            </form>
        </div>
                
                
        <div class="Conte_Datos">
            <div class="Conte_Foto">
                <img src="imagenes/fotoPerfil.png">
            </div>
            <div class="Conte_Form">
                <form id="Form_datos_Emp">
                    <br>
                    <label> Empresa </label> <input type="text" disabled="" value=<%= empresa.getNombreEmp() %> > <br><br>
                    <label> Telefono </label> <input type="text" disabled="" value=<%= empresa.getTeléfono() %> > <br><br>
                    <label> Correo </label> <input type="text" disabled="" value=<%= empresa.getCorreoEmp() %> > <br><br>
                </form> 
            </div>
        </div>
        
        <jsp:useBean id="lista_Puestos" scope="session" type="List<Puestos>" class="java.util.List"/>                  
        <div class="div_tabla_H" >
            <table class="Tabla_hab">
                <caption> <i> PUESTOS </i> </caption>
                <thead> 
                    <tr> 
                        <td> Nombre </td>
                        <td> Puesto </td>
                        <td> Caracteristica </td>
                        <td> % </td>
                    </tr>
                </thead>

                <tbody id="prueba"> <% /* la lista es traida se sesion  */%>
                    <% for (Puestos p: lista_Puestos) { int flag=0; %>
                    <% for (CaracteristicasPuestos CP: p.getCaracteristicasPuestos()) { %>
                        <tr> 
                            <td> <%= p.getNombrePuesto() %> </td>
                            <td> <%= p.getSalario()  %> </td>
                            <td> <%= CP.getCaracteristicas().getHabilidad()  %> </td>
                            <td> <%= CP.getValor()  %> </td>
                        </tr> <% } %> <% } %>   
                </tbody>
            </table>
        </div>
<script>

</script>

    </body>
</html>
