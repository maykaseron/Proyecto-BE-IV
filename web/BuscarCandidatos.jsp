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
        <form method="POST" action="javascript:buscarCandidatos();" accept-charset="utf-8">
            <input id="BuscaHabiPadre"  type="submit" value="Buscar">
        </form> 
        
        
        <div class="div_tabla_H" >
            <table class="Tabla_hab">
                <caption> <i> HABILIDADES </i> </caption>
                <thead> 
                    <tr> 
                        <td> Nombre  </td>
                        <td> Cédula  </td>
                        <td> Celular </td>
                        <td> Correo  </td>
                    </tr>
                </thead>
                <tbody id="prueba">
                </tbody>
            </table>
        </div>
<script>
function loaded(event){}
function buscar (idCaracteristica,elmt ) { // desplega la lista de CARAC
    carac = { idCaracteristica:idCaracteristica 
            };
    var listado = elmt;
    $.ajax ({ method: "POST", 
            url:"Busc_caracteristicas", 
            data: JSON.stringify(carac), 
            dataType:"json",
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
function lista (obj,listado) { // trae la lista del q se le dio click
    for (i=0; i<obj.length; i++) {
        var aux = obj[i];
        var ol =document.createElement("ul");
        if ( aux.habilitado === true ) {
        ol.innerHTML = "<li> <p style='color:#6600CC'  ondblclick='nivel(\""+aux.idCaracteristica+"\", this)' \n\
             onclick='buscar(\""+aux.idCaracteristica+"\", this )  '>"
                + aux.habilidad + "</p> </li>";
        } else {
            ol.innerHTML = "<li> <p   \n\
             onclick='buscar(\""+aux.idCaracteristica+"\", this)  '>"
                + aux.habilidad + "</p> </li>";
        }
        listado.appendChild(ol);
    }
}
function nivel(id,elmt) { // DOBLE CLICK para escribir el nivel a a buscar 
    carac = { idCaracteristica:id };
    var inp = document.createElement("span"); 
    inp.innerHTML = " <input  type='text' class='HabilidadHoja' id=\""+carac.idCaracteristica+"\"> ";
    var listado = elmt;
    listado.appendChild( inp );
    listado.removeAttribute("ondblclick");
}


function buscarCandidatos() {
    var obj = $( ".HabilidadHoja" );
    var lista = new Array();
    for (i=0; i<obj.length; i++) { 
        ID = { idCaracteristica:obj[i].id};
        car = { valor:obj[i].value,
            caracteristicas:ID
        };
        lista.push(car);
    }
    $.ajax ({ type: "POST", 
            url:"Buscar_Carac_Candidatos", 
            data: JSON.stringify(lista), 
            dataType:"json",
            "success": 
                    function(obj2){
                    candidatos(obj2); 
                },
             "error": function(status){
                     window.alert("???");
                }                    
            });
    $( "#lista3" );
}
function candidatos (obj) { // desserializa las oferentes 
    if ( obj.length < 1 )
        window.alert("Sin resultados");
    else
    for (i=0; i<obj.length; i++) {
        var ppp = obj[i];
        var tr =$("<tr />");
        tr.html("<td>"+ppp.nombreOferente+"</td>"+
                "<td>"+ppp.cedulaOferente+"</td>"+
                "<td>"+ppp.celular+"</td>" +
                "<td>"+ppp.correoOferente+"</td>" );
        $("#prueba").append(tr);
    }
}

document.addEventListener("DOMContentLoaded",loaded);
</script>                
    </body>
</html>
