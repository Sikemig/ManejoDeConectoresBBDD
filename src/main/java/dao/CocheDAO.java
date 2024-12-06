package dao;

import database.DbConnection;
import database.SchemaDB;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CocheDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CocheDAO(){ connection = new DbConnection().getConnection(); }

    public boolean addCoche(Coche coche) throws SQLException {
        connection = new DbConnection().getConnection();

        String query = String.format("INSERT into %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                SchemaDB.TAB_COC,
                SchemaDB.COL_COC_MAT, SchemaDB.COL_COC_MAR, SchemaDB.COL_COC_MOD, SchemaDB.COL_COC_COL);

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, coche.getMatricula());
        preparedStatement.setString(2, coche.getMarca());
        preparedStatement.setString(3, coche.getModelo());
        preparedStatement.setString(4, coche.getColor());

        return preparedStatement.execute();
    }


    public void deleteCoche(int id) throws SQLException {
        connection = new DbConnection().getConnection();

        String query = String.format("DELETE FROM %s WHERE %s=?",
                SchemaDB.TAB_COC,
                SchemaDB.COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        if(preparedStatement.executeUpdate() >0){
            System.out.println("Se ha eliminado el vehículo");
        }else {
            System.out.println("No se ha eliminado ningún vehiculo");
        }
    }

    public Coche getCoche(int id) throws SQLException {
        connection = new DbConnection().getConnection();

        String query = String.format("SELECT * FROM %s WHERE %s=?",
                SchemaDB.TAB_COC,
                SchemaDB.COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String matricula= resultSet.getString(SchemaDB.COL_COC_MAT);
            String marca= resultSet.getString(SchemaDB.COL_COC_MAR);
            String modelo=resultSet.getString(SchemaDB.COL_COC_MOD);
            String color=resultSet.getString(SchemaDB.COL_COC_COL);

            return new Coche(id,matricula,marca,modelo,color);
        } else {
            return null;
        }
    }

    public void updateCoche(int id, String matricula, String marca, String modelo, String color) throws SQLException {
        connection = new DbConnection().getConnection();

        String query = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?",
                SchemaDB.TAB_COC,
                SchemaDB.COL_COC_MAT, SchemaDB.COL_COC_MAR, SchemaDB.COL_COC_MOD, SchemaDB.COL_COC_COL,
                SchemaDB.COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, matricula );
        preparedStatement.setString(2, marca);
        preparedStatement.setString(3, modelo);
        preparedStatement.setString(4, color);

        preparedStatement.setInt(5, id);

        if(preparedStatement.executeUpdate() >0){
            System.out.println("Se han actualizado el vehículo");
        }else {
            System.out.println("No se ha actualizado ningún vehiculo");
        }
    }


    public ArrayList<Coche> listadoCoches() throws SQLException {
        connection = new DbConnection().getConnection();

        String query = String.format("SELECT * FROM %s",
                SchemaDB.TAB_COC);

        preparedStatement = connection.prepareStatement(query);

        resultSet = preparedStatement.executeQuery();

        ArrayList<Coche> listaCoches = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(SchemaDB.COL_ID);
            String matricula = resultSet.getString(SchemaDB.COL_COC_MAT);
            String marca = resultSet.getString(SchemaDB.COL_COC_MAR);
            String modelo = resultSet.getString(SchemaDB.COL_COC_MOD);
            String color = resultSet.getString(SchemaDB.COL_COC_COL);
            listaCoches.add(new Coche(id,matricula,marca,modelo,color));
        }
        return listaCoches;
    }

    public void listadoCochesYPasajeros() throws SQLException {
        connection = new DbConnection().getConnection();

        String query = String.format("SELECT coche.%s AS id_coche, coche.%s, coche.%s , coche.%s, " +
                        "pasajero.%s AS id_pasajero, pasajero.%s FROM %s coche " +
                        "INNER JOIN %s pasajero ON coche.%s = pasajero.%s",
                SchemaDB.COL_ID, SchemaDB.COL_COC_MAT, SchemaDB.COL_COC_MAR, SchemaDB.COL_COC_MOD,
                SchemaDB.COL_ID, SchemaDB.COL_PAS_NOM, SchemaDB.TAB_COC,
                SchemaDB.TAB_PAS, SchemaDB.COL_ID, SchemaDB.COL_PAS_COC);

        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int id_coche = resultSet.getInt("id_coche");
            String matricula = resultSet.getString(SchemaDB.COL_COC_MAT);
            String marca = resultSet.getString(SchemaDB.COL_COC_MAR);
            String modelo = resultSet.getString(SchemaDB.COL_COC_MOD);

            int id_pasajero = resultSet.getInt("id_pasajero");
            String nombre = resultSet.getString(SchemaDB.COL_PAS_NOM);

            System.out.println("El coche con ID "+id_coche+" con matrícula "+matricula + " que es un " + marca +" "+ modelo+
             " tiene de pasajeros a: " +nombre + " con ID de pasajero "+ id_pasajero);
        }
    }

    public void listadoPasajerosCoche(int id) throws SQLException {
        connection = new DbConnection().getConnection();

        String query = String.format("SELECT coche.%s AS id_coche, coche.%s, coche.%s , coche.%s, " +
                        "pasajero.%s AS id_pasajero, pasajero.%s FROM %s coche " +
                        "INNER JOIN %s pasajero ON coche.%s = pasajero.%s WHERE id_coche=?",
                SchemaDB.COL_ID, SchemaDB.COL_COC_MAT, SchemaDB.COL_COC_MAR, SchemaDB.COL_COC_MOD,
                SchemaDB.COL_ID, SchemaDB.COL_PAS_NOM, SchemaDB.TAB_COC,
                SchemaDB.TAB_PAS, SchemaDB.COL_ID, SchemaDB.COL_PAS_COC);



        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int id_coche = resultSet.getInt("id_coche");
            String matricula = resultSet.getString(SchemaDB.COL_COC_MAT);
            String marca = resultSet.getString(SchemaDB.COL_COC_MAR);
            String modelo = resultSet.getString(SchemaDB.COL_COC_MOD);

            int id_pasajero = resultSet.getInt("id_pasajero");
            String nombre = resultSet.getString(SchemaDB.COL_PAS_NOM);

            System.out.println("El coche con ID "+id_coche+" con matrícula "+matricula + " que es un " + marca +" "+ modelo+
                    " tiene de pasajeros a: " +nombre + " con ID de pasajero "+ id_pasajero);
        }
    }
}
