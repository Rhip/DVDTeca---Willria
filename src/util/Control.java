/*
 */

package util;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Control {
    String[][] matrizDatos;
    byte cols;
    
    public byte getCols(){
        return cols;
    }
    /*private void llenarLista(String tabla){
        String consulta = "select * from "+tabla;   
        List<String> cols = new ArrayList();
        List<List> filas = new ArrayList();        
        try{            
            Statement sentencia = Conexion.conexion.createStatement();
            ResultSet result = sentencia.executeQuery(consulta);            
            ResultSetMetaData data = result.getMetaData();            
            while(result.next()){
                for(int i=0;i<data.getColumnCount();i++){
                    cols.add(result.getString(i+1));
                }
                filas.add(cols);
            }
        }catch(SQLException ex){
            util.Util.mensaje(ex.getMessage(), "Error");
        }
        arregloDatos = toMatrix(filas, cols);
    }*/
    public String[][] getArrayDatos(String tabla){        
        llenarArreglo(tabla);
        return matrizDatos;
    }
    private void llenarArreglo(String vista){
        String[][] datos = null;
        String consulta = "select * from "+vista;
        byte filas = 0;
        try {
            Statement sentencia = Conexion.conexion.createStatement();
            ResultSet result = sentencia.executeQuery(consulta);
            ResultSetMetaData data = result.getMetaData();
            cols = (byte) data.getColumnCount();
            datos = new String[100][cols];
            byte i = 0;
            while(result.next()){
                filas = i;
                for(byte j=0;j<cols;j++){
                    datos[i][j] = result.getString(j+1);
                }
                i++;
            }
        } catch (SQLException ex) {
            util.Util.mensaje(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }        
        matrizDatos = depurarMatriz(datos, matrizDatos, (byte)(filas+1), cols);
    }    
    public String[][] depurarMatriz(String[][] matriz, String[][] nueva, byte filas, byte col){
        nueva = new String[filas][col];
        for(byte i=0;i<filas;i++){
            for(byte j=0;j<col;j++){
                nueva[i][j]=matriz[i][j];
            }
        }
        return nueva;
    }
}