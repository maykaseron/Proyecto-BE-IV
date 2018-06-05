<%-- 
    Document   : Publicarpuesto
    Created on : 03/06/2018, 02:10:31 AM
    Author     : anderson
--%>

<%@page import="entidades.CaracteristicasPuestos"%>
<%@page import="entidades.Puestos"%>
<%@page import="entidades.Caracteristicas"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" href = "css/Login.css"> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  
        <title>Agregar Caracteristicas a un Puesto</title>
    </head>
    <body>
        
        <nav>          
            <div class="container-fluid"> 
                <ul  class="nav navbar-nav">    
                <%@ include file="encabezado.jsp" %>
                <%  if (empresa2 == null) { %> 
                    <li><a href="LoginOferente">Iniciar-Sesión</a></li>
                <% } %>
                </ul>
            </div>
        </nav> 
                
        <!-- copiar el documento busca caracte_puestos.txt dq esta en: C:\Users\anderson\Documents\Cursos Actuales\Progra IV\Proyecto-BE-IV -->
        <ol id="lista3">
        <jsp:useBean id="CaracteristicasPadres" scope="request" type="List<Caracteristicas>" beanName="java.util.ArrayList"/>
            <% for(Caracteristicas L_CPad: CaracteristicasPadres){   %>
                    <li id="cuadro"> 
                        <p   
                           onclick="buscar(<%= L_CPad.getIdCaracteristica() %>,this )" > 
                            <%= L_CPad.getHabilidad() %>  </p>
                    </li>
            <% } %> 
        </ol>
        
        <jsp:useBean id="lista_Puestos" scope="session" type="List<Puestos>" class="java.util.List"/>                  
        <div class="div_tabla_H" >
            <table class="Tabla_hab">
                <caption> <i> PUESTOS </i> </caption>
                <thead> 
                    <tr> 
                        <td> Nombre </td>
                        <td> Puesto </td>
                    </tr>
                </thead>
                <tbody id="prueba"> <% /* la lista es traida se sesion  */%>
                    <% for (Puestos p: lista_Puestos) {  %>
                    <% if (  p.getCaracteristicasPuestos().size() == 0 ) { %> 
                        <tr onclick="yyy( <%= p.getIdPuesto() %> )">
                            <td> <%= p.getNombrePuesto() %> </td>
                            <td> <%= p.getSalario()  %> </td>
                            
                        </tr>
                    <% } else %>
                    <% for (CaracteristicasPuestos CP: p.getCaracteristicasPuestos()) { %>
                        <tr> 
                            <td> <%= p.getNombrePuesto() %> </td>
                            <td> <%= p.getSalario()  %> </td>
                            <td> <%= CP.getCaracteristicas().getHabilidad()  %> </td>
                            <td> <%= CP.getValor()  %> </td>
                        </tr> <% } %> <% } %>   
                </tbody>
            </table>
        </div>
     
<script>
function loaded(event){
}
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

function yyy ( id ) {
    C_O = {idCaracteristica:id
		};
                C_O;
    var obj = $( ".HabilidadHoja" );
    var lista = new Array();
    for (i=0; i<obj.length; i++) { 
        ID = { idCaracteristica:obj[i].id};
        car = { valor:obj[i].value,
            caracteristicas:ID
        };
        lista.push(car);
    }
    puesto = { nombrePuesto:$("#PersonaP").val(),
               salario:$("#SalarioP").val(),
               descripcionPuesto:$("#DescP").val()
        };
    data=new FormData();
    data.append("listaPuestos",JSON.stringify(lista));
    data.append("puesto",JSON.stringify(puesto));
    $.ajax ({ type: "POST", 
            url:"Puestos_Add", 
            data: data,
            processData: false,
            contentType: false,      
            "success": 
                    function(obj2){
                    prueba(obj2); 
                },
             "error": function(status){
                     window.alert("???");
                }                    
            });
    $( "#lista3" );
}
function prueba (obj) { // resultado = lista de puestos
    if ( obj.length < 1 )
        window.alert("Sin resultados");
    else
    for (i=0; i<obj.length; i++) {
        var ppp = obj[i];
        $( ".HabilidadHoja" );
    }
}

document.addEventListener("DOMContentLoaded",loaded);
</script>
    </body>
</html>
