/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import entidades.Empresa;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Model;

                /* POR AHORA ESTE NOMBRE     */
@WebServlet(name = "AdministrarEmpresa", urlPatterns = {"/AddEmpresa", "/AddOferente"})
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
        }
    }

protected void doAddEmpresa(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
    try{
        HttpSession s =  request.getSession( true);
        String nombreempresa = request.getParameter("nombreempresa");
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");
        String telefono = request.getParameter("telefono");
        String descripcion = request.getParameter("descripcion");
        
        Empresa emp = new Empresa();
        emp.setNombreEmp(nombreempresa);
        emp.setCorreoEmp(email);
        emp.setCorreoEmp(contrasena); 
        emp.setTeléfono(telefono);
        emp.setDescripcionEmp(descripcion);
        emp.setUbicacionEmp("Por defecto");
        
        Model.instance().addEmpresa(emp);
        request.getRequestDispatcher("principal.jsp").forward( request, response);
    }
    catch(Exception e){
        request.getRequestDispatcher("index.xhtml").forward( request, response);
    }		
}  	    

protected void doAddOferente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try{
        request.getRequestDispatcher("registrooferente.jsp").forward( request, response);
    }
    catch(Exception e){
        request.getRequestDispatcher("registrooferente.jsp").forward( request, response);
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