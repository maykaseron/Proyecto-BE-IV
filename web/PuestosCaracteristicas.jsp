<%-- 
    Document   : PuestosCaracteristicas
    Created on : 31/03/2018, 08:54:33 PM
    Author     : anderson
--%>

<%@page import="entidades.Puestos"%>
<%@page import="entidades.CaracteristicasPuestos"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="entidades.Caracteristicas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" href = "css/PuestosCaracteristicas.css" type="text/css"/>
        <script type="text/javascript" src="js/ajax.js"></script>        
        <title>Puestos Caracteristicas</title>
    </head>
    
    <body>
        
        <div class="Div_Top"> <p> ImaJobs </p> </div>
        
        <h1> Puestos Ofrecidos por Caracteristicas  </h1>
        <%--                
        <form method="post" action="BuscarCarac" >
            <label> Caracteristica: </label> 
                <input type="search" name="areaTrabajo" /> 
                <input type="submit" value="Buscar" >
        </form >
        --%>
        <jsp:useBean id="listaPuestos" scope="request" type=" List<CaracteristicasPuestos> " beanName="java.util.ArrayList"/>
        <!-- copiar el documento busca caracte_puestos.txt dq esta en: C:\Users\anderson\Documents\Cursos Actuales\Progra IV\Proyecto-BE-IV -->
        
        <input type="text" name="z" id="z">
        <br><br><br> <br><br><br>   
        
        <jsp:useBean id="CaracteristicasHijos" scope="request" type="List<Caracteristicas>" beanName="java.util.ArrayList"/>
        <jsp:useBean id="CaracteristicasPadres" scope="request" type="List<Caracteristicas>" beanName="java.util.ArrayList"/>
        
        
        <% for(Caracteristicas L_CPad: CaracteristicasPadres){   %>
            <ul>  
                <li > 
                     <p id="<%= L_CPad.getIdCaracteristica()  %>"  onclick="buscar(<%= L_CPad.getIdCaracteristica()  %> )" > <%= L_CPad.getHabilidad() %>  </p>  
                    <%-- <%= L_CPad.getHabilidad() %>  --%>
                </li>
            </ul>
        <% } %> 
        
        <% for(Caracteristicas L_CHij: CaracteristicasHijos){   %>
        <ul> 
            <li onclick="buscar(this, <%= L_CHij.getIdCaracteristica()  %> )"> 
                <p > <%= L_CHij.getHabilidad() %>  </p>
            </li>
        </ul>
        <%}%> 
     <a href = "Top5" target = "_self">Regresar</a>   
    </body>
    
<script>
function loaded(event){
}
function buscar (idCaracteristica ) {
    carac = { idCaracteristica:idCaracteristica 
            };
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
/* listado.insertAdjacentHTML('beforeend',' <ul> <li> "sirvo?" </li> </ul>'); }*/
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

</html>
