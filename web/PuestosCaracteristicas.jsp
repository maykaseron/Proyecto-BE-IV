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
        <meta charset="UTF-8">
        <link rel = "stylesheet" href = "css/PuestosCaracteristicas.css" type="text/css"/>
        <script type="text/javascript" src="js/ajax.js"></script>       
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        
        <title>Puestos Caracteristicas</title>
    </head>
    
    <body>
        
        <div class="Menu"> 
            <ul>
                <li id="Titulo"> ImaJobs </li>
                <li> <a href = "Top5" target = "_self"> Principal </a> </li>
            </ul>
        </div>
        
        <h1> Busqueda de Puestos por Caracteristicas  </h1>
        <%--                
        <form method="post" action="BuscarCarac" >
            <label> Caracteristica: </label> 
                <input type="search" name="areaTrabajo" /> 
                <input type="submit" value="Buscar" >
        </form >
        <input type="text" name="z" id="z">
        --%>
        
        <jsp:useBean id="listaPuestos" scope="request" type=" List<CaracteristicasPuestos> " beanName="java.util.ArrayList"/>
        <!-- copiar el documento busca caracte_puestos.txt dq esta en: C:\Users\anderson\Documents\Cursos Actuales\Progra IV\Proyecto-BE-IV -->
        <ol id="lista3">
        <jsp:useBean id="CaracteristicasPadres" scope="request" type="List<Caracteristicas>" beanName="java.util.ArrayList"/>
            <% for(Caracteristicas L_CPad: CaracteristicasPadres){   %>
                    <li id="cuadro"> 
                        <p   
                           onclick="buscar(<%= L_CPad.getIdCaracteristica()  %>,this )" > 
                            <%= L_CPad.getHabilidad() %>  </p>
                        <%-- <%= L_CPad.getHabilidad() %>  --%>
                    </li>
            <% } %> 
        </ol>
        
        
        
        <form method="POST" action="javascript:buscarPuestos();" accept-charset="utf-8">
            <input  type="submit" value="Registrar">
        </form>
    </body>
    
<script>
function loaded(event){
}
function buscar (idCaracteristica,elmt ) { 
    carac = { idCaracteristica:idCaracteristica 
            };
    var listado = elmt;
    ajax ({ "method": "POST", 
            "url":"Busc_caracteristicas", 
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
function nivel(id,elmt) {
    carac = { idCaracteristica:id };
    var inp = document.createElement("span"); 
    inp.innerHTML = " <input  type='text' class='buscaBusca' id=\""+carac.idCaracteristica+"\"> ";
    var listado = elmt;
    listado.appendChild( inp );
    listado.removeAttribute("ondblclick");
}

    /*
function buscarPuestos(  ) { 
    var obj = $( ".buscaBusca" );
    var miArray=new Array();
    for (i=0; i<obj.length; i++) { 
        var aux = { idCaracteristica:obj[i].id, valor:obj[i].value };
        miArray.push(aux);
    }
    $.ajax ({ type: "POST", 
            url:"Busc_puestos_X_caracteristicas", 
            data: JSON.stringify(aux),
            dataType:"json",
            success: 
                function(obj2){
                prueba(obj2); 
            },
            error: function(status){
                 window.alert("Error");
            }                    
        });
    document.getElementById("Titulo").style.color= "black";
}
*/
function buscarPuestos(  ) {
    var obj = $( ".buscaBusca" );
    var lista = new Array();
    for (i=0; i<obj.length; i++) { 
        ID = { idCaracteristica:obj[i].id};
        car = { valor:obj[i].value,
            caracteristicas:ID
        };
        lista.push(car);
    }
        $.ajax ({ type: "POST", 
                url:"Busc_puestos_X_caracteristicas", 
                data: JSON.stringify(lista), 
                dataType:"json",
                success: 
                    function(obj2){
                    prueba(obj2); 
                },
                error: function(status){
                     window.alert("Error");
                }                    
            });
    document.getElementById("Titulo").style.color= "black";
}
function prueba (obj) {
    for (i=0; i<obj.length; i++) {
        var ppp = obj[i];
    }
    ppp;
    document.getElementById("Titulo").style.color= "red";
}


document.addEventListener("DOMContentLoaded",loaded);
/* var obj = $( ".buscaBusca" );
    var car = { idCaracteristica:obj[0].id };
    $.ajax ({ type: "POST", 
            url:"Busc_puestos_X_caracteristicas", 
            data: JSON.stringify(car),
            dataType:"json",
            success: 
                function(obj2){
                prueba(obj2); 
            },
            error: function(status){
                 window.alert("Error");
            }                    
        }); 
    */
/*
function buscar (idCaracteristica ) {
    carac = { idCaracteristica:idCaracteristica 
            };
    var listado = document.getElementById( carac.idCaracteristica );
    ajax ({ "method": "POST", 
            "url":"Busc_caracteristicas", 
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
     */
</script>

</html>
