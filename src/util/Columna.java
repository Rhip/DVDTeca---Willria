/*
 */

package util;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class Columna extends TableColumn{
    DefaultTableCellRenderer renderer;
    public Columna(JTable tabla){               
        centrarTexto(tabla);
    }
    public final void centrarTexto(JTable tabla){
        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(byte i=0;i<tabla.getColumnCount();i++){
            tabla.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
    public void textoAIzquierda(JTable tabla, int columna){
        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        tabla.getColumnModel().getColumn(columna).setCellRenderer(renderer);
    }
    public void anchoColumna(JTable tabla, String columna, int predefinido, int max){
        tabla.getColumn(columna).setPreferredWidth(predefinido);
        tabla.getColumn(columna).setMaxWidth(max);
    }
}