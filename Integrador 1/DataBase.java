package arquituras.tp1.Integrador;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DataBase{
	private static String driver="com.mysql.cj.jdbc.Driver";
	private static String uri="jdbc:mysql://localhost:3306/jdbc_mysql_arq";

	public static void crearEsquema() {
		String table="";
		Connection conn=openConnection();
		try {
			table="CREATE TABLE cliente("+
					"id_cliente INT,"+
					"nombre VARCHAR(250),"+
					"email VARCHAR(250),"+
					"PRIMARY KEY(id_cliente))";
			conn.prepareStatement(table).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			table="CREATE TABLE factura("+
					"id_factura INT,"+
					"id_cliente INT,"+
					"PRIMARY KEY(id_factura))";
			conn.prepareStatement(table).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			table="CREATE TABLE producto("+
					"id_producto INT,"+
					"nombre VARCHAR(250),"+
					"valor FLOAT,"+
					"PRIMARY KEY(id_producto))";
			conn.prepareStatement(table).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			table="CREATE TABLE factura_producto("+
					"id_factura INT,"+
					"id_producto INT,"+
					"cantidad INT,"+
					"PRIMARY KEY(id_factura, id_producto))";
			conn.prepareStatement(table).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.commit();
			closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void crearForeignKeys() {
		try {
			Connection conn=openConnection();
			String statement="ALTER TABLE factura "
					+ "ADD CONSTRAINT FK_FacturaCliente "
					+ "FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente)";
			PreparedStatement ps;
				ps = conn.prepareStatement(statement);
			ps.executeUpdate();
			
			statement="ALTER TABLE factura_producto "
					+ "ADD CONSTRAINT FK_ProductoFactura "
					+ "FOREIGN KEY(id_producto) REFERENCES producto(id_producto), "
					+ "ADD CONSTRAINT FK_FacturaProducto "
					+ "FOREIGN KEY(id_factura) "
					+ "REFERENCES factura(id_factura)";
			ps=conn.prepareStatement(statement);
			ps.executeUpdate();
			
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void borrarEsquema() {
		borrarForeignKeys();
		borrarTablas();
	}
	
	private static void borrarTablas() {
		try {
			Connection conn=openConnection();
			String statement="DROP TABLE producto";
			conn.prepareStatement(statement).execute();
			statement="DROP TABLE factura";
			conn.prepareStatement(statement).execute();
			
			statement="DROP TABLE cliente";
			conn.prepareStatement(statement).execute();
			
			statement="DROP TABLE factura_producto";
			conn.prepareStatement(statement).execute();
			
			conn.commit();
			closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void borrarForeignKeys() {
		try {
			Connection conn=openConnection();
			String statement="ALTER TABLE factura_producto "
					+ "DROP FOREIGN KEY FK_FacturaProducto;";
			PreparedStatement ps=conn.prepareStatement(statement);
			ps.executeUpdate();
			
			statement="ALTER TABLE factura_producto "
					+ "DROP FOREIGN KEY FK_ProductoFactura";
			ps=conn.prepareStatement(statement);
			ps.executeUpdate();
			
			statement="ALTER TABLE factura "
					+ "DROP FOREIGN KEY FK_FacturaCliente";
			ps=conn.prepareStatement(statement);
			ps.executeUpdate();
			conn.commit();
			closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection openConnection() {
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

	public static void closeConnection(Connection conn) {
		try {			
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void cargarDatos(String ruta, String clase) {
		CSVParser parser=CSVReader.leerArchivo(ruta);
		for(CSVRecord row: parser) {
			row.iterator();
			Entity elemento=createEntity(clase, row);
			if(elemento!=null) {				
				DAO dao=Factory.createDAO(clase);
				dao.addElement(elemento);
			}
		}
	}

	private static Entity createEntity(String clase, CSVRecord datos) {
		switch(clase) {
		case "cliente":
			return new Cliente(Integer.valueOf(datos.get("id_cliente")), datos.get("nombre"), datos.get("email"));
		case "factura":
			return new Factura(Integer.valueOf(datos.get("id_factura")), Integer.valueOf(datos.get("id_cliente")));
		case "factura-producto":
			return new FacturaProducto(Integer.valueOf(datos.get("id_producto")),Integer.valueOf(datos.get("id_factura")), Integer.valueOf(datos.get("cantidad")));
		case "producto":
			return new Producto(Integer.valueOf(datos.get("id_producto")), datos.get("nombre"), Integer.valueOf(datos.get("valor")));
		default: return null;
		}
	}
}
