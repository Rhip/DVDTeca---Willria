/*
 */
package util;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class Componente {
    public static void crear(Frame ventana, int width, int height){
        ventana.setSize(width, height);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
    }
    public static JPanel crearJPanel(JPanel panel, Color fondo, LayoutManager layout){
        panel = new JPanel();
        panel.setBackground(fondo);
        panel.setLayout(layout);
        return panel;
    }
    public static JPanel crearJPanel(JPanel panel, Color fondo, LayoutManager layout, JPanel cont){
        panel = new JPanel();
        panel.setBackground(fondo);
        panel.setLayout(layout);
        cont.add(panel);
        return panel;
    }
    public static JLabel crearJLabel(JLabel label, String text, JPanel panel){
        label = new JLabel(text);
        
        label.setBackground(null);
        panel.add(label);
        return label;
    }
    public static TextField crearTextField(TextField campo, int tamaño, JPanel panel){
        campo = new TextField(tamaño);
        panel.add(campo);
        return campo;
    }    
    public static Button crearButton(Button boton, String text, JPanel panel){
        boton = new Button(text);
        boton.setFocusable(false);
        panel.add(boton);
        return boton;
    }
    public static JTextArea crearJTextArea(JTextArea area, int rows, int columns, JPanel panel){
        area = new JTextArea(rows, columns);
        area.setEditable(false);
        area.setAutoscrolls(false);
        area.setBorder(new LineBorder(Color.black));        
        panel.add(area);        
        return area;
    }
    public static JRadioButton crearJRadioButton(JRadioButton radio, String etiqueta, JPanel panel){
        radio = new JRadioButton();
        radio.setText(etiqueta);
        radio.setBackground(null);
        radio.setFocusPainted(false);
        panel.add(radio);
        return radio;
    }    
    public static Choice crearChoice(Choice choice, JPanel panel){
        choice = new Choice();
        panel.add(choice);
        return choice;
    }
    public static void agregarElementos(String[] elementos, Choice choice){
        int i = 0;
        while(i<elementos.length){
            choice.addItem(elementos[i]);
            i++;
        }
    }
}