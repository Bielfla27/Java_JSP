package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String url = "jdbc:postgresql://localhost:5432/Java-jsp?autoReconnect=true" ;
	private static String user = "postgres";
	private static String password = "Gabriel";
	private static Connection connection = null ;
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() {
		conectar();
	}

	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");//Carrega o drive de conexão do banco
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false); // não salva automaticamente		
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		return connection; 
	}

}
