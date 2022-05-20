package controller;
//import dao.idMethod;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import model.ConnectionManager;
import model.ConnectionManager;

public class ItemController extends HttpServlet {

    Connection conn;

        public void init(ServletConfig config) throws ServletException {
        super.init(config);

           String className = config.getInitParameter("jdbcClassName");
           String username = config.getInitParameter("dbUserName");
           String password = config.getInitParameter("dbPassword");
           String url = config.getInitParameter("jdbcDriverURL");
           String hostname = config.getInitParameter("dbHostName");
           String dbport = config.getInitParameter("dbPort");
           String dbname = config.getInitParameter("databaseName");
           
           ConnectionManager manageComs  = new ConnectionManager();
           conn = manageComs.establishConn(className, username, password, url, hostname, dbport, dbname); 
            
    }
        
//            ConnectionManager nn = new ConnectionManager();
//            nn.establishConn(username, password, url.toString(), className);
//            ServletContext ar = getServletContext();
//            ar.setAttribute("connected", ar);
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
       
        
        //select method
        HttpSession session = request.getSession();
        session.setAttribute("conn", conn);
        try {
        if (conn != null) {

        String uHome = request.getParameter("home");
        String klbTable = request.getParameter("infoTable");
        
        int uid;
        
        if (klbTable != null){
            Statement stmt = conn.createStatement();
            System.out.println(uHome);
            ResultSet result = stmt.executeQuery("SELECT * FROM kaloob_info");
        
            if (result != null) {
                uid = result.getInt("id");
                System.out.println(uid);
            }
        }

        if (uHome != null){
            Statement stmt = conn.createStatement();
                System.out.println(uHome);
                ResultSet result = stmt.executeQuery("SELECT * FROM kaloob_info");

                if (result != null) {
                    request.setAttribute("results", result);
                    request.getRequestDispatcher("homepage.jsp").forward(request, response);
                }
        }
  
        }
        else {
            response.sendRedirect("error.jsp");        

        }

        }
        catch (SQLException sqle) {
            response.sendRedirect("error.jsp");
        }
        
}




    
    
    

    
    
   
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
