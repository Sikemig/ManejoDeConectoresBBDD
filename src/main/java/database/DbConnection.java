package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static Connection connection;

    public Connection getConnection(){

        if(connection==null){
            newConnection();
        }
        return connection;
    }


    private void newConnection() {
        String url = "jdbc:mysql://127.0.0.1:3306/concesionarioAE2";

        try {
            connection = DriverManager.getConnection(url,"root","");
        } catch (SQLException e) {
            System.out.println("Error en la conexión de la BBDD");
        }
        System.out.println("Conexión creada correctamente");
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión");
        }finally {
            connection=null;
        }
    }

}
