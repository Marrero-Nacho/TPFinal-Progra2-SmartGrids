package Centrales.Plantas;

import Centrales.Registros.RegistroMonitoreo;
import Centrales.Registros.RegistroMonitoreoTermico;

public class PlantaTermica extends Planta {

    private String tipoCombustible;
    private double objetivoEmisionesCo2Hora;
    private double capacidadAlmacenamientoCombustible;
    
    public PlantaTermica(String nombre, String direccion, double capacidadMaxima, String fechaApertura, String tipoCombustible, double objetivoEmisionesCo2Hora, double capacidadAlmacenamientoCombustible){
        super(nombre, direccion, capacidadMaxima, fechaApertura);
        
        this.tipoCombustible = tipoCombustible;
        this.objetivoEmisionesCo2Hora = objetivoEmisionesCo2Hora;
        this.capacidadAlmacenamientoCombustible = capacidadAlmacenamientoCombustible; 
    }
    @Override 
    public boolean esRenovable(){
        return false;    
   }
   @Override 
    public boolean cumplioCuota(RegistroMonitoreo registro){
        //para las plantas térmicas, la cuota se cumple si se genera al
        //menos la mitad de la capacidad máxima y la cantidad de emisiones de CO2 es menor
        //que el objetivo de emisión por hora

        //verificacion
        if (!(registro instanceof RegistroMonitoreoTermico)){
            return false;// duda¿ de retornar false o lanzar excepcion
        }
        RegistroMonitoreoTermico rTermico = (RegistroMonitoreoTermico) registro; // ACAAA recorda esto es down casting creo xd
        
        boolean cumplioGeneracion = rTermico.getEnergiaGenerada() >= getCapacidadMinimaCuota();
        boolean cumpleEmision = rTermico.getCo2Generado()< this.objetivoEmisionesCo2Hora;

        return (cumplioGeneracion && cumpleEmision);
   }
   @Override 
    public double getCapacidadMinimaCuota(){
        return (getCapacidadMaxima()/2);
   } 
    public String getTipoCombustible(){
        return this.tipoCombustible;
   }
   public double getObjetivoEmisionesCo2Hora(){
        return this.objetivoEmisionesCo2Hora; 
   }
   public double getCapacidadAlmacenamientoCombustible(){
        return this.capacidadAlmacenamientoCombustible;
   }
}
