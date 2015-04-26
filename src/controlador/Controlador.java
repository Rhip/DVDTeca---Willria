/*
 */
package controlador;

import java.awt.Choice;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controlador {
    public static String mensajeError = "";    
    public void llenarComboBox(Choice choice, String tabla){
        String sql = "select * from "+tabla;
        try{
            Statement sentencia = conexion.Conexion.conexion.createStatement();
            ResultSet result = sentencia.executeQuery(sql);
            while(result.next()){
                choice.add(result.getString(2));
            }
        }catch(SQLException ex){
            mensajeError = ex.getMessage();
        }
    }
    public String getInfo(String tabla, byte indice){
        String valor = "null";
        String consulta = "select * from "+tabla;
        try{
            Statement sentencia = conexion.Conexion.conexion.createStatement();
            ResultSet result = sentencia.executeQuery(consulta);
            while(result.next()){
                if(indice == result.getByte(1)) { 
                    valor=result.getString(2);
                }
            }
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
        }
        return valor;
    }    
}