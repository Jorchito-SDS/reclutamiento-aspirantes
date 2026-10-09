package main.java.com.reclutaraspirantes.jm.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {

    public Connection obtenerConexion() throws SQLException {
        try {
       
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver de MySQL (Connector/J): " + e.getMessage(), e);
        }
        
       
        return DriverManager.getConnection(
                CredentialsDb.URL_MYSQL_DB,
                CredentialsDb.USER_DB,
                CredentialsDb.PASS_DB
        );
    }
}