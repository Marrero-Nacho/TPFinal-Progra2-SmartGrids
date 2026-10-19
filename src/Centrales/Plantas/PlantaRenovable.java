package Centrales.Plantas;

public class PlantaRenovable extends Planta {
	private String fuentePrimaria;
	private double eficienciaPromedio;
	
	public PlantaRenovable(Integer idPlanta, String nombre, String direccion, double capacidadMaxima, String fechaApertura, String fuentePrimaria, double eficienciaPromedio) {
		super(idPlanta, nombre, direccion, capacidadMaxima, fechaApertura);
		this.fuentePrimaria = fuentePrimaria;
		this.eficienciaPromedio = eficienciaPromedio;
	};
}
