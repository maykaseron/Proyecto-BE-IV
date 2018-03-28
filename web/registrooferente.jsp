<%-- 
    Document   : registrooferente
    Created on : Mar 17, 2018, 1:31:33 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head >
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel = "stylesheet" href = "registrooferente.css">
    <title>Registro oferente</title>
</head>
  
<body>
    <h1>REGISTRO OFERENTES</h1>
    
    <form class = "formoferente" action="AddOferente"> <%-- action para darle un evento a type="submit" value="Agregar" --%>
        <div class = "divform">
            <input type="text" placeholder = "Nombre" name="primernombre"><br><br>
            
            <input type="text" placeholder = "Apellido" name="apellido"><br><br>

            <input type="text" placeholder = "Cédula" name="cedula"><br><br>

            <input type="email" placeholder = "e-mail" name="email"><br><br>

            <input type="text" placeholder = "Celular" name="celular"><br><br>

            <input type="text" placeholder = "Nacionalidad" name="nacionalidad"><br><br>

            <input type="password" placeholder = "Contrasena" name="contrasena"><br><br>

            <input type="text" placeholder = "Puesto" name="puesto"><br><br>

            <select name="provincia">
                <option value="heredia">Heredia</option>
                <option value="alajuela">Alajuela</option>
                <option value="sanjose">San Jose</option>
                <option value="cartago">Cartago</option>
                <option value="limon">Limon</option>
                <option value="puntarenas">Puntarenas</option>
                <option value="guanacaste">Guanacaste</option>
            </select>
            <input type="submit" value="Continuar" >
        </div>
    </form><br><br>
    
	  
      <a href = "principal.jsp" target = "_self">Regresar</a>
</body>

</html>
