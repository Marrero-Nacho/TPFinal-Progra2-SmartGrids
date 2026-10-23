package Centrales.Plantas;


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
	public double getCapacidadMinimaCuota(){
		return ((getCapacidadMaxima()*getEficienciaPromedio()*this.horasRadiacionDiaria)/24);

	}
	public double getHorasRadiacionDiaria() {
		return horasRadiacionDiaria;
	}

	public void setHorasRadiacionDiaria(double horasRadiacionDiaria) {
		this.horasRadiacionDiaria = horasRadiacionDiaria;
	}

	public Integer getCantidadPaneles() {
		return cantidadPaneles;
	}

	public void setCantidadPaneles(Integer cantidadPaneles) {
		this.cantidadPaneles = cantidadPaneles;
	}

	public double getSuperficieCubierta() {
		return superficieCubierta;
	}

	public void setSuperficieCubierta(double superficieCubierta) {
		this.superficieCubierta = superficieCubierta;
	}
}
