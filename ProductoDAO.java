package arquituras.tp1.Integrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO extends DAO{
	private static final DAO instancia=new ProductoDAO();
	
	private ProductoDAO() {
		
	}
	
	public static DAO getInstance() {
		return instancia;
	}

	@Override
	public List<Entity> getAllElements() {
		List<Entity> list=new ArrayList<>();
		try{
			Connection conn=openConnection();
			String select="SELECT * FROM producto";
			PreparedStatement ps=conn.prepareStatement(select);
			ResultSet rs=ps.executeQuery();
			Producto aux;
			while(rs.next()) {
				aux=new Producto(rs.getInt(1),rs.getString(2),rs.getFloat(3));
				list.add(aux);
			}
			closeConnection(conn);
			return list;
		} catch (SQLException e) {
			System.out.println("Error al seleccionar");
			e.printStackTrace();
			System.exit(1);
			return new ArrayList<>();
		}
	}

	@Override
	public Entity getElement(int id) {
		try{
			Connection conn=openConnection();
			String select="SELECT * FROM Producto WHERE id=?";
			PreparedStatement ps=conn.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			Entity elemento;
			if(rs!=null) {
				elemento=new Producto(rs.getInt(1),rs.getString(2),rs.getFloat(3));
			}else {
				System.out.println("No se encuentra el elemento");
				elemento=null;
			}
			ps.close();
			rs.close();
			closeConnection(conn);
			return elemento;
		} catch (SQLException e) {
			System.out.println("Error al seleccionar");
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}

	@Override
	public void addElement(Entity ent) {
		try {			
			Connection conn=openConnection();
			insertElement(conn, (Producto)ent);
			closeConnection(conn);
		}catch (Exception e) {
			System.out.println("Error al insertar elemento");
			e.printStackTrace();
			System.exit(1);
		}

	}
	
	private void insertElement(Connection conn, Producto ent) {
		try {
			String statement="INSERT INTO producto (id_producto, nombre, valor) VALUES(?, ?, ?)";
			PreparedStatement ps;
			ps = conn.prepareStatement(statement);
			ps.setInt(1, ent.getIdProducto());
			ps.setString(2, ent.getNombre());
			ps.setFloat(3, ent.getValor());
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateElement(int id, Entity ent) {
		try {
			Connection conn=openConnection();
			updateProducto(conn, id, (Producto)ent);
			closeConnection(conn);
		}catch(Exception e) {
			System.out.println("Error en la actualización de los datos");
		}
	}
	
	private void updateProducto(Connection conn, int id, Producto ent) {
		try {
			String statement = "UPDATE producto SET nombre = ?, valor = ? WHERE id_producto = ?";
			PreparedStatement ps = conn.prepareStatement(statement);
			ps.setString(1, ent.getNombre());
	        ps.setFloat(2, ent.getValor());
	        ps.setInt(3, id);
	        int actualizado=ps.executeUpdate();
	        
	        if(actualizado==0) {	        	
	        	throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("No se pudo actualizar los datos");
		}
	}

	@Override
	public void deleteElement(int id, Entity ent) {
		try {
			Connection conn=openConnection();
			deleteProducto(conn, id, (Producto)ent);
			closeConnection(conn);
		}catch(Exception e) {
			System.out.println("Error en el borrado de los datos");
		}

	}
	
	public void deleteProducto(Connection conn, int id, Producto ent) {
		try {
			String statement = "DELETE FROM producto WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(statement);
	        ps.setInt(1, id);
	        int actualizado=ps.executeUpdate();
	        
	        if(actualizado==0) {	        	
	        	throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("No se pudo eliminar el elemento");
		}
	}

	public List<Entity> getProductoMayorRecaudación(String tipo){
		List<Entity> list=new ArrayList<>();
		try{
			Connection conn=openConnection();
			String select="";
			switch(tipo) {
			case "todos":
				select="SELECT p.id_producto, p.nombre, p.valor, SUM(fp.cantidad * p.valor) AS recaudacion "
						+ "FROM producto p "
						+ "JOIN factura_producto fp ON p.id_producto = fp.id_producto "
						+ "GROUP BY p.id_producto "
						+ "ORDER BY recaudacion DESC ";
				break;
			case "uno":
				select="SELECT p.id_producto, p.nombre, p.valor, SUM(fp.cantidad * p.valor) AS recaudacion "
				+ "FROM producto p "
				+ "JOIN factura_producto fp ON p.id_producto = fp.id_producto "
				+ "GROUP BY p.id_producto "
				+ "ORDER BY recaudacion DESC "
				+ "LIMIT 1;";
				break;
			}
			PreparedStatement ps=conn.prepareStatement(select);
			ResultSet rs=ps.executeQuery();
			ProductoRecaudado aux;
			while(rs.next()) {
				aux=new ProductoRecaudado(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getInt(4));
				list.add(aux);
			}
			closeConnection(conn);
			return list;
		} catch (SQLException e) {
			System.out.println("Error al seleccionar");
			e.printStackTrace();
			System.exit(1);
			return new ArrayList<>();
		}
	}
}
