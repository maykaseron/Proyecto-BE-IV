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
    <script type="text/javascript" src="js/ajax.js"></script>   
    <%-- <link rel = "stylesheet" href = "principal.css"> --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
  
<body>
    <div class="Div_Top"> <p> ImaJobs </p> </div>
    
    
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
                    <li><a href="ListarCaracteristicasPadre">Puestos Por Caracteristicas</a></li>
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
                
                <% for(Puestos p: Top5puestos) {   %>  <!-- empieza for Top5puestos-->
                
                <div class="item">  <br><br><br><br><br><br><br><br>
                    <%--<h2> centrar </h2> --%>
                   <%-- <table class="TableTop">--%>
                            <p>Nombre Empesa: <%= p.getEmpresa().getNombreEmp() %> </p> 
                            <p>Nombre Puesto: <%= p.getNombrePuesto() %></p>
                            <p>Salario: <%= p.getSalario() %></p>     
                            <!--  LCP lista CaracteristicasPuestos  -->  
                            <% for(CaracteristicasPuestos LCP: p.getCaracteristicasPuestos()) { %> <!-- empieza for LCP-->
                            <%--    <td> <%= LCP.getCaracteristicas().getAreaTrabajo() %>  </td>
                            <td> <%= LCP.getCaracteristicas().getEspecializacion() %></td>  --%>
                            <p>Habilidad: <%= LCP.getCaracteristicas().getHabilidad() %> 
                               Nivel de conocimiento: <%= LCP.getValor() %> </p> 
                            <% } %>  <!-- termina for LCP-->
                 <%--   </table> --%>
                </div>
                
                <% } %>  <!-- termina for Top5puestos-->
            </div>
              
              <!-- Carousel nav -->
              <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
              <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
        </div>
            
        <div id="login" class="login">
            <form method="POST" name="formulario" id="formulario" action="javascript:login();">
                <label>Correo </label> <input type="text" required name="correo" id="correo">
                <label>Contraseña </label> <input type="password" required name="contraseña" id="contraseña"> <br>
                <table> 
                    <tr> 
                        <td><label>Empresa </label> </td> 
                        <td><input type="radio" name="elegir" id="empresa" value="Empresa" required> </td> 
                        <td><label>Oferente </label> </td> 
                        <td><input type="radio" name="elegir" id="oferente" value="Oferente" required> </td> 
                    </tr>
                </table>
                <input type="submit" value="Iniciar sesión" >
            </form>
        </div>
    </div>
    
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function(){
            $('.myCarousel').carousel()
        });
        
        function login () {
            if ( document.getElementById("empresa").checked ) {                
                
                empresa = {
                    correoEmp:document.getElementById("correo").value,
                    contrasena:document.getElementById("contraseña").value,
                };
                 ajax ( { "method": "POST", 
                         "url":"LoginEmpresa", 
                         "data": empresa, 

                         "error": function(status) {
                             window.alert("Error");
                         }                    
                      }
                 );
                this.window.window.location.href = "loginEmpresa.jsp";
            }
            if ( document.getElementById("oferente").checked ) {
                document.getElementById("z").value = "oferente"; 
            }
            
        }
    </script>
</body>
</html>