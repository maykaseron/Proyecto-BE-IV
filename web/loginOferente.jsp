<%-- 
    Document   : loginOferente
    Created on : 02/05/2018, 01:44:40 AM
    Author     : anderson
--%>

<%@page import="entidades.CaracteristicasOferente"%>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>       
        <title>LoginOferente </title>
    </head>
    <body>
        
        <nav>          
            <div class="container-fluid"> 
                <ul  class="nav navbar-nav">    
                <%@ include file="encabezado.jsp" %>
                <%  if (oferente2 == null) { %> 
                    <li><a href="LoginOferente">Iniciar-Sesión</a></li>
                <% } %>
                </ul>
            </div>
        </nav> 
                
        <div class="Conte_Datos">
            <div class="Conte_Foto">
                <img id="Foto" src="imagenes/fotoPerfilver2.png">
            </div>
            <div class="Conte_Form">
                <form id="Form_datos"  method="POST" action="javascript:add();">
                    <br>
                    <label> Nombre </label> <input type="text" disabled="" value=<%= oferente2.getNombreOferente() %>  > 
                    <label> Apellido </label> <input type="text" disabled="" value=<%= oferente2.getPrimerApellido() %> > <br><br>
                    <label> Cédula </label> <input type="text" disabled="" value=<%= oferente2.getCedulaOferente() %> > 
                    <label> Celular </label> <input type="text" disabled="" value=<%= oferente2.getCelular() %> > <br><br>
                    <label class="Label_correo"> Correo </label> <input class="Label_correo" type="text" disabled="" value=<%= oferente2.getCorreoOferente() %> > 
                     <input type="file" id="PDF" accept="application/pdf">
                    <input class="boton" type="submit" value="Registrar">
                </form> 
                <%/* <img id="pdf" src="imagenes/iconPDFsubir.png"> */%>
            </div>
        </div>
                
    <div class="Div_Habilidades" >
        <div class="Div2_Habilidades" >
            <br>
            <label style="width: 100%; font-size: 120%"> Editar </label> <br><br>
            <label > Habilidad </label> <input disabled="" type="text"  id="habildad"  > <br><br>
            <label> Nivel </label>     <input type="text" name="habildad"  id="nivel" >
            <br><br><br><br>
            <form action="javascript:actualizaHabilidad()"> 
                <input disabled="" id="actualizar" type="submit" value="Actualizar" > </form>
        </div>
    </div>
    
    <jsp:useBean id="lista_habilidad" scope="session" type="List<CaracteristicasOferente>" class="java.util.List"/>                  
        <div class="div_tabla_H" >
            <table class="Tabla_hab">
                <caption> <i> HABILIDADES </i> </caption>
                <thead> 
                    <tr> 
                        <td> Habilidad </td>
                        <td> Nivel </td>
                        <td> Porcentaje </td>
                        <td> Eliminar </td>
                    </tr>
                </thead>

                <tbody id="prueba"> <% /* la lista es traida se sesion  */%>
                    <% for (CaracteristicasOferente C_O: lista_habilidad) { %>
                    <tr> 
                        <td onclick="editar( <%=C_O.getCaracteristicas().getIdCaracteristica() %> )"> <%= C_O.getCaracteristicas().getHabilidad() %> </td>
                        <td> <%= C_O.getValor()  %> </td>
                        <td> % </td>
                        <td onclick="popUp( <%=C_O.getIdCO() %> )"> <img id="delete" src="imagenes/delete.png"> </td>
    <div id="myModal" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
            <span class="close">&times;</span>
            <p id="POPup" onclick="eliminar( )"> Seguro </p>
        </div>
    </div>
                    
                    </tr> <% } %>  
                </tbody>
            </table>
        </div>
     
                
<script>
function editar ( id ) { // CaracteristicasOferente nivel
    C_O = {idCaracteristica:id
		};
        $.ajax({type: "POST", 
                  url:"Habilidad_edit", 
                  data: JSON.stringify(C_O), 
                  dataType:"json",
                  success: 
                    function(obj){
                        datosHabilidad(obj); 
                    },
                  error: function(status){
                         window.alert("Error");
                    }                    
        });   
}
function datosHabilidad ( C_O ) { //actualiza los datos del Div_Habilidades
    $("#actualizar").attr('class', C_O.caracteristicas.idCaracteristica );
    $("#actualizar").removeAttr('disabled');
    $("#habildad").val( C_O.caracteristicas.habilidad );
    $("#nivel").val( C_O.valor );
}
function actualizaHabilidad (){ // actualiza una habilidad, Gson recibe un prototipo q simula CaracteristicasOferente
    caracteristicas={ idCaracteristica:$("#actualizar").attr("class") }, // el prototipo de Caracteristicas su ID 
    ID = {  valor:$("#nivel").val(), // aqui el valor CaracteristicasOferente
            caracteristicas:caracteristicas // aqui el caracteristicas:caracteristicas de Caracteristicas
    };
        $.ajax({type: "POST", 
                      url:"Actualizar_Habilidad", 
                      data: JSON.stringify(ID), 
                      dataType:"json",
                      success: 
                        function( ){
                            restaurar( ); 
                        },
                      error: function(status){
                             window.alert("Error");
                        }                    
            }); 
}
function restaurar (  ){
    $("#prueba").load(" #prueba");
    $("#nivel").val( "" );
    $("#actualizar").attr('disabled','disabled');
    $("#habildad").val( "" );
}

 function add(){
        data=new FormData();
        data.append("foto",$("#PDF")[0].files[0]);
            $.ajax({type: "POST", 
                  url:"PDF_Add", 
                  data: data,
                  processData: false,
                  contentType: false,                  
                  success: 
                    function(){
                        Verif(); 
                    },
                  error: function(status){
                         window.alert("Nooooooooooooooooooooooo");
                    }                    
                });      
  }  
 function Verif(  ){
     
}
function eliminar ( ) { // el ID de Caracteristica Oferente
     ID = { idCO:$("#POPup").attr("name")  };
     $.ajax ({ type: "POST", 
            url:"Elminar_Habilidad", 
            data: JSON.stringify( ID ), 
            dataType:"json",
            success: 
                function(){
                restaurar( ); 
            },
            error: function(status){
                 window.alert("Error");
            }                    
        });
}
var modal = document.getElementById('myModal');
function popUp (id) { // el ID de Caracteristica Oferente
    ID = { idCO:id };
    modal.style.display = "block";
    $("#POPup").attr("name", ID.idCO)
}
var span = document.getElementsByClassName("close")[0];
span.onclick = function() {
        modal.style.display = "none";
    };
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

</script>
    </body>
</html>
