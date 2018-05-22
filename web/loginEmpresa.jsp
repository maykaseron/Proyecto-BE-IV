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
        <script type="text/javascript" src="js/ajax.js"></script>   
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
                <form id="Form_datos">
                    <br>
                    <label> Empresa </label> <input type="text" > <br><br>
                    <label> Telefono </label> <input type="text" > <br><br>
                    <label> Correo </label> <input type="text" > <br><br>
                    <label> Descripcion </label> <input type="text" > <br><br>
                </form> 
            </div>
        </div>
                
<script>

</script>

    </body>
</html>
