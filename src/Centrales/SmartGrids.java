package Centrales;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Centrales.Plantas.Planta;
import Centrales.Plantas.PlantaEolica;
import Centrales.Plantas.PlantaHidraulica;
import Centrales.Plantas.PlantaRenovable;
import Centrales.Plantas.PlantaSolar;
import Centrales.Plantas.PlantaTermica;
import Centrales.Registros.RegistroMonitoreo;
import Centrales.Registros.RegistroMonitoreoEolico;
import Centrales.Registros.RegistroMonitoreoTermico;


public class SmartGrids implements ISmartGrids {
	private Map<String, Planta> plantas = new LinkedHashMap<>();
	
	private void verificarNombreRepetido(String nombre) {
		if (plantas.containsKey(nombre)) {
    		throw new IllegalArgumentException("Ya existe una planta con ese nombre");
    	}
	}
	
    @Override
    public void registrarPlantaSolar(
    		String nombre,
    		String direccion,
    		double capacidadMaxima,
    		String fechaHoraPuestaEnFuncionamiento, // dentro de PantaSolar lo renombramos a fechaApertura
    		String fuentePrimaria,
    		double eficienciaPromedio,
    		double superficieCubierta,
    		int cantidadPaneles,
    		double horasRadiacionDiaria
    		) {
    	/*sistema.registrarPlantaSolar(
    	 * "Solar Norte",	nombre
    	 * "Ruta 1",		direccion
    	 * 1000,			capacidad maxima
    	 * INICIO,			fecha apertura
    	 * "Sol",			fuente primaria
    	 * 0.8,				eficiencia promedio
    	 * 1500,			superficie cubierta
    	 * 12,				cantidad de paneles
    	 * 10				horas de radiacion
    	 * );*/
    	PlantaSolar plantaSolar = new PlantaSolar(nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio, superficieCubierta, cantidadPaneles, horasRadiacionDiaria);
    	verificarNombreRepetido(nombre);
    	plantas.put(nombre, plantaSolar);
    }

