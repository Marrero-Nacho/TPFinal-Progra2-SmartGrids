package Centrales.Plantas;

public class PlantaHidraulica extends PlantaRenovable {
	private int cantidadTurbinas;
	private double capacidadEmbalse;
	
	public PlantaHidraulica (String nombre, String direccion, double capacidadMaxima, String fechaApertura, String fuentePrimaria, double eficienciaPromedio, int cantidadTurbinas, double capacidadEmbalse) {
		super(nombre, direccion, capacidadMaxima, fechaApertura, fuentePrimaria, eficienciaPromedio);
		this.cantidadTurbinas = cantidadTurbinas;
		this.capacidadEmbalse = capacidadEmbalse;
	}
}
