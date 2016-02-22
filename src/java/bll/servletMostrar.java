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
import pojo.Direccion;
import pojo.Persona;

/**
 *
 * @author Paco
 */
public class servletMostrar extends HttpServlet {
    
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
            
            try {
                
                List <Persona> personas=ObjOp.listarPersonas(sessionBuild);
                
                if (personas.size() > 0) {
                    
                    out.print("<table>");
                        out.print("<thead><tr>");
                            out.print("<th>PERSONA</th>");
                            out.print("<th>NIF</th>");
                            out.print("<th>NOMBRE</th>");
                            out.print("<th>FECHA_NACIMIENTO</th>");
                            out.print("<th>DIRECCIÓN</th>");
                            out.print("<th>CALLE</th>");
                            out.print("<th>POBLACIÓN</th>");
                            out.print("<th>PROVINCIA</th>");
                            out.print("<th>COD_POSTAL</th>");
                        out.print("</tr></thead>");
                        out.print("<tbody>");
                            Iterator iterPersonas = personas.iterator();
                            int c=0;
                            String estilo = "";
                            while (iterPersonas.hasNext()) {
                                Persona ObjPersona = (Persona)iterPersonas.next();
                                if ((c%2) == 0) {
                                    estilo = "";
                                }
                                else {
                                    estilo = "alt";
                                }
                                out.print("<tr class='"+estilo+"'>");
                                    out.print("<td>"+ObjPersona.getId()+"</td>");
                                    out.print("<td>"+ObjPersona.getNif()+"</td>");
                                    out.print("<td>"+ObjPersona.getNombre()+"</td>");
                                    out.print("<td>"+ObjPersona.getFechaNac()+"</td>");
                                    out.print("<td>"+ObjPersona.getDireccion().getId()+"</td>");
                                    out.print("<td>"+ObjPersona.getDireccion().getCalle()+"</td>");
                                    out.print("<td>"+ObjPersona.getDireccion().getPoblacion()+"</td>");
                                    out.print("<td>"+ObjPersona.getDireccion().getProvincia()+"</td>");
                                    out.print("<td>"+ObjPersona.getDireccion().getCodigoPostal()+"</td>");
                                out.print("</tr>");
                                c++;
                            }
                        out.print("</tbody>");
                    out.print("</table>");
                }
                else {
                    out.print("<table>");
                        out.print("<tbody>");
                        out.print("<tr><td colspan=\"9\">Error. No existen personas registradas</td></tr>");
                        out.print("</tbody>");
                    out.print("</table>");
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
