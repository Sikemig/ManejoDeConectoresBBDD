package database;

public interface SchemaDB {
    String DB_NAME="concesionarioAE2";

    String TAB_COC="coches";
    String TAB_PAS="pasajeros";

    String COL_ID="id";

    String COL_COC_MAT="matricula";
    String COL_COC_MAR="marca";
    String COL_COC_MOD="modelo";
    String COL_COC_COL="color";

    String COL_PAS_NOM="nombre";
    String COL_PAS_EDA="edad";
    String COL_PAS_PES="peso";
    String COL_PAS_COC="id_coche";
}
