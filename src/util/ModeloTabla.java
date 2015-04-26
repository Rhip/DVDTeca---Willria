/*
 */

package util;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel {       
    public ModeloTabla(String vista){
        super(50, 4);       
        setColumnIdentifiers(columnas(vista).toArray());
    } 
    public ModeloTabla(String vista, int nFilas){
        super(nFilas, 4);
        setColumnIdentifiers(columnas(vista).toArray());
    }
    public final List columnas(String vista){
        String sql = "select * from "+vista;
        List columnas =new ArrayList();
        byte cols;
        try{            
            Statement sta = Conexion.conexion.createStatement();
            ResultSet rs = sta.executeQuery(sql);
            ResultSetMetaData meta = rs.getMetaData();
            cols = (byte)(meta.getColumnCount());
            int i=0;
            while(i<cols){
                columnas.add(meta.getColumnLabel(i+1));
                i++;
            }
        }catch(SQLException ex){
            Util.mensaje(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return columnas;
    }
    @Override
    public boolean isCellEditable(int fila, int columna){
        return false;       
    }
}