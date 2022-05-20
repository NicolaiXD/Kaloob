package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;


public class ConnectionManager {    
    Connection conn;

    public Connection establishConn(String className, String username, String password, String url,
            String hostname, String dbport, String dbname) {
    try {
            Class.forName(className);
            StringBuffer dburl = new StringBuffer(url)
                    .append("://")
                    .append(hostname)
                    .append(":")
                    .append(dbport)
                    .append("/")
                    .append(dbname);
            conn = DriverManager.getConnection(dburl.toString(), username, password);
    } catch (SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
            sqle.printStackTrace();
    } catch (ClassNotFoundException nfe) {
        System.out.println("ClassNotFoundException error occured - "
                    + nfe.getMessage());
        nfe.printStackTrace();
        } return conn;
    
    }
    
   
    public static int setInfo(String name, String item, String location, String schedule, Connection conn) {
        
            try {
            
                
                    String query = "Insert into kaloob_info(name, item, location, schedule) values(?, ?, ?, ?);";
                    
                    PreparedStatement ps = conn.prepareStatement(query);
                    
                    ps.setString(1, name);
                    ps.setString(2, item);
                    ps.setString(3, location);
                    ps.setString(4, schedule);
                    int ip = ps.executeUpdate();
                    
                    return ip;
                
            }
            catch(SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
            }
        return 0;
        }
    
    
    public static int updateInfo(String name, String item, String location, String schedule, Connection conn, int id) {
        
        //int ResultSet = 0;
        try {
            String query = "UPDATE kaloob_info set name = ?, item = ?, location = ?, schedule = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, item);
            ps.setString(3, location);
            ps.setString(4, schedule);
            ps.setString(5, String.valueOf(id));
            
            //Perform update query
            return ps.executeUpdate();
            
            

        } 
        catch (SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
            
        return 0;
        }
        
    }
    
    public static ResultSet viewTable (Connection conn) {
        try {
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM kaloob_info");
        return result;
        }
        catch (SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
        }
        
        
        return null;
    }
    
    
    
    
    
    public static int deleteInfo(int id, Connection conn) {
        try {
            if (conn !=  null) {
            String query = "delete from kaloob_info where id = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setInt(1, id);
            ps.executeUpdate();
            return 1;
            }
        }
        catch(SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
        }
        return 0;
    }
    
    
    
                
    
}
    
