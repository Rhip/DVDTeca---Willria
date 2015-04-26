/*
 */

package util;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Util {
    public static void mensaje(String mensaje, String titulo, String urlIcono, int tipoSms){
        JOptionPane.showMessageDialog(null, mensaje, titulo, tipoSms, 
                new ImageIcon(urlIcono));
    }
    public static void mensaje(String mensaje, String titulo, int tipoSms){
        JOptionPane.showMessageDialog(null, mensaje, titulo, tipoSms);
    }
    public static int confirmacion(String mensaje, String titulo, String urlIcono){
        int op = JOptionPane.showConfirmDialog(null, mensaje, titulo, 
                JOptionPane.YES_NO_OPTION, 1, new ImageIcon(urlIcono));
        return op;
    }
    public static int confirmacion(String mensaje, String titulo, int opciones, int tipoSms){
        int op = JOptionPane.showConfirmDialog(null, mensaje, titulo, opciones, tipoSms);
        return op;
    }    
    public static boolean hayNumeros(String texto){
        char[] car = texto.toCharArray();
        for(int i=0;i<car.length;i++){
            if(Character.isDigit(car[i])) { return true; }
        }return false;        
    }
    public static boolean hayLetras(String texto){
        char[] car = texto.toCharArray();
        for(int i=0;i<car.length;i++){
            if(Character.isLetter(car[i])) { return true; }
        }return false;
    }
    public static String[] aUnArreglo(List list){
        byte tamaño = (byte)list.size();
        String[] array = new String[tamaño];
        for(byte i=0;i<list.size();i++){
            array[i] = list.get(i).toString();
        }
        return array;
    }
    public static String[] rowToArray(String texto){
        String[] salida = new String[contarComas(texto)+1];
        separar(salida, texto, (byte)0, (byte)0, (byte)0);        
        return salida;
    }
    private static void separar(String[] salida, String texto, 
        byte i, byte posicion, byte cont){
        if(i>=texto.length()) { return; }
        if(texto.charAt(i)==','){
            salida[cont] = texto.substring(posicion, i);
            posicion = (byte)(i+1);
            cont++;
        }
        separar(salida, texto, (byte)(i+1), posicion, cont);
    }
    private static byte contarComas(String texto){
        byte n = 0;
        for(byte i=0;i<texto.length();i++){
            if(texto.charAt(i)==','){ n++; }
        }
        return n;
    }
}