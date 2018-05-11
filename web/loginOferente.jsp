<%-- 
    Document   : loginOferente
    Created on : 02/05/2018, 01:44:40 AM
    Author     : anderson
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.Caracteristicas"%>
<%@page import="entidades.Oferente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" href = "css/Login.css">
        <script type="text/javascript" src="js/ajax.js"></script>        
        <title>LoginOferente </title>
    </head>
    <body>
        <% Oferente oferente= (Oferente) session.getAttribute("Login_Oferente");%>
        <div class="Menu"> 
            <ul>
            <%  if (oferente != null) { %> 
                <li id="Titulo"> ImaJobs </li>
                <li><%= oferente.getNombreOferente() %> </li>
                <li> <a href=""> Habilidades </a> </li>
                <li> <a href="Logout"> Salir </a> </li>
            <% } %>
            <%  if (oferente == null) { %> 
                <li id="Titulo"> ImaJobs </li>
                <li> <a href="Top5"> Cerrar Sesión </a> </li>
            <% } %>
            </ul>
        </div>
            
        <div class="Div_Habilidades"> 
            <form method="POST" name="formulario" id="formulario" action="">
                <label>Habilidad </label> <input type="text" required name="correo" id="correo"> <br>  <br>   
                <label>Nivel </label> <input type="password" required name="contraseña" id="contraseña"> <br> <br>  
                <input type="submit" value="Editar" >
            </form>
        </div>   
            
        
        
<script>
function buscar ( ) {
    var listado = document.getElementById( carac.idCaracteristica );
    ajax ({ "method": "POST", 
            "url":"ListarCaracteristicasPadre", 
            "success": 
                function(obj){
                lista(obj,listado); 
            },
            "error": function(status){
                 window.alert("Error");
            }                    
        });
}
function buscar (idCaracteristica ) {
    carac = { idCaracteristica:idCaracteristica  };
    var listado = document.getElementById( carac.idCaracteristica );
    ajax ({ "method": "POST", 
            "url":"pru", 
            "data": carac,
            "success": 
                function(obj){
                lista(obj,listado); 
            },
            "error": function(status){
                 window.alert("Error");
            }                    
        });
        listado.removeAttribute("onclick");
}
function lista (obj,listado) {
    for (i=0; i<obj.length; i++) {
        var aux = obj[i];
        var ul =document.createElement("ul");
        ul.innerHTML = "<li > <p id='\""+aux.idCaracteristica+"\"' onclick='buscar(\""+aux.idCaracteristica+"\")'>"+ aux.habilidad + "</p> </li>";
        listado.appendChild(ul);
    }
}
document.addEventListener("DOMContentLoaded",loaded);
</script>
    </body>
</html>
