package Misc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vinic
 */
public class Database {
    public Database(){
    }
    
    public static java.sql.Connection getConnection(){
        Connection conn = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sdserver","root","root");
        }catch (SQLException e) {
            System.out.println("Não foi possível se conectar ao banco.");
            return null;
        }catch(ClassNotFoundException e){
            System.out.println("O Drive não foi encontrado.");
            return null;
        }
        return conn;
    }
    
    public static boolean closeConnection(){
        try{
            Database.getConnection().close();
            return true;
        }catch(SQLException e){
            return false;
        }
    }
}
