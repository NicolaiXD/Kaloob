package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ConnectionManager;

@WebServlet("/ConnectionManager")
public class InsertController extends HttpServlet {


    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        HttpSession session = request.getSession();
        Connection con = (Connection) session.getAttribute("conn");
        String uName = request.getParameter("txt_name");
        String uItem = request.getParameter("txt_item");
        String uLocation = request.getParameter("txt_location");
        String uSchedule = request.getParameter("txt_schedule");

        System.out.println(uName);
        System.out.println(uItem);
        System.out.println(uLocation);
        System.out.println(uSchedule);
        
        //if statement checks if each column doesn't have an input from user.
        if (!("".equals(uName)) && !("".equals(uItem)) && !("".equals(uLocation)) && !("".equals(uSchedule))) {
//            System.out.println("True");
//            System.out.println("");
            ConnectionManager.setInfo(uName, uItem, uLocation, uSchedule, con);
            response.sendRedirect("welcome.jsp");
            
        } else {
//            System.out.println("False");
            response.sendRedirect("error.jsp");
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
