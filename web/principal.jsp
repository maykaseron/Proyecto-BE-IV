<%-- 
    Document   : principal
    Created on : Mar 17, 2018, 1:26:37 PM
    Author     : pc
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.Puestos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <title>Bolsa Empleo</title>
    <link rel = "stylesheet" href = "principal.css">
</head>
  
<body>
    <div >
       <h1> Bolsa Empleo </h1>
    </div>
      
    <div class="reg">
        <ul>
            <li>
                <a href="#">Registro</a>
                <ul>
                    <li><a href="registroempresa.jsp">Registro empresa</a></li>
                    <li><a href="registrooferente.jsp">Registro oferente</a></li>
                </ul>
            </li>
        </ul>
          
        <form class = "formbusvac" name="busqueda vacantes" action="22-html5-search-input.php" method="POST">
            <input type="search" placeholder = "Buscar vacantes" name="busquedavacante">
            <input type="submit" value="Buscar">
        </form>
          
    </div>
    
    <div id="body" style="margin: 0 auto; width:85%;">   
        <div id="listar" class="area" style="width:50%;">   
            <br>
            <jsp:useBean id="sugerencias" scope="request" type="List<Puestos>" class="java.util.ArrayList"/>
            <table class="grid">
              <caption>TOP 5</caption>
              <thead><tr><td>Nombre</td><td>Salario</td><td>Descrip</td><td>IdPuesto</td></tr></thead>
              <tbody style="height: 250px;">
                <% for(Puestos p: sugerencias){ %>
                     <tr><td><%= p.getEmpresa().getNombreEmp() %></td><td><%= p.getSalario() %></td>
                     <td><%= p.getDescripcionPuesto() %></td><td><%= p.getIdPuesto() %></td></tr>
               <% } %>
            </tbody>
            </table>
        </div>
    </div>    
          
              
              <!--a href = "registroempresa.html" target = "_self">Registro empresa</a><br><br>
              <a href = "registrooferente.html" target = "_self">Registro oferente</a><br><br></div><br>
		   
		   <!--a href="registrooferente.html" target="frame" >Registro Oferente</a>
		   <a href="registroempresa.html" target="frame" >Registro Empresa</a>
		   <iframe name="frame"> </iframe-->
</body>
</html>