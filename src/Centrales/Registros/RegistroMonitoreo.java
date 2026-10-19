package Centrales.Registros;

import java.time.LocalDate;

public class RegistroMonitoreo {
    private LocalDate fechaHora;
    private double energiaGenerada;
    private int cantidadIncidencias;

    public RegistroMonitoreo(LocalDate fechaHora, double energiaGenerada, int cantidadIncidencias) {
        this.fechaHora = fechaHora;
        this.energiaGenerada = energiaGenerada;
        this.cantidadIncidencias = cantidadIncidencias;
    }

    public void crearRegistroMonitoreo(RegistroMonitoreo registroMonitoreo) {}
    public LocalDate getFechaHora() {
        return fechaHora;
    }
    public double getEnergiaGenerada() {
        return energiaGenerada;
    }
    public int getCantidadIncidencias() {
        return cantidadIncidencias;
    }
}
