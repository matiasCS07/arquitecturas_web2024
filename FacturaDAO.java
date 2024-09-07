package arquituras.tp1.Integrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO extends DAO {
	private static final DAO instancia=new FacturaDAO();
	
	private FacturaDAO() {
	}

	@Override
	public List<Entity> getAllElements() {
		List<Entity> list=new ArrayList<>();
		try{
			Connection conn=openConnection();
			String select="SELECT * FROM factura";
			PreparedStatement ps=conn.prepareStatement(select);
			ResultSet rs=ps.executeQuery();
			Factura aux;
			while(rs.next()) {
				aux=new Factura(rs.getInt(1),rs.getInt(2));
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
			String select="SELECT * FROM factura WHERE id=?";
			PreparedStatement ps=conn.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			Entity elemento;
			if(rs!=null) {
				elemento=new Factura(rs.getInt(1),rs.getInt(2));
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
			insertElement(conn, (Factura)ent);
			closeConnection(conn);
		}catch (Exception e) {
			System.out.println("Error al insertar elemento");
			e.printStackTrace();
			System.exit(1);
		}

	}
	
	private void insertElement(Connection conn, Factura ent) {
		try {
			String statement="INSERT INTO factura (id_factura, id_cliente) VALUES(?, ?)";
			PreparedStatement ps;
			ps = conn.prepareStatement(statement);
			ps.setInt(1, ent.getIdFactura());
			ps.setInt(2, ent.getIdCliente());;
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteElement(int id, Entity ent) {
		try {
			Connection conn=openConnection();
			deleteCliente(conn, id, (Factura)ent);
			closeConnection(conn);
		}catch(Exception e) {
			System.out.println("Error en el borrado de los datos");
		}

	}
	
	public void deleteCliente(Connection conn, int id, Factura ent) {
		try {
			String statement = "DELETE FROM factura WHERE id_factura = ?";
			PreparedStatement ps = conn.prepareStatement(statement);
	        ps.setInt(1, id);
	        int actualizado=ps.executeUpdate();
	        
	        if(actualizado==0) {	        	
	        	throw new Exception();
			}
	        ps.close();
	        conn.commit();
		}catch(Exception e) {
			System.out.println("No se pudo eliminar el elemento");
		}
	}

	public static DAO getInstance() {
		return instancia;
	}

}
