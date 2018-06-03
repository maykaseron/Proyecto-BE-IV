/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.google.gson.Gson;
import entidades.Caracteristicas;
import entidades.CaracteristicasOferente;
import entidades.CaracteristicasPuestos;
import entidades.Oferente;
import entidades.Puestos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
                "/Habilida_Oferente", "/Busc_puestos_X_caracteristicas", "/Habilidad_edit", "/Actualizar_Habilidad", "/PDF_Add",
                "/Lista_Habilidades_Add", "/Habilidades_Add", "/Elminar_Habilidad", "/Listar_Carac_Empresa"} )
public class Busqueda extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch(request.getServletPath()){ 
            case "/Top5":
                this.doTop5(request, response); 
                break;
            case "/ListarCaracteristicasPadre":
                this.doListarCaracteristicasPadre (request, response);
            case "/Busc_caracteristicas":  // busca los hijos del padre q se lio dio click
                this.doBusc_caracteristicas (request, response);
                break; 
            case "/Busc_puestos_X_caracteristicas": // cuando se la de doble click este busca
                this.doBusc_puestos_caracteristicas (request, response);
                break;
            case "/Habilidad_edit":
                this.doHabilidad_edit (request, response);
                break;
            case "/Actualizar_Habilidad":
                this.doActualizar_Habilidad (request, response);
                break;
            case "/PDF_Add":
                this.doPDF_Add (request, response);
                break;
            case "/Lista_Habilidades_Add":
                this.doLista_Habilidades_Add (request, response);
                break;
            case "/Habilidades_Add":
                this.doHabilidades_Add (request, response);
                break;
            case "/Elminar_Habilidad":
                this.doElminar_Habilidad (request, response);
                break;
            case "/Listar_Carac_Empresa":
                this.doListar_Carac_Empresa (request, response);
                break;
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
        try{ // busca la caracteristicas q son el papa de los tomates 
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
    
    private void doBusc_caracteristicas(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException  {
        try{ // busca los hijos del padre q se lio dio click
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
            Reader caracteristicaReader = request.getReader();
            Gson gson = new Gson();
            CaracteristicasPuestos[] caracteristicaPuest = gson.fromJson(caracteristicaReader, CaracteristicasPuestos[].class); 
            PrintWriter out = response.getWriter();  
            List<Puestos> lista = Model.instance().getCaracteristicasPuestosNivelPu( caracteristicaPuest  );
          //  Model.instance().pppp(lista);
            response.setContentType("application/json; charset=UTF-8");
            out.write(gson.toJson(lista));
            System.out.print("paso 44");
            response.setStatus(200); // ok with content   
          }
        catch(Exception e){
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("registrooferente.jsp").forward(request, response);
          }	
    }
    
    private void doHabilidad_edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try{
            HttpSession s =  request.getSession( true );
            BufferedReader reader = request.getReader();
            Gson gson = new Gson(); 
            Caracteristicas Car = gson.fromJson(reader, Caracteristicas.class);
            PrintWriter out = response.getWriter();
            CaracteristicasOferente C_O = Model.instance().getCaracteristicasOferenteIdCar( Car.getIdCaracteristica() );
            response.setContentType("application/json; charset=UTF-8");
            out.write(gson.toJson( C_O ));        
            response.setStatus(200); // ok with content

        }
        catch(Exception e){
             response.setStatus(401); //Bad request
        }	
    }
    
    private void doActualizar_Habilidad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try{
            HttpSession s =  request.getSession( true );
            BufferedReader reader = request.getReader();
            Gson gson = new Gson(); 
            CaracteristicasOferente C_O  = gson.fromJson(reader, CaracteristicasOferente.class);
            int valor = C_O.getValor();
 C_O = Model.instance().getCaracteristicasOferenteIdCar( C_O.getCaracteristicas().getIdCaracteristica() );
            PrintWriter out = response.getWriter();
            C_O.setValor(valor); 
            Model.instance().updateCaracteristicasOferente ( C_O );
            Oferente oferente = (Oferente) s.getAttribute("Login_Oferente");
            List<CaracteristicasOferente> listaHabili = Model.instance().getAllCaracteristicasOferenteCed( oferente.getCedulaOferente() );
            response.setContentType("application/json; charset=UTF-8");
            s.setAttribute("lista_habilidad", listaHabili);
            out.write(gson.toJson( listaHabili ));   
            response.setStatus(200); // ok with content
        }
        catch(Exception e){
            response.setStatus(401); //Bad request
        }	
    }
    
