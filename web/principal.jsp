<%-- 
    Document   : principal
    Created on : Mar 17, 2018, 1:26:37 PM
    Author     : pc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Puestos"%>
<%@page import="java.util.List"%>
<%@page import="entidades.CaracteristicasPuestos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <title>Bolsa Empleo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/principal.css" rel="stylesheet" >
    <%-- <link rel = "stylesheet" href = "principal.css"> --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
  
<body>
    <div>
       <h1> Bolsa Empleo </h1>
    </div>
    
    <div class="reg">
        <ul>
            <li>
                <a href="#">Registro</a>
                <ul>
                    <li><a href="registroempresa.jsp">Registroempresa</a></li>
                    <li><a href="registrooferente.jsp">Registrooferente</a></li>
                </ul>
            </li>
            <li>
                <a href="#">Busqueda</a>
                <ul>
                    <li><a href="ListarCaracteristicas">Puestos Por Caracteristicas</a></li>
                </ul>
            </li>
        </ul>
    </div>
    
    
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    
    <div id="contenedor">
        <div id="myCarousel" class="carousel slide">
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
                <li data-target="#myCarousel" data-slide-to="3"></li>
                <li data-target="#myCarousel" data-slide-to="4"></li>
            </ol>
              <!-- Carousel items -->
           
            <div class="carousel-inner">
                
                <jsp:useBean id="Top5puestos" scope="request" type="List<Puestos>" class="java.util.ArrayList"/>
                <div class="active item"> <br><br><br><br><br><br><br><br>
                    <h2> Top 5 </h2> </div>
                
                <%  %>
                <% for(Puestos p: Top5puestos) {   %>  <!-- empieza for Top5puestos-->
                
                <div class="item">  <br><br><br><br><br><br><br><br>
                    <%--<h2> centrar </h2> --%>
                    <table class="TableTop">
                        <thead>
                            <tr>
                                <th>Nombre Empesa</th>
                                <th>Nombre Puesto</th>
                                <th>Salario</th>
                                <th>Area Trabajo</th>
                                <th>Especializacion</th>
                                <th>Valor</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td> <%= p.getEmpresa().getNombreEmp() %> </td>
                            <td> <%= p.getNombrePuesto() %> </td>
                            <td> <%= p.getSalario() %> </td>
                            <!--  LCP lista CaracteristicasPuestos  -->  
                            <% for(CaracteristicasPuestos LCP: p.getCaracteristicasPuestos()) { %> <!-- empieza for LCP-->
                            <td> <%= LCP.getCaracteristicas().getAreaTrabajo() %>  </td>
                            <td> <%= LCP.getCaracteristicas().getEspecializacion() %></td>
                            <td> <%= LCP.getValor() %> </td> 
                            <% } %>  <!-- termina for LCP-->
                        </tr>
                        </tbody>
                    </table> 
                </div>
                
                <% } %>  <!-- termina for Top5puestos-->
            </div>
              
              <!-- Carousel nav -->
              <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
              <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
        </div>
    </div>
 
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function(){
            $('.myCarousel').carousel()
        });
    </script>
</body>
</html>