<%-- 
    Document   : principal
    Created on : Mar 17, 2018, 1:26:37 PM
    Author     : pc
--%>
<%@page import="entidades.Empresa"%>
<%@page import="entidades.Oferente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Puestos"%>
<%@page import="java.util.List"%>
<%@page import="entidades.CaracteristicasPuestos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
         <!--    https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_ev_onclick2 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <title>Bolsa Empleo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap  HOJAS DE ESTILO-->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/principal.css" rel="stylesheet" >
    <!-- SCRIPT -->
    <script type="text/javascript" src="js/ajax.js"></script> 
    <script src="http://code.jquery.com/jquery.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
  
<body id="fff">
    
    <% Empresa empresa= (Empresa) session.getAttribute("Login_Empresa"); %>
    <% Oferente oferente= (Oferente) session.getAttribute("Login_Oferente");%>
    
    
    <nav class="navbar navbar-inverse">
        <div class="navbar-header">
              <a class="navbar-brand" href="#">ImaJobs</a>
        </div>
        <div class="container-fluid">
            <ul class="nav navbar-nav">
            <%  if (empresa == null && oferente == null) { %> 
                <li class="active"><a href="#">Login</a></li>
                <li><a href="registroempresa.jsp">Registroempresa</a></li>
                <li><a href="registrooferente.jsp">Registrooferente</a></li>
                <li><a href="ListarCaracteristicasPadre">Puestos Por Caracteristicas</a></li>
              
            <% } %>
            <%  if (empresa != null ) { %> 
                <li class="active"> <a href="#"><%= empresa.getNombreEmp() %> </a>  </li>    
                <li> <a href="registroempresa.jsp"> Publicar puesto </a> </li>
                <li> <a href="registroempresa.jsp"> Buscar candidatos </a> </li>
                <li> <a href="Logout"> Cerrar Sesión </a> </li>
            <% } else { %>
            <%  if (oferente != null) { %> 
                <li class="active"> <a href="loginOferente.jsp"> <%= oferente.getNombreOferente() %> </a> </li>
                <li> <a href=""> Habilidades </a> </li>
                <li> <a href="Logout"> Salir </a> </li>
            <% } }%>
           </ul>        
        </div>
    </nav>
    
    <div >
        <div>
            <form>
                <label id="red" style="color:red" onclick=" detalles() "> SIRVO </label>
            </form>
        </div>
    </div>
    
    <div style="width: 70%;         height: 50%; display: inline-block;">
                 <jsp:useBean id="Top5puestos" scope="request" type="List<Puestos>" class="java.util.ArrayList"/>
        <div class="carousel slide" id="img-carousel" data-ride="carousel">
          <!-- Indicators-->
          <ol class="carousel-indicators">
            <li class="active" data-target="#img-carousel" data-slide-to="0"></li>
            <li data-target="#img-carousel" data-slide-to="1"></li>
            <li data-target="#img-carousel" data-slide-to="2"></li>
          </ol>
          <!-- Wrapper for slides-->
          <div class="carousel-inner" role="listbox" >

              <div class="item active"><img src="https://beautimour.com/wp-content/uploads/2018/04/pexels-photo-380769.jpeg" alt=""/>
                  <div class="carousel-caption">
                    <h2 class="animated fadeInDown"> Top 5 </h2>
                    <p class="animated fadeInUp"> Los ultimos Puestos publicos </p>
                  </div>
                </div>

              <% for(Puestos p: Top5puestos) {   %>  <!-- empieza for Top5puestos-->
              <div class="item"><img src="https://beautimour.com/wp-content/uploads/2018/04/pexels-photo-459654.jpeg" alt=""/>
                  <div class="carousel-caption">
                    <h2 class="animated bounceInDown">  Empesa: <%= p.getEmpresa().getNombreEmp() %>  </h2>
                    <p class="animated bounceInUp"> Nombre Puesto: <%= p.getNombrePuesto() %> </p>
                    <p class="animated bounceInUp"> Salario: <%= p.getSalario() %> </p>
                    <% for(CaracteristicasPuestos CP: p.getCaracteristicasPuestos()) {   %>
                            <p class="animated bounceInUp"> Valor: <%= CP.getValor() %> </p>
                             <p class="animated bounceInUp"> Habilidad: <%= CP.getCaracteristicas().getHabilidad() %> </p>
                    <% }   %>
                    <p class="animated bounceInUp" onclick=" detalles2() ">  leer más </p>
                  </div>
                </div>
              <% } %>   
              </div>
              <!-- Controls--><a class="left carousel-control" href="#img-carousel" role="button" data-slide="prev"><i class="fa fa-chevron-left" aria-hidden="true"></i><span class="sr-only">Previous</span></a><a class="right carousel-control" href="#img-carousel" role="button" data-slide="next"><i class="fa fa-chevron-right" aria-hidden="true"></i><span class="sr-only">Next</span></a>
            </div>
    </div>
      
        <div id="login" class="login">
            <form method="POST" name="formulario" id="formulario" action="javascript:login();">
                <label>Correo </label> <br> <input type="text" required name="correo" id="correo" value=""> <br>
                <label>Contraseña </label>  <br> <input type="password" required name="contraseña" id="contraseña"> <br>
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

function detalles() {
    document.getElementById("red").style.color = "blue";
}
function detalles2() {
    document.getElementById("red").style.color = "red";
}
function login () {
    if ( document.getElementById("empresa").checked ) {                
        empresa = { correoEmp:document.getElementById("correo").value,
                    contrasena:document.getElementById("contraseña").value  };
         ajax ( { "method": "POST", 
                 "url":"LoginEmpresa", 
                 "data": empresa, 
                 "success": 
                    function(obj){
                    document.getElementById("formulario").reset();
                    redireccionar();
                    },
                 "error": function(status) {
                        redireccionarError ();
                 }                    
              }
         );
    }
    if ( document.getElementById("oferente").checked ) {
        oferente = { correoOferente:document.getElementById("correo").value,
            contrasena:document.getElementById("contraseña").value  };
        ajax ( { "method": "POST", 
                 "url":"LoginOferente", 
                 "data": oferente, 
                 "success": 
                    function(obj){
                    document.getElementById("formulario").reset();
                    redireccionar2();
                    },
                 "error": function(status) {
                        redireccionarError ();
                 }                    
              }
         );
    }
}
function redireccionar () { this.window.location.replace ( "loginEmpresa.jsp" ); }
function redireccionar2 () { this.window.location.replace ( "loginOferente.jsp" ); }
function redireccionarError () { this.window.location.replace ( "Top5" ); }

document.addEventListener("DOMContentLoaded",loaded);
    </script>
</body>
</html>