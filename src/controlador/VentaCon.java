/*
 */

package controlador;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import modelos.Venta;
import util.Util;

public class VentaCon implements Gestionar{    
    @Override
    public boolean insertar(){
        Venta venta = new Venta();
        String sql = "insert into Venta values ('"+venta.getVenta_id()+"', "+
                                                "'"+venta.getGanancia_total()+"')";
        try{
            Statement sentencia = Conexion.conexion.createStatement();
            sentencia.executeUpdate(sql);
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
        }
        return true;
    }
    
    public short numeroVentas(){
        String consulta = "select venta_id from Venta";
        short ventas = 0;
        try{
            Statement sentencia = Conexion.conexion.createStatement();
            ResultSet result = sentencia.executeQuery(consulta);
            while(result.next()){
                ventas++;
            }
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
        }
        return ventas;
    }
    public short getUltimoId(){
        String consulta = "select venta_id from venta";
        short id = 0;
        try{
            Statement sentencia = Conexion.conexion.createStatement();
            ResultSet result = sentencia.executeQuery(consulta);
            while(result.next()){
                id = result.getShort(1);
            }
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
        }
        return id;
    }
    public double setGananciaTotal(List precios){
        double total = 0.0;
        for(byte i=0;i<precios.size();i++){
            String[] temp = Util.rowToArray(precios.get(i).toString());
            total += (Double.parseDouble(temp[0])*Double.parseDouble(temp[1]));
        }
        return total;
    }
}