/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.google.gson.Gson;
import entidades.Caracteristicas;
import entidades.CaracteristicasPuestos;
import entidades.Puestos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Model;

// "/ListarCaracteristicas","/BuscarCaracterAreaTrabajo","/BuscarPuestosPorEspecial"
@WebServlet(name = "Busqueda", urlPatterns = {"/Top5","/ListarCaracteristicasPadre","/BuscarCarac","/Busc_caracteristicas",
                "/Habilida_Oferente","/Busc_puestos_X_caracteristicas"} )
public class Busqueda extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ListaEspecializacion = new ArrayList();
        ListaPuetos = new ArrayList();
        switch(request.getServletPath()){ 
            case "/Top5":
                this.doTop5(request, response); 
                break;
            case "/ListarCaracteristicasPadre":
                this.doListarCaracteristicasPadre (request, response);
                break;
            case "/BuscarCarac":
                this.doBuscarCarac (request, response);
                break;    
            case "/Busc_caracteristicas":
                this.doBusc_caracteristicas (request, response);
                break; 
            case "/Busc_puestos_X_caracteristicas":
                this.doBusc_puestos_caracteristicas (request, response);
                break;    
            /*
            case "/ListarCaracteristicas":
                this.doListarCaracteristicas (request, response);
                break;
            case "/BuscarCaracterAreaTrabajo":
                this.doBuscarPorCaracteriscas (request, response);
                break;
            case "/BuscarPuestosPorEspecial":
                this.doBuscarPuestosPorEspecial (request, response);
                break;*/
        }
    }
    
    private void doTop5(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try{
            List<Puestos> puestos = Model.instance().ListTop5();
            request.setAttribute("Top5puestos",puestos);
            request.getRequestDispatcher("principal.jsp").forward( request, response);
          }
        catch(Exception e){
                String error = e.getMessage(); 	
                request.setAttribute("error",error);
                request.getRequestDispatcher("principal.jsp").forward( request, response);
          }		
    }
    
    private void doListarCaracteristicasPadre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try{
            HttpSession s =  request.getSession( true);
            List<Caracteristicas>  ListaCaracterPadres = Model.instance().getAllCaracteristicasPadres();
            request.setAttribute( "CaracteristicasPadres", ListaCaracterPadres ); 
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(ListaCaracterPadres));
            request.getRequestDispatcher("PuestosCaracteristicas.jsp").forward( request, response);
          }
        catch(Exception e){
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("registrooferente.jsp").forward(request, response);
          }	
    }
    
    private void doBuscarCarac(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException  {
        try{
            //int areaTrabajo = Integer.parseInt( request.getParameter( "aaa" ) );
            //List<Caracteristicas>  ListaCaracterHijos = Model.instance().getBuscarCaracteristicas(areaTrabajo);
            //request.setAttribute( "CaracteristicasHijos", ListaCaracterHijos );  
            request.getRequestDispatcher("PuestosCaracteristicas.jsp").forward( request, response);
          }
        catch(Exception e){
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("registrooferente.jsp").forward(request, response);
          }	
    }
    
    private void doBusc_caracteristicas(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException  {
        try{
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            Caracteristicas caracteristica = gson.fromJson(reader, Caracteristicas.class);
            PrintWriter out = response.getWriter();
    List<Caracteristicas>  ListaCaracterHijos = Model.instance().getBuscarCaracteristicas( caracteristica.getIdCaracteristica() );
            request.setAttribute( "CaracteristicasHijos", ListaCaracterHijos );  
            response.setContentType("application/json; charset=UTF-8");
            out.write(gson.toJson(ListaCaracterHijos));
            response.setStatus(200); // ok with content
          }
        catch(Exception e){
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("registrooferente.jsp").forward(request, response);
          }	
    }
    private void doBusc_puestos_caracteristicas(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException  {
        try{
            System.out.print("paso 11");
            /*
            Reader caracteristicaReader = request.getReader();
            Gson gson = new Gson();
            Caracteristicas[] caracteristica = gson.fromJson(caracteristicaReader, Caracteristicas[].class); 
            PrintWriter out = response.getWriter(); 
            response.setContentType("application/json; charset=UTF-8");
            out.write(gson.toJson(caracteristica));
            response.setStatus(200); // ok with content   
            */ 
            System.out.print(  request.getPart("listaId").getInputStream() );
            System.out.print("22");
           /* Reader caracteristicaReader = new BufferedReader(new InputStreamReader(request.getPart("listaId_C").getInputStream()));
            Gson gson = new Gson();
            
            Caracteristicas[] caracteristica = gson.fromJson(caracteristicaReader, Caracteristicas[].class); 
            PrintWriter out = response.getWriter();
            response.setContentType("application/json; charset=UTF-8");
            out.write(gson.toJson(caracteristica));
            System.out.print("555");*/
            response.setStatus(200); // ok with content     
          }
        catch(Exception e){
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("registrooferente.jsp").forward(request, response);
          }	
         
    }
    
    
    
    
    
    
    
    
    
    
    
    List<Caracteristicas> ListaEspecializacion;
    List<Caracteristicas> listaAreaTrabajo; List<CaracteristicasPuestos> ListaPuetos;
    private void doListarCaracteristicas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try{
            ListaEspecializacion = new ArrayList();
            ListaPuetos = new ArrayList();
            HttpSession s =  request.getSession( true);
          /*listaAreaTrabajo = Model.instance().getCaracteristicasAreaTrabajo();*/
            request.setAttribute("listaAreaTrabajo", listaAreaTrabajo);
            
            request.setAttribute("listaEspecializacion",ListaEspecializacion); 
            request.setAttribute( "listaPuestos", ListaPuetos ); 
            
            request.getRequestDispatcher("PuestosCaracteristicas.jsp").forward( request, response);
          }
        catch(Exception e){
                request.getRequestDispatcher("principal.jsp").forward(request, response);
          }	
    }
    
    private void doBuscarPorCaracteriscas(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException  {
        try{
            HttpSession s =  request.getSession( true);
            String areaTrabajo = request.getParameter( "areaTrabajo" );
            System.out.println(areaTrabajo);
        /*    ListaEspecializacion = Model.instance().getCaracteristicasEspecializ(areaTrabajo);*/
            request.setAttribute("listaEspecializacion",ListaEspecializacion);
            
            request.setAttribute("listaAreaTrabajo", listaAreaTrabajo);
            request.setAttribute( "listaPuestos", ListaPuetos );
            
            request.getRequestDispatcher("PuestosCaracteristicas.jsp").forward( request, response);
          }
        catch(Exception e){
                request.getRequestDispatcher("principal.jsp").forward(request, response);
          }	
    }
    
    private void doBuscarPuestosPorEspecial(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException  {
        try{
            HttpSession s =  request.getSession( true);
            request.setAttribute("listaAreaTrabajo", listaAreaTrabajo);
            request.setAttribute("listaEspecializacion",ListaEspecializacion); 
            
            String especialización = request.getParameter( "especializacion" );
          /*  ListaPuetos = Model.instance().getPuestosPorCaracteristicas( especialización );*/
            request.setAttribute( "listaPuestos", ListaPuetos );
            request.getRequestDispatcher("PuestosCaracteristicas.jsp").forward( request, response);
          }
        catch(Exception e){
                request.getRequestDispatcher("principal.jsp").forward(request, response);
          }	
        
    }
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

    

}
