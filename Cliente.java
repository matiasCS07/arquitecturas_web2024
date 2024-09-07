package arquituras.tp1.Integrador;

public class Cliente implements Entity{
	private int id;
	private String nombre;
	private String email;
	
	public Cliente() {
	}
	public Cliente(int id, String nombre, String email) {
		setId(id);
		setNombre(nombre);
		setEmail(email);
	}
	
	public String toString() {
		return "| "+getId()+" | "+getNombre()+" | "+getEmail()+" |";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
