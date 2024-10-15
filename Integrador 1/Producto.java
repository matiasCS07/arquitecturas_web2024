package arquituras.tp1.Integrador;

public class Producto implements Entity {
	private int idProducto;
	private String nombre;
	private float valor;
	
	public Producto() {
	}
	
	public Producto(int id, String n, float v) {
		setIdProducto(id);
		setNombre(n);
		setValor(v);
	}
	
	public String toString() {
		return "| "+getIdProducto()+" | "+getNombre()+" | "+getValor()+" |";
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
}
