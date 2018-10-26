package edu.co.sergio.mundo.dao;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class Conexion {

    private static Connection CONEXION = null;

    public static Connection getConnection() throws URISyntaxException {     //*String HOST = "ec2-54-225-115-234.compute-1.amazonaws.com";          /*String DATABASE = "d9kj4u2gq1cn5h";         String USER = "ztbkpolfrmyqdh";              String PASS = "d5b82b0fa20ff62c7935d09553475a821ac36a790fb5d9e785024f31c3a8d21f";*/
        
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];         /*String dbUrl = "jdbc:postgresql://"                  + HOST + "/" + DATABASE                 + "?user=" + USER + "&password="                 + PASS + "&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";*/
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        if (CONEXION == null) {
            try {
                CONEXION = DriverManager.getConnection(dbUrl, username, password);//CONEXION = DriverManager.getConnection(dbUrl);
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
            }

        }
        return CONEXION;
    }

    public static void closeConnection() {
        try {
            if (CONEXION != null) {
                CONEXION.close();
                CONEXION = null;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
