package arquituras.tp1.Integrador;

public class FacturaProducto implements Entity {
	private int idFactura;
	private int idProducto;
	private int cantidad;
	
	public FacturaProducto() {
	}
	
	public FacturaProducto(int idF, int idP, int c) {
		setIdFactura(idF);
		setIdProducto(idP);
		setCantidad(c);
	}
	
	public String toString() {
		return "| "+getIdFactura()+" | "+getIdProducto()+" | "+getCantidad()+" |";
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
