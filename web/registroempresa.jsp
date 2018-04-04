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
    <h1>REGISTRAR EMPRESAS</h1>
    <jsp:useBean id="Emp" scope="request" type="Empresa" beanName="entidades.Empresa"/>
    
    <form class = "formempresa" action="AddEmpresa"> <%-- action para darle un evento a type="submit" value="Agregar" --%>
        <div class = "divempresa">
            <input type="text" placeholder = "Nombre" name="nombreempresa" value=<%= Emp.getNombreEmp() %> ><br><br>

            <input type="email" placeholder = "e-mail" name="email" value=<%= Emp.getCorreoEmp() %> ><br><br>

            <input type="password" placeholder = "password" name="contrasena"><br><br>

            <input type="tel" placeholder = "telefono" name="telefono" value=<%= Emp.getTeléfono() %>><br><br>

            <input type="text" placeholder = "descripcion" name="descripcion" value=<%= Emp.getDescripcionEmp() %>><br><br>

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
