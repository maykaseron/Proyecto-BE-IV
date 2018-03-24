<%-- 
    Document   : registroempresa
    Created on : Mar 17, 2018, 1:30:57 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
<head ><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel = "stylesheet" href = "registroempresa.css">
    <title>Registro Empresa</title>
</head>

<body> 
    <h1>REGISTRAR EMPRESAS</h1>
  
    <form class = "formempresa" action=""> <%-- action para darle una ccion a type="submit" value="Agregar" --%>
        <div class = "divempresa">
            <input type="text" placeholder = "Nombre" name="nombreempresa"><br><br>

            <input type="email" placeholder = "e-mail" name="email"><br><br>

            <input type="password" placeholder = "password" name="contrasena"><br><br>

            <input type="tel" placeholder = "telefono" name="telefono"><br><br>

            <input type="text" placeholder = "descripcion" name="descripcion"><br><br>

            <select name="provincia" >
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
	  
    <center>
        <a href = "principal.jsp" target = "_self">Regresar</a>/<center>
  
     
</body>
</html>
