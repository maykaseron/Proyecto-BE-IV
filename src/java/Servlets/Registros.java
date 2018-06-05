/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.google.gson.Gson;
import entidades.Administrador;
import entidades.CaracteristicasOferente;
import entidades.Empresa;
import entidades.Oferente;
import entidades.Puestos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Model;

                /* POR AHORA ESTE NOMBRE     */
@WebServlet(name = "AdministrarEmpresa", urlPatterns = {"/AddEmpresa", "/AddOferente", "/LoginEmpresa","/LoginOferente",
    "/Logout", "/LoginAdministrador"})
public class Registros extends HttpServlet {
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8"); creado por netbeans
        switch(request.getServletPath()){ 
            case "/AddEmpresa":
                 this.doAddEmpresa(request, response);
                 break;
            case "/AddOferente":
                 this.doAddOferente(request, response); 
                 break;
            case "/LoginEmpresa":
                this.doLoginEmpresa (request, response); 
                break;
            case "/LoginOferente":
                this.doLoginOferente (request, response); 
                break;
            case "/LoginAdministrador":
                this.doLoginAdministrador (request, response); 
                break;
            case "/Logout":
                this.doLogout (request, response); 
                break;    
        }
    }

protected void doAddEmpresa(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
    int a=5;Empresa em;
    try{
        HttpSession s =  request.getSession( true);
        String nombreempresa = request.getParameter("nombreempresa");
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");
        String telefono = request.getParameter("telefono");
        String descripcion = request.getParameter("descripcion");
        String ubicacion = request.getParameter( "provincia" );
       
        Empresa emp = new Empresa();
        emp.setNombreEmp(nombreempresa);
        emp.setCorreoEmp(email);
        emp.setContrasena(contrasena); 
        emp.setTeléfono(telefono);
        emp.setDescripcionEmp(descripcion);
        emp.setUbicacionEmp(ubicacion);
        request.setAttribute("Emp", emp);
        request.setAttribute("error", "Gracias por usar el sistema ImaJobs");
        Model.instance().addEmpresa(emp);
        
        request.getRequestDispatcher("registroempresa.jsp").forward( request, response);
    }
    catch(Exception e){
        request.setAttribute("error", e.getMessage());
        request.getRequestDispatcher("registroempresa.jsp").forward( request, response);
    }		
}  	    

protected void doAddOferente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try{
        HttpSession s =  request.getSession( true);
        String primernombre = request.getParameter("primernombre");
        String apellidos = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String email = request.getParameter("email");
        String celular = request.getParameter("celular");
        String nacionalidad = request.getParameter("nacionalidad");
        String contrasena = request.getParameter("contrasena");
        String ubicacion = request.getParameter( "provincia" );

        Oferente ofe = new Oferente();
        ofe.setNombreOferente(primernombre);
        ofe.setPrimerApellido(apellidos);
        ofe.setCedulaOferente(cedula);
        ofe.setCorreoOferente(email);
        ofe.setCelular(celular);
        ofe.setNacionalidad(nacionalidad);
        ofe.setUbicacion(ubicacion);
        ofe.setContrasena(contrasena);
        request.setAttribute("Ofe", ofe);
        Model.instance().addOferente(ofe);
        request.setAttribute("error", "Gracias por usar el sistema ImaJobs");
        request.getRequestDispatcher("registrooferente.jsp").forward( request, response);
    }
    catch(Exception e){
        request.setAttribute("error", e.getMessage());
        request.getRequestDispatcher("registrooferente.jsp").forward( request, response);
    }	
}
    
    private void doLoginEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HttpSession s =  request.getSession( true );
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            Empresa empresa = gson.fromJson(reader, Empresa.class);
            PrintWriter out = response.getWriter(); 
            empresa = Model.instance().getEmpresaLogin( empresa );
            if ( empresa.getAprobado() == true ) { 
                 List<Puestos> listaPuestos = Model.instance().getAllPuestosIDEmpresa ( empresa.getIdEmp() );
                for ( Puestos p : listaPuestos ) {
                    p.setCaracteristicasPuestos( Model.instance().getALLPuestoCaracteristicasPuestos(p.getIdPuesto()) );
                }
                s.setAttribute("Login_Empresa", empresa);
                s.setAttribute("lista_Puestos", listaPuestos);
                response.setContentType("application/json; charset=UTF-8");
                out.write(gson.toJson(empresa)); 
            } 
            response.setStatus(200);
      }
      catch(Exception e){	
          request.setAttribute("error",e.getMessage());
          request.getRequestDispatcher("Error.jsp").forward( request, response);
          response.setStatus(401); //Bad request
      }	
    }
    private void doLoginOferente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HttpSession s =  request.getSession( true );
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            Oferente oferente = gson.fromJson(reader, Oferente.class);
            PrintWriter out = response.getWriter(); 
            oferente = Model.instance().getOferenteLogin(oferente);
            if ( oferente.getAprobado() == true ) {
                List<CaracteristicasOferente> listaHabili = Model.instance().getAllCaracteristicasOferenteCed( oferente.getCedulaOferente() );
                oferente.setListaCaracteristicasOferente( listaHabili );
                s.setAttribute("Login_Oferente", oferente);
                s.setAttribute("lista_habilidad", listaHabili);
                response.setContentType("application/json; charset=UTF-8"); 
                out.write(gson.toJson(oferente)); 
            }
            response.setStatus(200);
      }
      catch(Exception e){	
          request.setAttribute("error",e.getMessage());
          response.setStatus(401); //Bad request
          request.getRequestDispatcher("principal.jsp").forward( request, response);
      }	
    }
    
    private void doLoginAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            System.out.println( "asdadss" );
            HttpSession s =  request.getSession( true );
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            Administrador admi = gson.fromJson(reader, Administrador.class);
            PrintWriter out = response.getWriter(); 
            admi = Model.instance().getAdministradorLogin(admi);
            
            admi.setListaOferentesNuevos( Model.instance().getAllOferenteDesaprobados() );
            admi.setListaEmpresaNuevos( Model.instance().getAllEmpresaAprobar() );
            
            s.setAttribute("Login_Administrador_Oferentes", admi.getListaOferentesNuevos() );
            s.setAttribute("Login_Administrador_Empresa", admi.getListaEmpresaNuevos() );
            s.setAttribute("Login_Administrador", admi);
            response.setContentType("application/json; charset=UTF-8"); 
            out.write(gson.toJson(admi));
            response.setStatus(200);
      }
      catch(Exception e){	
          request.setAttribute("error",e.getMessage());
          response.setStatus(401); //Bad request
          request.getRequestDispatcher("principal.jsp").forward( request, response);
      }	
    }
    
    private void doLogout(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            request.getSession().invalidate();
            request.getRequestDispatcher("principal.jsp").forward( request, response);     
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
