package Centrales.Plantas;

import java.time.LocalDate;

public class Planta {
	private Integer idPlanta;
	private String nombre;
	private String direccion;
	private double capacidadMax;
	private boolean estado;
	private String fechaApertura;
	private double cuotaMinima;
//	private ArrayList<RegistroMonitoreo> registrosMonitoreo;
	
	public Planta(Integer idPlanta, String nombre, String direccion, double capacidadMax, String fechaApertura) {
		this.idPlanta = idPlanta;
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMax = capacidadMax;
		this.estado = false;
		this.fechaApertura = fechaApertura;
		this.cuotaMinima = 0; // POR DEFAULT 0, en los ejemplos que pasaron los profes no hay cuota minima aun
//		this.registrosMonitoreo = new ArrayList<>();
	}
}
