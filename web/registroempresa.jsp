<%-- 
    Document   : registroempresa
    Created on : Mar 17, 2018, 1:30:57 PM
    Author     : pc
--%>

<%@page import="entidades.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head >
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <%--  <link rel = "stylesheet" href = "registroempresa.css">          --%>
    <link rel = "stylesheet" href = "css/registro.css">
    <title>Registro Empresa</title>
</head>

<body> 
    <div class="Div_Top"> <p> ImaJobs </p> </div>
    
    <br>
    
    
    <jsp:useBean id="Emp" scope="request" type="Empresa" beanName="entidades.Empresa"/>
    
    <form class = "formempresa" action="AddEmpresa"> <%-- action para darle un evento a type="submit" value="Agregar" --%>
        <div class = "divempresa">
            
            <h1>REGISTRAR EMPRESAS</h1>
            
            <label>Nombre </label> <input type="text" name="nombreempresa" value=<%= Emp.getNombreEmp() %> ><br><br>

            <label>E-mail </label> <input type="email" name="email" value=<%= Emp.getCorreoEmp() %> ><br><br>

            <label>Password </label> <input type="password" name="contrasena"><br><br>

            <label>Telefono </label> <input type="tel" name="telefono" value=<%= Emp.getTeléfono() %>><br><br>

            <label>Descripcion </label> <input type="text" name="descripcion" value=<%= Emp.getDescripcionEmp() %>><br><br>
            
            <label>Provincia</label>
            <select name="provincia" >
                <option value="heredia">Heredia</option>
                <option value="alajuela">Alajuela</option>
                <option value="san jose">San Jose</option>
                <option value="cartago">Cartago</option>
                <option value="limon">Limon</option>
                <option value="puntarenas">Puntarenas</option>
                <option value="guanacaste">Guanacaste</option>
            </select>
            <input type="submit" value="Agregar" >
        </div>
    </form><br><br>
    
    <a href = "Top5" target = "_self">Regresar</a>

</body>
        
</html>
