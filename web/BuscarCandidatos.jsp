<%-- 
    Document   : BuscarCandidatos
    Created on : 03/06/2018, 07:48:23 PM
    Author     : anderson
--%>

<%@page import="entidades.Caracteristicas"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>       
         <link rel = "stylesheet" href = "css/Login.css">
        <title>Buscar Candidatos</title>
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
                
        <ol id="lista3">
        <jsp:useBean id="CaracteristicasPadres" scope="request" type="List<Caracteristicas>" beanName="java.util.ArrayList"/>
            <% for(Caracteristicas L_CPad: CaracteristicasPadres){   %>
                    <li id="cuadro"> 
                        <p   
                           onclick="buscar(<%= L_CPad.getIdCaracteristica()  %>,this )" > 
                            <%= L_CPad.getHabilidad() %>  </p>
                    </li>
            <% } %> 
        </ol>
        <form method="POST" action="javascript:addHabilidades();" accept-charset="utf-8">
            <input id="BuscaHabiPadre"  type="submit" value="Agregar">
        </form> 
                
    </body>
</html>
