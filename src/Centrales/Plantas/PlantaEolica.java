package Centrales.Plantas;


import Centrales.Registros.RegistroMonitoreo;
import Centrales.Registros.RegistroMonitoreoEolico;

public class PlantaEolica extends PlantaRenovable {
	private int cantidadAerogeneradores;
	private double alturaTorres;
	private double velocidadMinimaViento;
	
	public PlantaEolica(String nombre, String direccion, double capacidadMaxima, String fechaApertura, String fuentePrimaria, double eficienciaPromedio, int cantidadAerogeneradores, double alturaTorres, double velocidadMinimaViento) {
		super(nombre, direccion, capacidadMaxima, fechaApertura, fuentePrimaria, eficienciaPromedio);
		this.cantidadAerogeneradores = cantidadAerogeneradores;
		this.alturaTorres = alturaTorres;
		this.velocidadMinimaViento = velocidadMinimaViento;
	}
	@Override 
	public double getCapacidadMinimaCuota(){
		return (getCapacidadMaxima()*getEficienciaPromedio());
	}
	
	public int getCantidadAerogeneradores() {
		return cantidadAerogeneradores;
	}

	public void setCantidadAerogeneradores(int cantidadAerogeneradores) {
		this.cantidadAerogeneradores = cantidadAerogeneradores;
	}

	public double getAlturaTorres() {
		return alturaTorres;
	}

	public void setAlturaTorres(double alturaTorres) {
		this.alturaTorres = alturaTorres;
	}

	public double getVelocidadMinimaViento() {
		return velocidadMinimaViento;
	}

	public void setVelocidadMinimaViento(double velocidadMinimaViento) {
		this.velocidadMinimaViento = velocidadMinimaViento;
	}
	public String getReporteHorasSinVelocidadMinima(){     // SOLO PARA METODO 
		StringBuilder sb = new StringBuilder();
		for (int hora=0; hora<24; hora++){
			double maxVelocidadEnHora = -1;
			boolean tieneRegistro = false;
			boolean alcanzoElMinimo = false;

			for( RegistroMonitoreo reg : getRegistrosMonitoreo()){
				if (reg instanceof RegistroMonitoreoEolico regEolico && regEolico.getFechaHora().getHour() == hora){
					tieneRegistro = true;
					double velDeEsaHora = regEolico.getVelocidadVientoPromedio();

					if (velDeEsaHora> this.velocidadMinimaViento){
						alcanzoElMinimo = true; 
						break; // queda descartada por ser mayor al minimo
					}
					if (velDeEsaHora > maxVelocidadEnHora){
						maxVelocidadEnHora = velDeEsaHora;
					}


				}
			}
			if (tieneRegistro && !alcanzoElMinimo){
				String velString = (maxVelocidadEnHora % 1 ==0) ? String.format("%.0f", maxVelocidadEnHora) : String.valueOf(maxVelocidadEnHora);
				sb.append(" ") .append(String.format("%02d", hora)) .append(": ") .append(velString) .append(" m/s\\n"); 
			}
		}
		return sb.toString(); 
			
	}
}
			
	
