package SQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {

	public static Connection getConexion() {
		// TODO Auto-generated constructor stub
		
		String conexionURL = "jdbc:sqlserver://192.168.100.118:1433;"
                + "database=Hospital_Crist_Sebas_Grupo3;"
                + "user=s.rosario;"
                + "password=cnum27589H;"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=30;";
		
		try {
			Connection connection = DriverManager.getConnection(conexionURL);
			return connection;
			
		} catch(SQLException e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
    public static void closeConexion(Connection connection) {
        if (connection != null) {
            try {
            	connection.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

}
