package arquituras.tp1.Integrador;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO{
	private static String driver="com.mysql.cj.jdbc.Driver";
	private static String uri="jdbc:mysql://localhost:3306/jdbc_mysql_arq";
	
	public Connection openConnection() {
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);	
		}

		try {
			Connection conn=DriverManager.getConnection(uri, "root", "123456");
			conn.setAutoCommit(false);
			return conn;
		}catch (SQLException e) {
			//e.printStackTrace();
			System.out.print("conexión no establecida, saliendo...");
			System.exit(1);
			return null;
		}
	}
	public void closeConnection(Connection conn) {
		try {			
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public abstract List<Entity> getAllElements();
	public abstract Entity getElement(int id);
	public abstract void addElement(Entity e);
	public abstract void deleteElement(int id, Entity e);
}
