/*
 */

package modelos;

public class Pelicula {
    public static  short pelicula_id;
    public static String nombre;
    public static  byte genero_id;
    public static  byte productora_id;
    public static String duracion;
    public static short unidades;
    public static byte tipo_id;
    public static byte estado_id;

    public short getPelicula_id() {
        return pelicula_id;
    }

    public void setPelicula_id(short dvd_id) {
        this.pelicula_id = dvd_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getGenero_id() {
        return genero_id;
    }

    public void setGenero_id(byte genero_id) {
        this.genero_id = genero_id;
    }

    public byte getProductora_id() {
        return productora_id;
    }

    public void setProductora_id(byte productora_id) {
        this.productora_id = productora_id;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public short getUnidades() {
        return unidades;
    }

    public void setUnidades(short unidades) {
        this.unidades = unidades;
    }

    public byte getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(byte tipo_id) {
        this.tipo_id = tipo_id;
    }

    public byte getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(byte estado_id) {
        this.estado_id = estado_id;
    }   
}