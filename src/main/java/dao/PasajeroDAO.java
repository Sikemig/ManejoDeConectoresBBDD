package dao;

import database.DbConnection;
import database.SchemaDB;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PasajeroDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PasajeroDAO(){ connection = new DbConnection().getConnection(); }

    public boolean addPasajero(Pasajero pasajero) throws SQLException {
        connection = new DbConnection().getConnection();

        String query = String.format("INSERT into %s (%s,%s,%s) VALUES (?,?,?)",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_PAS_NOM, SchemaDB.COL_PAS_EDA, SchemaDB.COL_PAS_PES);

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, pasajero.getNombre());
        preparedStatement.setInt(2, pasajero.getEdad());
        preparedStatement.setDouble(3, pasajero.getPeso());

        return preparedStatement.execute();
    }

    public void deletePasajero(int id) throws SQLException {
        connection = new DbConnection().getConnection();

        String query = String.format("DELETE FROM %s WHERE %s=?",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        if(preparedStatement.executeUpdate() >0){
            System.out.println("Se ha eliminado el pasajero");
        }else {
            System.out.println("No se ha eliminado ningún pasajero");
        }
    }


    public Pasajero getPasajero(int id) throws SQLException {
        connection = new DbConnection().getConnection();

        String query = String.format("SELECT * FROM %s WHERE %s=?",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String nombre= resultSet.getString(SchemaDB.COL_PAS_NOM);
            int edad= resultSet.getInt(SchemaDB.COL_PAS_EDA);
            double peso=resultSet.getDouble(SchemaDB.COL_PAS_PES);
            int id_coche=resultSet.getInt(SchemaDB.COL_PAS_COC);

            return new Pasajero(id,nombre,edad,peso,id_coche);
        } else {
            return null;
        }
    }

    public ArrayList<Pasajero> listadoPasajeros() throws SQLException {
        connection = new DbConnection().getConnection();

        String query = String.format("SELECT * FROM %s",
                SchemaDB.TAB_PAS);

        preparedStatement = connection.prepareStatement(query);

        resultSet = preparedStatement.executeQuery();

        ArrayList<Pasajero> listaPasajeros = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(SchemaDB.COL_ID);
            String nombre = resultSet.getString(SchemaDB.COL_PAS_NOM);
            int edad= resultSet.getInt(SchemaDB.COL_PAS_EDA);
            double peso=resultSet.getDouble(SchemaDB.COL_PAS_PES);
            int id_coche=resultSet.getInt(SchemaDB.COL_PAS_COC);
            listaPasajeros.add(new Pasajero(id,nombre,edad,peso,id_coche));
        }
        return listaPasajeros;
    }

    public void ponerCoche(int id_pasajero, int id_coche) throws SQLException {
        connection = new DbConnection().getConnection();

        String query = String.format("UPDATE %s SET %s=? WHERE %s=?",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_PAS_COC,
                SchemaDB.COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id_coche);

        preparedStatement.setInt(2, id_pasajero);

        if(preparedStatement.executeUpdate() >0){
            System.out.println("Se ha añadido el pasajero a un coche");
        }else {
            System.out.println("No se ha podido añadir el pasajero a un coche");
        }
    }


    public void quitarCoche(int id_pasajero) throws SQLException {
        connection = new DbConnection().getConnection();

        String query = String.format("UPDATE %s SET %s=NULL WHERE %s=?",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_PAS_COC,
                SchemaDB.COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id_pasajero);

        if(preparedStatement.executeUpdate() >0){
            System.out.println("Se ha quitado el pasajero de un coche");
        }else {
            System.out.println("No se ha podido quitar el pasajero de un coche");
        }
    }
}
