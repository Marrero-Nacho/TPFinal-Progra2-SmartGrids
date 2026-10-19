package Centrales.Registros;

import java.time.LocalDate;

public class RegistroMonitoreoEolico extends RegistroMonitoreo{
    private double velocidadVientoPromedio;

    public RegistroMonitoreoEolico(LocalDate fechaHora, double energiaGenerada, int cantidadIncidencias, double velocidadVientoPromedio) {
        super(fechaHora, energiaGenerada, cantidadIncidencias);
        this.velocidadVientoPromedio = velocidadVientoPromedio;
    }

    public double getVelocidadVientoPromedio() {
        return velocidadVientoPromedio;
    }
}
