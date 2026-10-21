package Centrales.Plantas;

import java.time.LocalDate;

public class PlantaSolar extends PlantaRenovable {
	private double horasRadiacionDiaria;
	private Integer cantidadPaneles;
	private double superficieCubierta;
	
	public PlantaSolar(String nombre, String direccion, double capacidadMaxima, String fechaApertura, String fuentePrimaria, double eficienciaPromedio, double superficieCubierta, int cantidadPaneles, double horasRadiacionDiaria) {
		super(nombre, direccion, capacidadMaxima, fechaApertura, fuentePrimaria, eficienciaPromedio);
		this.horasRadiacionDiaria = horasRadiacionDiaria;
		this.cantidadPaneles = cantidadPaneles;
		this.superficieCubierta = superficieCubierta;
	}
}