    private void doPDF_Add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try{
       //     HttpSession s =  request.getSession( true );
            Gson gson = new Gson(); 
            PrintWriter out = response.getWriter();
            OutputStream pdfFile = new FileOutputStream (new File( "C:/Users/anderson/Desktop/prueba.pdf"  ));
            InputStream pdfReader = request.getPart("foto").getInputStream();
            int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = pdfReader.read(bytes)) != -1) {
                    pdfFile.write(bytes, 0, read);
                }
            pdfFile.close(); 
            response.setStatus(200); // ok with content
        }
        catch(Exception e){
            response.setStatus(401); //Bad request
        }	
    }
    
    private void doLista_Habilidades_Add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try{
            HttpSession s =  request.getSession( true);
            List<Caracteristicas>  ListaCaracterPadres = Model.instance().getAllCaracteristicasPadres();
            request.setAttribute( "CaracteristicasPadres", ListaCaracterPadres ); 
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(ListaCaracterPadres));
            request.getRequestDispatcher("AddHabilidades.jsp").forward( request, response);
        }
        catch(Exception e){
            response.setStatus(401); //Bad request
        }	
    }
    
    private void doHabilidades_Add (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try{
            HttpSession s =  request.getSession( true);
            BufferedReader reader = request.getReader();
            Gson gson = new Gson(); 
            CaracteristicasOferente[] ListaC_O  = gson.fromJson(reader, CaracteristicasOferente[].class);
            PrintWriter out = response.getWriter();
            Oferente ofe = (Oferente) s.getAttribute("Login_Oferente"); 
            for ( CaracteristicasOferente CO : ListaC_O ) { 
                CO.setOferente(ofe);
                Model.instance().addCaracteristicasOferentes(CO );
            }
            List<CaracteristicasOferente> listaHabili = Model.instance().getAllCaracteristicasOferenteCed( ofe.getCedulaOferente() );
            response.setContentType("application/json; charset=UTF-8");
            s.setAttribute("lista_habilidad", listaHabili);
            out.write(gson.toJson( listaHabili ));   
            response.setStatus(200); // ok with content
        }
        catch(Exception e){
            System.out.println( "linea 240" );
            response.setStatus(401); //Bad request
        }	
    }
    
    private void doElminar_Habilidad (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try{
            HttpSession s =  request.getSession( true);
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();  
            CaracteristicasOferente C_O  = gson.fromJson(reader, CaracteristicasOferente.class); System.out.println( C_O.getIdCO() );
            Model.instance().deleteCaracteristicasOferentes( C_O.getIdCO() );
            PrintWriter out = response.getWriter();
            Oferente oferente = (Oferente) s.getAttribute("Login_Oferente");
            List<CaracteristicasOferente> listaHabili = Model.instance().getAllCaracteristicasOferenteCed( oferente.getCedulaOferente() );
            response.setContentType("application/json; charset=UTF-8");
            s.setAttribute("lista_habilidad", listaHabili);
            out.write(gson.toJson( listaHabili ));   
            response.setStatus(200); // ok with content
        }
        catch(Exception e){
            ;
            response.setStatus(401); //Bad request
        }	
    }
    
     private void doListar_Carac_Empresa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try{
            HttpSession s =  request.getSession( true);
            List<Caracteristicas>  ListaCaracterPadres = Model.instance().getAllCaracteristicasPadres();
            request.setAttribute( "CaracteristicasPadres", ListaCaracterPadres ); 
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(ListaCaracterPadres));
            request.getRequestDispatcher("Publicarpuesto.jsp").forward( request, response);
        }
        catch(Exception e){
            response.setStatus(401); //Bad request
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
