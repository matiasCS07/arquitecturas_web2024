package arquituras.tp1.Integrador;

public class Factory {
	public static DAO createDAO(String entidad) {
		switch(entidad) {
		case "cliente":
			return ClienteDAO.getInstance();
		case "factura":
			return FacturaDAO.getInstance();
		case "factura-producto":
			return FacturaProductoDAO.getInstance();
		case "producto":
			return ProductoDAO.getInstance();
		default: return null;
		}
	}
}
