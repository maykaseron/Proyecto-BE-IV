<%-- 
    Document   : PuestosCaracteristicas
    Created on : 31/03/2018, 08:54:33 PM
    Author     : anderson
--%>

<%@page import="entidades.Puestos"%>
<%@page import="entidades.CaracteristicasPuestos"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="entidades.Caracteristicas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" href = "css/PuestosCaracteristicas.css" type="text/css"/>
        <title>Puestos Caracteristicas</title>
    </head>
    
    <body>
        <h1> Puestos Ofrecidos por Caracteristicas:  </h1>
        
        <form method="post" action="BuscarCaracterAreaTrabajo" >
            <label> Elija un área de Trabajo: </label> 
                <input type="search" name="areaTrabajo" /> 
                <input type="submit" value="Buscar" >
        </form >
                <br><br>
        <form method="post" action="BuscarPuestosPorEspecial" >
            <label> Elija una especialización: </label>
                <input type="search" name="especializacion" /> 
                <input type="submit" value="Buscar" >
        </form> <br><br>
        
         <form method="post" action="ListarCaracteristicas" >
                <input type="submit" value="Restablecer" >
        </form >       
        <br><br><br> <br><br><br>   
        
        <jsp:useBean id="listaAreaTrabajo" scope="request" type="List<Caracteristicas> " 
                        beanName="java.util.ArrayList"/>
         <jsp:useBean id="listaEspecializacion" scope="request" type="List<Caracteristicas>" 
                         beanName="java.util.ArrayList" />
        
        <div class="areaTrabajo">
            <table>
                <thead>
                    <tr> 
                        <td> Área de trabajo </td> 
                    </tr>
                </thead>
                <tbody>
                    
                    <% if ( listaAreaTrabajo !=null || listaAreaTrabajo.isEmpty() ) { %>
                        <% for (Caracteristicas lc: listaAreaTrabajo)  { %>
                                <tr> 
                                    <td> <%= lc.getAreaTrabajo() %> </td> 
                                </tr>
                        <% } %>  
                    <% } else { %>
                    <tr> 
                        <td> Sin resultados </td> 
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div> 
        <br><br><br>  
        
        <% if ( listaEspecializacion !=null || !listaEspecializacion.isEmpty() ) { %>
        <div class="especializacion">
            <table>
                <thead>
                    <tr> 
                        <td> Especialización </td> 
                    </tr>
                </thead>
                <tbody>
                    
                    <%  for (Caracteristicas lc: listaEspecializacion)  {  %>
                            <tr> 
                                <td> <%= lc.getEspecializacion() %> </td> 
                            </tr>
                    <% } %> 
                </tbody>
            </table>
        </div> <br><br><br>  
        <% } %>
        
        
        <jsp:useBean id="listaPuestos" scope="request" type=" List<CaracteristicasPuestos> "
                     beanName="java.util.ArrayList"/>
        <% if ( listaPuestos !=null || !listaPuestos.isEmpty() ) { %>
        <div class="grid">  
            <table>
                <caption>Puestos disponibles</caption>
                <thead><tr><td>Nombre empresa</td><td>Puesto</td><td>Salario</td><td>AreaTrabajo</td><td>Especializacion</td>
                           <td>Dominio</td>
                        </tr>
                </thead>
                <tbody style="height: 250px;">
                    <% Puestos  puesto = new Puestos (); %>
                    <% for(CaracteristicasPuestos p: listaPuestos){   %>
                        <tr> 
                        <%--    Detalles Puesto      --%>
                            <% if ( p.getPuesto().getIdPuesto() != puesto.getIdPuesto() ) {%>
                            <td><%= p.getPuesto().getEmpresa().getNombreEmp() %></td> 
                            <td> <%= p.getPuesto().getNombrePuesto() %> </td>
                            <td> <%= p.getPuesto().getSalario() %> </td> 
                                 <% puesto.setIdPuesto( p.getPuesto().getIdPuesto() ); %>
                            <% } else { %> 
                                    <td>  </td> 
                                    <td>  </td>
                                    <td>  </td> 
                            <%}%> 
                            
                        <%--    Detalles Caracteris      --%>
                            <td> <%= p.getCaracteristicas().getAreaTrabajo() %> </td>
                            <td> <%= p.getCaracteristicas().getEspecializacion() %> </td> 

                        <%--    Detalles Nivel de espc      --%>
                            <td> <%= p.getValor() %> </td> 
                       </tr>
                    <% } %> 
                </tbody>
            </table> 
        </div>      
        <% } %>
        
     <a href = "Top5" target = "_self">Regresar</a>   
    </body>
</html>
