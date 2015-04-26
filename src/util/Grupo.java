/*
 */

package util;

import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class Grupo extends ButtonGroup{
    Enumeration<AbstractButton> enu = new Enumeration<AbstractButton>() {
        @Override
        public boolean hasMoreElements() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        @Override
        public AbstractButton nextElement() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };
    public String itemSeleccionado(){
        JRadioButton rButton;
        String labelButton = "";
        enu = this.getElements();
        while(enu.hasMoreElements()){
            rButton = (JRadioButton) enu.nextElement();
            if(rButton.isSelected()){
                labelButton = rButton.getText();
            }
        }
        return labelButton;
    }
    public String nombreItemSeleccionado(){
        JRadioButton rButton;
        String labelButton = "";
        enu = this.getElements();
        while(enu.hasMoreElements()){
            rButton = (JRadioButton) enu.nextElement();
            if(rButton.isSelected()){
                labelButton = rButton.getName();
            }
        }
        return labelButton;
    }
}