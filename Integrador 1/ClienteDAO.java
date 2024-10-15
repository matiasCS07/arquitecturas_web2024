package arquituras.tp1.Integrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO extends DAO {
	private static final DAO instancia=new ClienteDAO();
	
	private ClienteDAO() {
	}
	
	public static DAO getInstance() {
		return instancia;
	}
	
	@Override
	public List<Entity> getAllElements() {
		List<Entity> list=new ArrayList<>();
		try{
			Connection conn=openConnection();
			String select="SELECT * FROM cliente;";
			PreparedStatement ps=conn.prepareStatement(select);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Cliente cl=new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3));
				list.add(cl);
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
			String select="SELECT * FROM cliente WHERE id_cliente=?";
			PreparedStatement ps=conn.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			Entity elemento;
			if(rs!=null) {
				elemento=new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3));
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
			insertElement(conn, (Cliente)ent);
			closeConnection(conn);
		}catch (Exception e) {
			System.out.println("Error al insertar elemento");
			e.printStackTrace();
			System.exit(1);
		}

	}
	
	private void insertElement(Connection conn, Cliente ent) {
		try {
			String statement="INSERT INTO cliente (id_cliente, nombre, email) VALUES(?, ?, ?)";
			PreparedStatement ps;
			ps = conn.prepareStatement(statement);
			ps.setInt(1, ent.getId());
			ps.setString(2, ent.getNombre());
			ps.setString(3, ent.getEmail());
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
			updateCliente(conn, id, (Cliente)ent);
			closeConnection(conn);
		}catch(Exception e) {
			System.out.println("Error en la actualización de los datos");
		}
	}
	
	private void updateCliente(Connection conn, int id, Cliente ent) {
		try {
			String statement = "UPDATE cliente SET nombre = ?, email = ? WHERE id_cliente = ?";
			PreparedStatement ps = conn.prepareStatement(statement);
			ps.setString(1, ent.getNombre());
	        ps.setString(2, ent.getEmail());
	        ps.setInt(3, id);
	        int actualizado=ps.executeUpdate();
	        
	        if(actualizado==0) {	        	
	        	throw new Exception();
			}
	        ps.close();
	        conn.commit();
		}catch(Exception e) {
			System.out.println("No se pudo actualizar los datos");
		}
	}

	@Override
	public void deleteElement(int id, Entity ent) {
		try {
			Connection conn=openConnection();
			deleteCliente(conn, id, (Cliente)ent);
			closeConnection(conn);
		}catch(Exception e) {
			System.out.println("Error en el borrado de los datos");
		}

	}
	
	public void deleteCliente(Connection conn, int id, Cliente ent) {
		try {
			String statement = "DELETE FROM cliente WHERE id_cliente = ?";
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

}