    @Override
    public void registrarPlantaEolica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, int cantidadAerogeneradores, double alturaTorres, double velocidadMinimaViento) {
    	/*sistema.registrarPlantaEolica(
    	 * "Eolica Sur",	nombre
    	 * "Ruta 2",		direccion
    	 * 800,				capacidad maxima
    	 * INICIO,			fecha apertura
    	 * "Viento",		fuente primaria
    	 * 0.75,			eficiencia promedio
    	 * 4,				cantidad de aerogeneradores
    	 * 80,				altura torres
    	 * 5				velocidad minima de viento
    	 * );*/
    	PlantaEolica plantaEolica = new PlantaEolica(nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio, cantidadAerogeneradores, alturaTorres, velocidadMinimaViento);
    	verificarNombreRepetido(nombre);
    	plantas.put(nombre, plantaEolica);
    }

    @Override
    public void registrarPlantaHidraulica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, int cantidadTurbinas, double capacidadEmbalse) {
    	PlantaHidraulica plantaHidraulica = new PlantaHidraulica(nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio, cantidadTurbinas, capacidadEmbalse);
    	verificarNombreRepetido(nombre);
    	plantas.put(nombre, plantaHidraulica);
    }

    @Override
    public void registrarPlantaTermica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String tipoCombustible, double objetivoEmisionesCo2PorHora, double capacidadAlmacenamientoCombustible) {
    	PlantaTermica plantaTermica = new PlantaTermica(nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, tipoCombustible, objetivoEmisionesCo2PorHora, capacidadAlmacenamientoCombustible);
    	verificarNombreRepetido(nombre);
    	plantas.put(nombre, plantaTermica);
    }

    @Override
    public List<String> consultarPlantas() {
    	List<String> listaPlantas = new ArrayList<>();
    	for(Planta planta : this.plantas.values()) {
    		String estadoActual = planta.estaActiva() ? "(ACTIVA)" : "(INACTIVA)";
    		listaPlantas.add(planta.getNombre() + " " + estadoActual + ": " + planta.getFechaApertura().toString() + ":00");
    	}
        return listaPlantas;
    }

    @Override
    public double capacidadMinimaCuota(String nombre) {
        return plantas.get(nombre).getCapacidadMinimaCuota();
    }

    @Override
    public void activarPlanta(String nombre, String fechaHora) {
    	Planta planta = plantas.get(nombre);
    	planta.activar(LocalDateTime.parse(fechaHora));
    }

    @Override
    public void desactivarPlanta(String nombre, String fechaHora) {
    	Planta planta = plantas.get(nombre);
    	planta.desactivar(LocalDateTime.parse(fechaHora));
    }

    @Override
    public boolean estaActiva(String nombre) {
        return false;
    }

    @Override // PARA PLANTA SOLAR E HIDRAULICA
    public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias) {
    	Planta planta = plantas.get(nombre);
    	if (!(planta instanceof PlantaSolar) && !(planta instanceof PlantaHidraulica)) {
    		throw new IllegalArgumentException("Los parametros de registro no corresponden al tipo de planta Solar o Hidraulica");
    	}
    	RegistroMonitoreo nuevoRegistro = new RegistroMonitoreo(LocalDateTime.parse(fechaHora), energiaGenerada, incidencias);
    	planta.agregarRegistroMonitoreo(nuevoRegistro);
    }

    @Override // PARA PLANTA EOLICA
    public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias, double velocidadViento) {
    	Planta planta = plantas.get(nombre);
    	if (!(planta instanceof PlantaEolica)) {
    		throw new IllegalArgumentException("Los parametros de registro no corresponden al tipo de planta Eolica");
    	}
    	RegistroMonitoreoEolico nuevoRegistro = new RegistroMonitoreoEolico(LocalDateTime.parse(fechaHora), energiaGenerada, incidencias, velocidadViento);
    	planta.agregarRegistroMonitoreo(nuevoRegistro);
    }

    @Override // PARA PLANTA TERMICA
    public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias, double co2Emitido, double combustibleUtilizado) {
    	Planta planta = plantas.get(nombre);
    	if (!(planta instanceof PlantaTermica)) {
    		throw new IllegalArgumentException("Los parametros de registro no corresponden al tipo de planta Termica");
    	}
    	RegistroMonitoreoTermico nuevoRegistro = new RegistroMonitoreoTermico(LocalDateTime.parse(fechaHora), energiaGenerada, incidencias, co2Emitido, combustibleUtilizado);
    	planta.agregarRegistroMonitoreo(nuevoRegistro);
    }

    @Override
    public double totalGenerado(String nombre) {
    	if (!plantas.containsKey(nombre)) {
    		throw new IllegalArgumentException("No existe una planta con ese nombre");
    	}
        Planta p = plantas.get(nombre);
        return p.getTotalGenerado();
    }

    @Override
    public int[] incidenciasPorHora(String nombre) {
        Planta p = plantas.get(nombre);
        return p.getIncidenciasPorHora();
    }

    @Override
    public int[] histogramaCumplimiento(String nombre, String fechaDesde, String fechaHasta) {
    	Planta planta = plantas.get(nombre);
    	LocalDateTime fechaDesdeLDT = LocalDate.parse(fechaDesde).atStartOfDay(); // los tests solo envian fecha. Hay q completar HH:MM:SS para ser LocalDateTime
    	LocalDateTime fechaHastaLDT = LocalDate.parse(fechaHasta).atStartOfDay();
    	
    	if (fechaDesdeLDT.isAfter(fechaHastaLDT)) {
    		throw new RuntimeException("La fecha de inicio no puede ser posterior a la fecha de fin"); 
    	}
    	
    	return planta.getHistogramaCumplimiento(fechaDesdeLDT, fechaHastaLDT);
    }

    @Override
    public Map<String, Integer> plantasRenovablesPorFuente() {
    	Map<String, PlantaRenovable> plantasRenovables = new HashMap<>();
    	Map<String, Integer> plantasPorFuente = new HashMap<>();
    	
    	// PRIEMRO FILTRO SOLO LAS TIPO RENOVABLE
    	for (Map.Entry<String, Planta> entry : plantas.entrySet()) {
    		if (entry.getValue() instanceof PlantaRenovable) {
    			plantasRenovables.put(entry.getKey(), (PlantaRenovable) entry.getValue());
    		}
    	}
    	
    	for(PlantaRenovable pr : plantasRenovables.values()) {
    		String fuentePrimaria = pr.getFuentePrimaria();
    		if (plantasPorFuente.containsKey(fuentePrimaria)) {
    			int nuevaCantidad = plantasPorFuente.get(fuentePrimaria) + 1;
    			plantasPorFuente.put(fuentePrimaria, nuevaCantidad);
    		} else {
    			plantasPorFuente.put(fuentePrimaria, 1);
    		}
    	}
        return plantasPorFuente;
    }

    @Override
    public List<String> plantasQueNuncaCumplieronCuota() {
    	List<String> noCumplieronCuota = new ArrayList<>();
    	
    	for (Planta planta: this.plantas.values()) {
    		if (planta.nuncaCumplioCuota()) {
    			noCumplieronCuota.add(planta.getNombre());
    		}
    	}
    	
        return noCumplieronCuota;
    }

    @Override
    public String horasSinVelocidadMinimaPorPlantaEolica() {
        return "";
    }

    @Override
    public int limpiarMonitoreosSinDatos() {
        return 0;
    }

    @Override
    public List<String> plantasOrdenadasPorEnergiaGenerada() {
        return List.of();
    }

    @Override
    public List<String> plantasInactivasEn(String fechaHora) {
        return List.of();
    }
}
