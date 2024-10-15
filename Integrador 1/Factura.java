package arquituras.tp1.Integrador;

public class Factura implements Entity {
	private int idFactura;
	private int idCliente;
	
	public Factura() {
	}
	
	public Factura(int idF, int idC) {
		setIdFactura(idF);
		setIdCliente(idC);
	}
	
	@Override
	public String toString() {
		return "| "+getIdFactura()+" | "+getIdCliente()+" |";
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
}
