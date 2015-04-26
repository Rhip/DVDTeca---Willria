/*
 */

package controlador;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import modelos.Detalle;
import modelos.Pelicula;
import modelos.Venta;
import util.Util;

public class DetalleCon implements Gestionar{
    
    @Override
    public boolean insertar(){
        Venta venta = new Venta();
        Pelicula pelicula = new Pelicula();
        Detalle detalle = new Detalle();
        String sql = "insert into Detalle values ('"+venta.getVenta_id()+"', '"
                                                    +pelicula.getPelicula_id()+"', '"
                                                    +detalle.getFecha()+"', '"
                                                    +detalle.getUnidades()+"', '"
                                                    +detalle.getPrecio()+"')";
        try{
            Statement sentencia = Conexion.conexion.createStatement();
            sentencia.executeUpdate(sql);
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
        }
        return true;
    }
}