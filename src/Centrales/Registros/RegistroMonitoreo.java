package Centrales.Registros;

import java.time.LocalDateTime;

public class RegistroMonitoreo {
    private LocalDateTime fechaHora;
    private double energiaGenerada;
    private int cantidadIncidencias;

    public RegistroMonitoreo(LocalDateTime fechaHora, double energiaGenerada, int cantidadIncidencias) {
        this.fechaHora = fechaHora;
        this.energiaGenerada = energiaGenerada;
        this.cantidadIncidencias = cantidadIncidencias;
    }

    public void crearRegistroMonitoreo(RegistroMonitoreo registroMonitoreo) {}
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public double getEnergiaGenerada() {
        return energiaGenerada;
    }
    public int getCantidadIncidencias() {
        return cantidadIncidencias;
    }
}
