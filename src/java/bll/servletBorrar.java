/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.HibernateUtil;
import dao.Operaciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import pojo.Direccion;
import pojo.Persona;

/**
 *
 * @author Paco
 */
public class servletBorrar extends HttpServlet {
    
    private SessionFactory sessionBuild;
    
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            sessionBuild = HibernateUtil.getSessionFactory();
        }  finally  {}
    }
    
    @Override
    public void destroy () {
        super.destroy();
        try {
            sessionBuild.close();
        } finally  {}
    }

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
        HttpSession session = request.getSession(true);
        
        try {
            
            Operaciones ObjOp = new Operaciones();
            String nif = request.getParameter("nif");
            
            try {
                
                Persona ObjPersona = new Persona (nif);
                
                boolean borrado = ObjOp.borrarPersona(ObjPersona, sessionBuild);
                
                String texto="";
                
                if (borrado) {
                    texto="Persona borrada";
                }
                else {
                    texto="Error. No existe ninguna persona con ese NIF";
                }
                
                out.print("<table>");
                    out.print("<tbody>");
                        out.print("<tr><td colspan=\"9\">"+texto+"</td></tr>");
                    out.print("</tbody>");
                out.print("</table>");
                
                
            } catch (Exception e) {
                System.out.println("Exception: "+e.getMessage());
            }
        
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
