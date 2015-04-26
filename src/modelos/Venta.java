/*
 */

package modelos;

public class Venta {
    public static short venta_id = 0;
    public static double ganancia_total = 0.0;
    
    public short getVenta_id(){
        return venta_id;
    }
    
    public void setVenta_id(short venta_id){
        this.venta_id = venta_id;
    }
    
    public double getGanancia_total(){
        return ganancia_total;
    }
    
    public void setGanancia_total(double ganancia_total){
        this.ganancia_total = ganancia_total;
    }
}
