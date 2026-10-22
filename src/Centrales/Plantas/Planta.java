package Centrales.Plantas;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Centrales.Registros.RegistroMonitoreo;

public abstract class  Planta {
	private String nombre;
	private String direccion;
	private double capacidadMax;
	private boolean estado;
	private String fechaApertura;
    private ArrayList<RegistroMonitoreo> registrosMonitoreo;
    private ArrayList<PeriodoInactividad> periodosInactividad;
	
	
    private static class PeriodoInactividad { 
		private LocalDateTime inicio; 
		private LocalDateTime fin; // null si sigue inactiva 
		public PeriodoInactividad(LocalDateTime inicio) { 
			this.inicio = inicio; 
			this.fin = null; 
			} 
			public void setFin(LocalDateTime fin) { 
				this.fin = fin;
				} 
			public boolean estabaInactivaEn(LocalDateTime fechaHora) {
				if (fechaHora.isBefore(inicio)) {
					
					return false; 
					} 
				if (fin == null) { 
					return true;
					
				}
				return !fechaHora.isAfter(fin); 
		}
	}	
	
	public Planta(String nombre, String direccion, double capacidadMaxima, String fechaApertura) {
//	    this.idPlanta = idPlanta; los metodos de IsmartGrids no requieren idPlanta
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMax = capacidadMaxima;
		this.estado = true;// POR DEFAULT true requerimiento de ismart grids
		this.fechaApertura = fechaApertura; 
//		this.cuotaMinima = 0; // POR DEFAULT 0, Eimino, es un dato que varia el metodo de calcular dependiendo la planta (getCapacidadCuotaMInima)
		this.registrosMonitoreo = new ArrayList<>();
		this.periodosInactividad = new ArrayList<>();
	}
	//metodos abstractos, adaptaran su comportamiento dependiendo la clase de planta
	public abstract double getCapacidadMinimaCuota();
	public abstract boolean cumplioCuota(RegistroMonitoreo registro);
	public abstract boolean esRenovable();
	
	//metodos
	public boolean estaActiva() { 
		return this.estado; 
		} 
	public void activar(LocalDateTime fechaHora) { 
		if (!this.estado) {
			this.estado = true; 
			if (!periodosInactividad.isEmpty()) {
				PeriodoInactividad ultimo = periodosInactividad.get(periodosInactividad.size() - 1);
				if (ultimo.fin == null) {
					ultimo.setFin(fechaHora); 
				}
			}
		}
	}
	
	public void desactivar(LocalDateTime fechaHora) { 
		if (this.estado) {
			this.estado = false; 
			periodosInactividad.add(new PeriodoInactividad(fechaHora));
			} 
		} 
	
	public boolean estabaInactivaEn(LocalDateTime fechaHora) {
		for (PeriodoInactividad p : periodosInactividad) {
			if (p.estabaInactivaEn(fechaHora)) {
				return true; 
			}
		}
	return false; 
	}
	
//metodos de registro monitoreos
	public void agregarRegistroMonitoreo(RegistroMonitoreo registro) { 
		if (!this.estado) {
			
			throw new RuntimeException("No se puede registrar monitoreo en una planta inactiva."); 
			
		} 
		if (registro.getFechaHora().isBefore(LocalDateTime.parse(this.fechaApertura))) {
			
			throw new RuntimeException("La fecha de monitoreo es anterior a la puesta en funcionamiento."); 
		}
		
		for (RegistroMonitoreo r : registrosMonitoreo) {
			if (r.getFechaHora().equals(registro.getFechaHora())) {
				throw new RuntimeException("Ya existe un registro para esa fecha y hora.");
			}
		
		} 
		this.registrosMonitoreo.add(registro); 
	}
	public double getTotalGenerado() {
		double total = 0;
		for (RegistroMonitoreo r : registrosMonitoreo) {
			total += r.getEnergiaGenerada();
		}
		return total;
	}
	
	public int[] getIncidenciasPorHora() {
		
		int[] incidencias = new int [24];
		for (RegistroMonitoreo r : registrosMonitoreo) {
			int hora = r.getFechaHora().getHour();
			incidencias[hora] += r.getCantidadIncidencias();
		}
		return incidencias;
	}
	public boolean nuncaCumplioCuota() {
		if (registrosMonitoreo.isEmpty()) {
			return true;
		}
		for (RegistroMonitoreo r : registrosMonitoreo) {
			if (cumplioCuota(r)) {
				return false;
			}
		}
		return true;
		
	}
	public int limpiarMonitoreoSinDatos() {
		ArrayList<RegistroMonitoreo> sinDatos = new ArrayList<>();
		for(RegistroMonitoreo r : registrosMonitoreo) {
			if (r.getEnergiaGenerada() == 0 && r.getCantidadIncidencias() == 0) {
				
				sinDatos.add(r);
			}
			
		}
		registrosMonitoreo.removeAll(sinDatos);
		return sinDatos.size();
	}
	
	public int[] getHistogramaCumplimiento(LocalDateTime desde, LocalDateTime hasta) { 
		int[] hist = new int[24];
		for (RegistroMonitoreo r : registrosMonitoreo) { 
			LocalDateTime fh = r.getFechaHora(); 

			if (!fh.isBefore(desde) && !fh.isAfter(hasta)) { 
				if (cumplioCuota(r)) { 
					int hora = fh.getHour(); 
					hist[hora]++; 
				}
			}
		}
		return hist; 
	}
//getters
	public String getNombre() {
		return this.nombre;
	}
	public String getDireccion() {
		return this.direccion;
	}
	
	public double getCapacidadMaxima() {
		return this.capacidadMax;
	}
	public LocalDateTime getFechaApertura() {
		return LocalDateTime.parse(this.fechaApertura);
	}
	
	public ArrayList<RegistroMonitoreo> getRegistrosMonitoreo(){
		return this.registrosMonitoreo;
	}
	
}
