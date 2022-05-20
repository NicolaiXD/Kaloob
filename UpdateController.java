package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ConnectionManager;

public class UpdateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Connection con = (Connection) session.getAttribute("conn");
        String uName = request.getParameter("txt_name");
        String uItem = request.getParameter("txt_item");
        String uLocation = request.getParameter("txt_location");
        String uSchedule = request.getParameter("txt_schedule");
        int uid = Integer.parseInt(request.getParameter("id"));


        if (con != null) {
            int result = ConnectionManager.updateInfo(uName, uItem, uLocation, uSchedule, con, uid);

                if(result == 0){
                    response.sendRedirect("error.jsp");
                    return;
                }
                ResultSet results = ConnectionManager.viewTable(con);
                request.setAttribute("results", results);
                request.getRequestDispatcher("homepage.jsp").forward(request, response);
            }
            else{
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
