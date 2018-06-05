<%-- 
    Document   : loginEmpresa
    Created on : 01/05/2018, 08:02:06 PM
    Author     : anderson
--%>

<%@page import="entidades.Puestos"%>
<%@page import="java.util.List"%>
<%@page import="entidades.CaracteristicasPuestos"%>
<%@page import="entidades.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" href = "css/Login.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  
        <title>Perfil </title>
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
        <div id="formu_Publ_Puestos">
            <form>
                
            </form>
        </div>
                
                
        <div class="Conte_Datos">
            <div class="Conte_Foto">
                <img src="imagenes/fotoPerfil.png">
            </div>
            <div class="Conte_Form">
                <form id="Form_datos_Emp">
                    <br>
                    <label> Empresa </label> <input type="text" disabled="" value=<%= empresa.getNombreEmp() %> > <br><br>
                    <label> Telefono </label> <input type="text" disabled="" value=<%= empresa.getTeléfono() %> > <br><br>
                    <label> Correo </label> <input type="text" disabled="" value=<%= empresa.getCorreoEmp() %> > <br><br>
                </form> 
            </div>
        </div>
        
       
        <div class="Conte_AddPuestos">
            <div class="Conte_Form_AddPuestos">

                    <label> Nombre Puesto </label> <input type="text" value="" required="" id="NombreP"> <br><br>
                    <label> Salario </label> <input type="text" value="" required="" id="SalarioP"> <br><br>
                    <label> Descripcion Puesto </label> <input type="text" value="" required="" id="DescP"> <br><br>

                    <table id="TipoPues"> 
                        <tr> 
                            <td> <label> Tipo Puesto </label> <td>
                            <td><label>Publico </label> </td> 
                            <td><input type="radio" name="TpPublicacion" id="empresa" value="true" required> </td> 
                            <td><label>Privado </label> </td> 
                            <td><input type="radio" name="TpPublicacion" id="oferente" value="false" required> </td> 
                        </tr>
                    </table>
                    <br>
                     <form id="Form_add_Puestos" action="javascript:addPuestos();" method="POST" >     
                    <input id="BuscaHabiPadre"  type="submit" value="Agregar">
                     </form>      

            </div>
        </div>
        
                
        <jsp:useBean id="lista_Puestos" scope="session" type="List<Puestos>" class="java.util.List"/>                  
        <div class="div_tabla_H" >
            <table class="Tabla_hab">
                <caption> <i> PUESTOS </i> </caption>
                <thead> 
                    <tr> 
                        <td> Nombre </td>
                        <td> Puesto </td>
                        <td> Estado </td>
                        <td> Caracteristica </td>
                        <td> % </td>
                    </tr>
                </thead>
                <tbody id="prueba"> <% /* la lista es traida se sesion  */%>
                    <% for (Puestos p: lista_Puestos) {  %>
                    <% if (  p.getCaracteristicasPuestos().size() == 0 ) { %> 
                        <tr>
                            <td> <%= p.getNombrePuesto() %> </td>
                            <td> <%= p.getSalario()  %> </td>
                            <td onclick="desactivar( <%= p.getActivo() %>,<%= p.getIdPuesto() %> )"> <%= p.getActivo()  %> </td>
                            <td> Por agregar </td>
                            <td> Por agregar </td>
                        </tr>
                    <% } else %>
                    <% for (CaracteristicasPuestos CP: p.getCaracteristicasPuestos()) { %>
                        <tr> 
                            <td> <%= p.getNombrePuesto() %> </td>
                            <td> <%= p.getSalario()  %> </td>
                            <td onclick="desactivar( <%= p.getActivo() %>,<%= p.getIdPuesto() %> )"> <%= p.getActivo()  %> </td>
                            <td> <%= CP.getCaracteristicas().getHabilidad()  %> </td>
                            <td> <%= CP.getValor()  %> </td>
                        </tr> <% } %> <% } %>   
                </tbody>
            </table>
        </div>
<script>
function addPuestos () {
    puesto = { nombrePuesto:$("#NombreP").val(),
               salario:$("#SalarioP").val(),
               descripcionPuesto:$("#DescP").val(),
               tipoPublicacion: $("input[name='TpPublicacion']:checked").val()
        };
    data=new FormData();
    data.append("puesto",JSON.stringify(puesto));
    $.ajax ({ type: "POST", 
            url:"Puestos_Add", 
            data: data,
            processData: false,
            contentType: false,      
            "success": 
                    function(obj2){
                    window.updatePuestos(obj2);
                },
             "error": function(status){
                     window.alert("error");
                }                    
            });
}

function updatePuestos(p){
	var tr =$("<tr />");
	tr.html("<td>"+p.nombrePuesto+"</td>"+
				"<td>"+p.salario+"</td>"+
                                "<td>"+p.activo+"</td>"+
				"<td>"+"Por agregar"+"</td>"+
				"<td>"+"Por agregar"+"</td>");
	$("#prueba").append(tr);
  }
function desactivar(act, id) {
    puesto = { activo:act,
        idPuesto:id
    };
    if ( puesto.activo === true ) {
        $.ajax({type: "POST", 
                  url:"Desactivar_Puesto", 
                  data: JSON.stringify(puesto), 
                  dataType:"json",
                  success: 
                    function(obj){
                        show(obj); 
                    },
                  error: function(status){
                         window.alert("Error");
                    }                    
                });   
    }
        puesto;
}

function show(per){
	per;
  } 
</script>

    </body>
</html>
