package Centrales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Centrales.Plantas.Planta;
import Centrales.Plantas.PlantaEolica;
import Centrales.Plantas.PlantaHidraulica;
import Centrales.Plantas.PlantaSolar;


public class SmartGrids implements ISmartGrids {
	private Map<Integer, Planta> plantas = new HashMap<>();
	
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
    	PlantaSolar plantaSolar = new PlantaSolar(0, nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio, superficieCubierta, cantidadPaneles, horasRadiacionDiaria);
    	plantas.put(0, plantaSolar);
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
    	PlantaEolica plantaEolica = new PlantaEolica(1, nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio, cantidadAerogeneradores, alturaTorres, velocidadMinimaViento);
    	plantas.put(1, plantaEolica);
    }

    @Override
    public void registrarPlantaHidraulica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, int cantidadTurbinas, double capacidadEmbalse) {
    	PlantaHidraulica plantaHidraulica = new PlantaHidraulica(2, nombre, direccion, capacidadMaxima, fechaHoraPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio, cantidadTurbinas, capacidadEmbalse);
    	plantas.put(2, plantaHidraulica);
    }

    @Override
    public void registrarPlantaTermica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String tipoCombustible, double objetivoEmisionesCo2PorHora, double capacidadAlmacenamientoCombustible) {

    }

    @Override
    public List<String> consultarPlantas() {
        return List.of();
    }

    @Override
    public double capacidadMinimaCuota(String nombre) {
        return 0;
    }

    @Override
    public void activarPlanta(String nombre, String fechaHora) {

    }

    @Override
    public void desactivarPlanta(String nombre, String fechaHora) {

    }

    @Override
    public boolean estaActiva(String nombre) {
        return false;
    }

    @Override
    public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias) {

    }

    @Override
    public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias, double velocidadViento) {

    }

    @Override
    public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias, double co2Emitido, double combustibleUtilizado) {

    }

    @Override
    public double totalGenerado(String nombre) {
        return 0;
    }

    @Override
    public int[] incidenciasPorHora(String nombre) {
        return new int[0];
    }

    @Override
    public int[] histogramaCumplimiento(String nombre, String fechaDesde, String fechaHasta) {
        return new int[0];
    }

    @Override
    public Map<String, Integer> plantasRenovablesPorFuente() {
        return Map.of();
    }

    @Override
    public List<String> plantasQueNuncaCumplieronCuota() {
        return List.of();
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
