package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseConnection {


    public static Connection getConexion() { 
    	
		String connectionUrl =
                "jdbc:sqlserver://192.168.100.118:1433;"
                        + "database=Hospital_Crist_Sebas_Grupo3;"
                        + "user=c.ventura;"
                        + "password=Escorpion!270931;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";
        

        try {
        	Connection conexion = DriverManager.getConnection(connectionUrl);
        	return conexion;

        } 
        catch (SQLException e) {
                System.out.println(e.toString());
                return null;
        }
        
    }
    
    public static void closeConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}