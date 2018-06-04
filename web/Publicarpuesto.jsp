<%-- 
    Document   : Publicarpuesto
    Created on : 03/06/2018, 02:10:31 AM
    Author     : anderson
--%>

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
        <title>Publicar Puesto</title>
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
    <form id="Form_add_Puestos" action="javascript:addPuestos();" method="POST">     
        <div class="Conte_AddPuestos">
            <div class="Conte_Form_AddPuestos">
                
                    <br>
                    <label> Nombre Puesto </label> <input type="text" value="" required="" id="NombreP"> <br><br>
                    <label> Salario </label> <input type="text" value="" required="" id="SalarioP"> <br><br>
                    <label> Descripcion Puesto </label> <input type="text" value="" required="" id="DescP"> <br><br>
                    
                    <table id="TipoPues"> 
                        <tr> 
                            <td> <label> Tipo Puesto </label> <td>
                            <td><label>Publico </label> </td> 
                            <td><input type="radio" name="elegir" id="empresa" value="Empresa" required> </td> 
                            <td><label>Privado </label> </td> 
                            <td><input type="radio" name="elegir" id="oferente" value="Oferente" required> </td> 
                        </tr>
                    </table>
                    <br>
                    <input id="BuscaHabiPadre"  type="submit" value="Agregar">
               
            </div>
        </div>
                
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
     </form>      
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

function addHabilidades () {
    var obj = $( ".HabilidadHoja" );
    var lista = new Array();
    for (i=0; i<obj.length; i++) { 
        ID = { idCaracteristica:obj[i].id};
        car = { valor:obj[i].value,
            caracteristicas:ID,
        };
        lista.push(car);
    }
    $.ajax ({ type: "POST", 
            url:"Habilidades_Add", 
            data: JSON.stringify(lista), 
            dataType:"json",
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
