package arquituras.tp1.Integrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaProductoDAO extends DAO {
	private static final DAO instancia=new FacturaProductoDAO();
	
	private FacturaProductoDAO() {
	}
	
	@Override
	public List<Entity> getAllElements() {
		List<Entity> list=new ArrayList<>();
		try{
			Connection conn=openConnection();
			String select="SELECT * FROM factura_producto";
			PreparedStatement ps=conn.prepareStatement(select);
			ResultSet rs=ps.executeQuery();
			FacturaProducto aux;
			while(rs.next()) {
				aux=new FacturaProducto(rs.getInt(1),rs.getInt(2),rs.getInt(3));
				list.add(aux);
			}
			ps.close();
			rs.close();
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
			String select="SELECT * FROM factura_producto WHERE id_factura=?";
			PreparedStatement ps=conn.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			Entity elemento;
			if(rs!=null) {
				elemento=new FacturaProducto(rs.getInt(1),rs.getInt(2),rs.getInt(3));
			}else {
				closeConnection(conn);
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
			insertElement(conn, (FacturaProducto)ent);
			closeConnection(conn);
		}catch (Exception e) {
			System.out.println("Error al insertar elemento");
			e.printStackTrace();
			System.exit(1);
		}

	}
	
	private void insertElement(Connection conn, FacturaProducto ent) {
		try {
			String statement="INSERT INTO factura_producto (id_factura, id_producto, cantidad) VALUES(?, ?, ?)";
			PreparedStatement ps;
			ps = conn.prepareStatement(statement);
			ps.setInt(1, ent.getIdFactura());
			ps.setInt(2, ent.getIdProducto());
			ps.setInt(3, ent.getCantidad());
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateElement(int id, Entity ent) {
		try {
			Connection conn=openConnection();
			updateFacturaProducto(conn, id, (FacturaProducto)ent);
			closeConnection(conn);
		}catch(Exception e) {
			System.out.println("Error en la actualización de los datos");
		}
	}
	
	private void updateFacturaProducto(Connection conn, int id, FacturaProducto ent) {
		try {
			String statement = "UPDATE factura_producto SET cantidad = ? WHERE idFactura = ?";
			PreparedStatement ps = conn.prepareStatement(statement);
			ps.setInt(1, ent.getCantidad());
	        ps.setInt(2, id);
	        int actualizado=ps.executeUpdate();
	        
	        if(actualizado==0) {	        	
	        	throw new Exception();
			}
	        conn.commit();
		}catch(Exception e) {
			System.out.println("No se pudo actualizar los datos");
		}
	}

	@Override
	public void deleteElement(int id, Entity ent) {
		try {
			Connection conn=openConnection();
			deleteFacturaProducto(conn, id, (FacturaProducto)ent);
			closeConnection(conn);
		}catch(Exception e) {
			System.out.println("Error en el borrado de los datos");
		}

	}
	
	public void deleteFacturaProducto(Connection conn, int id, FacturaProducto ent) {
		try {
			String statement = "DELETE FROM factura_producto WHERE id_factura = ?";
			PreparedStatement ps = conn.prepareStatement(statement);
	        ps.setInt(1, id);
	        int actualizado=ps.executeUpdate();
	        
	        if(actualizado==0) {	        	
	        	throw new Exception();
			}
	        conn.commit();
		}catch(Exception e) {
			System.out.println("No se pudo eliminar el elemento");
		}
	}

	public static DAO getInstance() {
		return instancia;
	}

}
