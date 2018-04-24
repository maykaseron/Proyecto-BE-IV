<%-- 
    Document   : registrooferente
    Created on : Mar 17, 2018, 1:31:33 PM
    Author     : pc
--%>

<%@page import="entidades.Oferente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head >
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <%--  <link rel = "stylesheet" href = "registrooferente.css">  --%>
    <link rel = "stylesheet" href = "css/registro.css">
    <title>Registro oferente</title>
    
    
    
</head>
  
<body>
    <div class="Div_Top"> <p> ImaJobs </p> </div>
    
    <br>

    
    <jsp:useBean id="Ofe" scope="request" type="Oferente" beanName="entidades.Oferente"/>
    
    <form class = "formoferente" action="AddOferente"> <%-- action para darle un evento a type="submit" value="Agregar" --%>
        <div class = "divform">
            
            <h1>REGISTRAR OFERENTES</h1>
            
            <label>Nombre </label> <input type="text" required name="primernombre" value=<%= Ofe.getNombreOferente() %> ><br><br>
            
            <label>Apellido </label> <input type="text" required name="apellido"value=<%= Ofe.getPrimerApellido() %>><br><br>

            <label>Cédula </label> <input type="text" required name="cedula" value=<%= Ofe.getCedulaOferente() %>><br><br>

            <label>E-mail </label> <input type="email" name="email" value=<%= Ofe.getCorreoOferente() %> ><br><br>

            <label>Celular </label> <input type="text" required name="celular" value=<%= Ofe.getCelular() %> ><br><br>

            <label>Nacionalidad </label> <input type="text" required name="nacionalidad" value=<%= Ofe.getNacionalidad() %> ><br><br>

            <label>Contraseña </label> <input type="password" name="contrasena"><br><br>

            <label>Provincia </label>
            <select name="provincia">
                <option value="heredia">Heredia</option>
                <option value="alajuela">Alajuela</option>
                <option value="sanjose">San Jose</option>
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
