/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CRUD;
import modelo.Usuario;

/**
 *
 * @author Juan Benitez
 */
public class ServletUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        String accion = request.getParameter("accion");
        //String num = request.getParameter("num");
         CRUD crudAlguien = new CRUD();
         Usuario clien = (Usuario) request.getSession().getAttribute("usuario");
        try {

            switch (accion) {
                case "login":
                    System.out.println("inciio");
                    Usuario alguien = CRUD.iniciarSesion(request.getParameter("user"), request.getParameter("password"));
                    request.getSession().setAttribute("usuario", alguien);
                    response.sendRedirect("web/usuario.jsp");
                    break;
                case "salir":
                    System.out.println("asdas");
                    request.getSession().setAttribute("usuario", null);
                    request.getSession().invalidate();
                    response.sendRedirect("index.html");
                    break;
                case "modificarG":
                   
                   
                    crudAlguien.setAlguien(new Usuario());
                    crudAlguien.getAlguien().setId(clien.getId());
                    crudAlguien.getAlguien().setPassword(clien.getPassword());
                    crudAlguien.getAlguien().setNombre(clien.getNombre());
                    crudAlguien.getAlguien().setUser(clien.getUser());
                    crudAlguien.getAlguien().setP_ganados(clien.getP_ganados()+1);
                    crudAlguien.getAlguien().setP_perdidos(clien.getP_perdidos());
                    System.out.println("metod=" + crudAlguien.getAlguien().getP_ganados());
                    clien.setP_ganados(clien.getP_ganados()+1);
                    request.getSession().setAttribute("usuario", clien);
                    crudAlguien.modificarUsuario();
                    response.sendRedirect("web/usuario.jsp");
                    break;
                case "modificarP":
                  
                    crudAlguien.setAlguien(new Usuario());
                    crudAlguien.getAlguien().setId(clien.getId());
                    crudAlguien.getAlguien().setPassword(clien.getPassword());
                    crudAlguien.getAlguien().setNombre(clien.getNombre());
                    crudAlguien.getAlguien().setUser(clien.getUser());
                    crudAlguien.getAlguien().setP_ganados(clien.getP_ganados());
                    crudAlguien.getAlguien().setP_perdidos(clien.getP_perdidos()+1);
                    System.out.println("metod=" + crudAlguien.getAlguien().getP_ganados());
                    clien.setP_perdidos(clien.getP_perdidos()+1);
                    request.getSession().setAttribute("usuario", clien);
                    crudAlguien.modificarUsuario();
                    response.sendRedirect("web/usuario.jsp");
                    break;

            }

        } catch (Exception error) {
            System.out.println(accion);
            response.sendRedirect("web/mensaje.jsp?mensaje=" + error.getMessage());
        } finally {
            out.close();
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
