/*
 */

package modelos;

import java.sql.Date;

public class Detalle {
    public static short pelicula_id;
    public static short venta_id;
    public static String fecha;
    public static byte unidades;
    public static double precio;

    public short getPelicula_id() {
        return pelicula_id;
    }

    public void setPelicula_id(short pelicula_id) {
        this.pelicula_id = pelicula_id;
    }

    public short getVenta_id() {
        return venta_id;
    }

    public void setVenta_id(short venta_id) {
        this.venta_id = venta_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public byte getUnidades() {
        return unidades;
    }

    public void setUnidades(byte unidades) {
        this.unidades = unidades;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}