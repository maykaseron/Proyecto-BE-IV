<%-- 
    Document   : loginAdministrador
    Created on : 04/06/2018, 09:38:23 PM
    Author     : anderson
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="entidades.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" href = "css/Login.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  
        <title>Perfil</title>
    </head>
    <body>
        <% Administrador administrador= (Administrador) session.getAttribute("Login_Administrador"); %>
         <nav>          
            <div class="container-fluid"> 
                <ul  class="nav navbar-nav">
                <%@ include file="encabezado.jsp" %>
                 <%  if (administrador == null) { %> 
                    <li id="Titulo"> ImaJobs </li>
                    <li> <a href="Top5"> Principal  </a> </li>
                <% } %>
                </ul>
            </div>
        </nav>
        
        <jsp:useBean id="Login_Administrador_Oferentes" scope="session" type="List<Oferente>" class="java.util.List"/>        
        <div class="div_tabla_H" >
            <table class="Tabla_hab">
                <caption> <i> Oferentes Por Aprobar </i> </caption>
                <thead> 
                    <tr> 
                        <td> Nombre </td>
                        <td> Correo </td>
                        <td> Estado </td>
                        <td> Cedula </td>
                        <td> Celular </td>
                    </tr>
                </thead>
                <tbody id="prueba"> <% /* la lista es traida se sesion  */%>
                    <% for (Oferente o: Login_Administrador_Oferentes) {  %>
                        <tr>
                            <td> <%= o.getNombreOferente() %> </td>
                            <td> <%= o.getCorreoOferente()  %> </td>
                            <td onclick="aprobarOfe( <%= o.getAprobado() %>,<%= o.getCedulaOferente() %> )"> <%= o.getAprobado()  %> </td>
                            <td> <%= o.getCedulaOferente() %>  </td>
                            <td> <%= o.getCelular() %>  </td>
                        </tr>
                    <% } %>   
                </tbody>
            </table>
        </div>        
        
                <br><br>
        
        <jsp:useBean id="Login_Administrador_Empresa" scope="session" type="List<Empresa>" class="java.util.List"/>        
        <div class="div_tabla_H" >
            <table class="Tabla_hab">
                <caption> <i> Oferentes Por Aprobar </i> </caption>
                <thead> 
                    <tr> 
                        <td> Nombre </td>
                        <td> Correo </td>
                        <td> Estado </td>
                        <td> Teléfono </td>
                    </tr>
                </thead>
                <tbody id="prueba"> <% /* la lista es traida se sesion  */%>
                    <% for (Empresa e: Login_Administrador_Empresa) {  %>
                        <tr>
                            <td> <%= e.getNombreEmp() %> </td>
                            <td> <%= e.getCorreoEmp()  %> </td>
                            <td onclick="aprobarEmp( <%= e.getAprobado() %>,<%= e.getIdEmp() %> )"> <%= e.getAprobado()  %> </td>
                            <td> <%= e.getTeléfono() %>  </td>
                        </tr>
                    <% } %>   
                </tbody>
            </table>
        </div>    
<script>
function aprobarOfe(apro, id) {
    ofer = { aprobado:apro,
        cedulaOferente:id
    };
    if ( ofer.aprobado === false ) {
        $.ajax({type: "POST", 
                  url:"Admi_Aprobar_Oferente", 
                  data: JSON.stringify(ofer), 
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
}

function aprobarEmp(apro, id) {
    empr = { aprobado:apro,
        idEmp:id
    };
    if ( empr.aprobado === false ) {
        $.ajax({type: "POST", 
                  url:"Admi_Aprobar_Empresa", 
                  data: JSON.stringify(empr), 
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
}

function show(per){
	per;
  } 
</script>               
                
    </body>
</html>
