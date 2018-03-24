<%-- 
    Document   : principal
    Created on : Mar 17, 2018, 1:26:37 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel = "stylesheet" href = "principal.css">
   
  <head>
    <title>Bolsa Empleo</title>
  </head>
  
  <body>
      
       <div >
      
       <h1> Bolsa Empleo </h1>
        </div>
      
      
      <div class="reg">
          
            <ul>
              <li>
                <a href="#">Registro</a>
                <ul>
                 <li><a href="registroempresa.jsp">Registro empresa</a></li>
                  <li><a href="registrooferente.jsp">Registro oferente</a></li>
                </ul>
              </li>
            </ul>
          
           <form class = "formbusvac" name="busqueda vacantes" action="22-html5-search-input.php" method="POST">
	<input type="search" placeholder = "Buscar vacantes" name="busquedavacante">
	<input type="submit" value="Buscar">
                </form>
          
          </div>
  
          
              
              <!--a href = "registroempresa.html" target = "_self">Registro empresa</a><br><br>
              <a href = "registrooferente.html" target = "_self">Registro oferente</a><br><br></div><br>
		   
		   <!--a href="registrooferente.html" target="frame" >Registro Oferente</a>
		   <a href="registroempresa.html" target="frame" >Registro Empresa</a>
		   <iframe name="frame"> </iframe-->
         
		 
		
  
     
  </body>
</html>