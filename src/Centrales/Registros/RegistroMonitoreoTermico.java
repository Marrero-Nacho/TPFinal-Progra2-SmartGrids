package Centrales.Registros;

import java.time.LocalDate;

public class RegistroMonitoreoTermico extends RegistroMonitoreo {
    private double co2Generado;
    private double combustibleUtilizado;

    public RegistroMonitoreoTermico(LocalDate fechaHora, double energiaGenerada, int cantidadIncidencias, double velocidadVientoPromedio, double co2Generado, double combustibleUtilizado) {
        super(fechaHora, energiaGenerada, cantidadIncidencias);
        this.co2Generado = co2Generado;
        this.combustibleUtilizado = combustibleUtilizado;
    }

    public double getCo2Generado() {
        return co2Generado;
    }

    public double getCombustibleUtilizado() {
        return combustibleUtilizado;
    }
}
