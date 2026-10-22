package Centrales.Plantas;

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
}
