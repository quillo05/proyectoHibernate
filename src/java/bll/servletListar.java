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
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import pojo.Persona;

/**
 *
 * @author Paco
 */
public class servletListar extends HttpServlet {
    
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
                
                Persona ObjPersona=ObjOp.mostrarPersona(new Persona(nif),sessionBuild);

                if (ObjPersona != null) {
                    out.print(ObjPersona);
                }
                else {
                    out.print("Error. No existen personas registradas con ese NIF");   
                }
                
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
