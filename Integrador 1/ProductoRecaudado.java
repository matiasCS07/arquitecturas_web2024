package arquituras.tp1.Integrador;

public class ProductoRecaudado extends Producto {
	private int recaudacion;
	
	public ProductoRecaudado() {
	}
	
	public ProductoRecaudado(int id, String n, float v, int recaudacion) {
		super(id,n,v);
		setRecaudado(recaudacion);
	}
	
	public String toString() {
		return "| "+getIdProducto()+" | "+getNombre()+" | "+getValor()+" | "+getRecaudado();
	}
	
	public int getRecaudado() {
		return recaudacion;
	}

	public void setRecaudado(int recaudado) {
		this.recaudacion = recaudado;
	}
	
	
}
