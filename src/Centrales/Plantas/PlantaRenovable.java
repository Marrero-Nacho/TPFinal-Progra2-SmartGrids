package Centrales.Plantas;

import Centrales.Registros.RegistroMonitoreo;

public class PlantaRenovable extends Planta {
	private String fuentePrimaria;
	private double eficienciaPromedio;
	
	public PlantaRenovable(String nombre, String direccion, double capacidadMaxima, String fechaApertura, String fuentePrimaria, double eficienciaPromedio) {
		super(nombre, direccion, capacidadMaxima, fechaApertura);
		this.fuentePrimaria = fuentePrimaria;
		this.eficienciaPromedio = eficienciaPromedio;
	}

	@Override
	public double getCapacidadMinimaCuota() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean cumplioCuota(RegistroMonitoreo registro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esRenovable() {
		// TODO Auto-generated method stub
		return false;
	};
}
