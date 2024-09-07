package paquete.ejecucion;

import java.util.List;

import arquituras.tp1.Integrador.*;

public class archivoDeEjecucion {

	public static void main(String[] args) {
		DataBase.crearEsquema();
		
		DataBase.cargarDatos(".\archivos de prueba\clientes.csv", "cliente");
		List<Entity> list=Factory.createDAO("cliente").getAllElements();
		System.out.println("Clientes");
		for(Entity e: list) {
			System.out.println(e);
		}
		
		DataBase.cargarDatos(".\archivos de prueba\productos.csv", "producto");
		list=Factory.createDAO("producto").getAllElements();
		System.out.println("Productos");
		for(Entity e: list) {
			System.out.println(e);
		}
		
		DataBase.cargarDatos(".\archivos de prueba\facturas.csv", "factura");
		list=Factory.createDAO("factura").getAllElements();
		System.out.println("factura");
		for(Entity e: list) {
			System.out.println(e);
		}
		
		DataBase.cargarDatos(".\archivos de prueba\facturas-productos.csv", "factura-producto");
		list=Factory.createDAO("factura-producto").getAllElements();
		System.out.println("factura-producto");
		for(Entity e: list) {
			System.out.println(e);
		}
		
		DataBase.crearForeignKeys();
		
		ProductoDAO pdao=(ProductoDAO) Factory.createDAO("producto");
		List<Entity> list=pdao.getProductoMayorRecaudaci�n("todos");
		System.out.println("Productos de mayor recaudaci�n");
		for(Entity e: list) {
			System.out.println(e);
		}
		
//		DataBase.borrarEsquema();
	}

}
