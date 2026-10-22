package Centrales.Plantas;

import Centrales.Registros.RegistroMonitoreo;

public abstract class PlantaRenovable extends Planta {
	private String fuentePrimaria;
	private double eficienciaPromedio;
	
	public PlantaRenovable(String nombre, String direccion, double capacidadMaxima, String fechaApertura, String fuentePrimaria, double eficienciaPromedio) {
		super(nombre, direccion, capacidadMaxima, fechaApertura);
		this.fuentePrimaria = fuentePrimaria;
		this.eficienciaPromedio = eficienciaPromedio;
	}

	// getCapacidadMinimaCuota() queda como abstracto, la implementacion se dara en cada clase hija de PlantaRenoble
	
	@Override 
	public boolean cumplioCuota(RegistroMonitoreo registro){
		return (registro.getEnergiaGenerada() >= this.getCuotaMinima());
	}

	@Override
	public boolean esRenovable() {
		return true;
	};
}
