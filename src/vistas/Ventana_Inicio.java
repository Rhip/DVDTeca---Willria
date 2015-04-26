/*
 */

package vistas;

import controlador.Controlador;
import controlador.DetalleCon;
import controlador.PeliculaCon;
import controlador.VentaCon;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import modelos.Detalle;
import modelos.Pelicula;
import modelos.Venta;
import util.Componente;
import util.Control;
import util.Grupo;
import util.ModeloTabla;
import util.Util;

public class Ventana_Inicio extends JFrame{
    JPanel registro, opciones, consultas;
    // Panel Registro
    JLabel l1, l2, l3, l4, l5 , l6, l7, l8, l9, l10;
    TextField txtNombre, txtUnidades, txtDuracion;
    Choice cGenero, cProductora, cTipo, cEstado;
    Button btnRegistrarDVD, btnPedidos;
    //Panel Opciones    
    JLabel l11, l12, l13;
    TextField txtConsulta, txtNombreVenta;
    JRadioButton rNombre, rGenero, rProductora, rDuracion, rEstado;
    Grupo grupo = new Grupo();
    Button btnVentas, btnConsultarDVD, btnVender, btnMostrarTodo;
    JTextArea areaResumen;
    JSpinner spinner = new JSpinner();
    //Panel Consultas    
    PeliculaCon peliCon = new PeliculaCon(true);
    JTable tbDVD = new JTable(new ModeloTabla("consultarDVD", peliCon.numeroFilas()));
    JScrollPane scroll = new JScrollPane(tbDVD);
    //Otros
    Control control = new util.Control();
    Controlador controla = new Controlador();
    VentaCon vCon = new VentaCon();
    DefaultTableModel modelo;
    public static String[][] matrizDatos;
    Calendar calendario = GregorianCalendar.getInstance();
    Timer timy = new Timer();
    TimerTask tarea;
    JPopupMenu popup = new JPopupMenu();    
    String hora_actual, nVentas, nPeliculas, nPendientes;
    byte hora, minuto, segundo;
    public Ventana_Inicio(){
        super("DVD-Teca Willria");        
        setSize(795, 510);
        setResizable(false);
        setLocationRelativeTo(null);
        icono("src/recursos/icono.png");
        hora = (byte) calendario.get(Calendar.HOUR);
        minuto = (byte) calendario.get(Calendar.MINUTE);
        segundo = (byte) calendario.get(Calendar.SECOND);
        //setResizable(false);
        setLayout(new GridLayout(3, 1));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //Panel registro
        registro = Componente.crearJPanel(registro, Color.yellow, new GridLayout(4, 4));
        l1 = Componente.crearJLabel(l1, "Película:", registro);
        txtNombre = new TextField(20);
        JPanel pa = new JPanel();
        pa.setBackground(null);
        pa.add(txtNombre);
        registro.add(pa);
        l2 = Componente.crearJLabel(l2, "Unidades:", registro);
        txtUnidades = new TextField(15);
        JPanel aa = new JPanel();
        aa.setBackground(null);
        aa.add(txtUnidades);
        registro.add(aa);
        l3 = Componente.crearJLabel(l3, "Duración:", registro);
        txtDuracion = new TextField(15);
        JPanel sa = new JPanel();
        sa.setBackground(null);
        sa.add(txtDuracion);
        sa.add(new JLabel("min"));
        registro.add(sa);
        l4 = Componente.crearJLabel(l4, "Género:", registro);
        JPanel da = new JPanel();
        da.setBackground(null);
        registro.add(da);
        cGenero = Componente.crearChoice(cGenero, da);
        controla.llenarComboBox(cGenero, "Genero");
        l5 = Componente.crearJLabel(l5, "Productora:", registro);
        JPanel ea = new JPanel();
        ea.setBackground(null);
        registro.add(ea);
        cProductora = Componente.crearChoice(cProductora, ea);
        controla.llenarComboBox(cProductora, "Productora");
        l6 = Componente.crearJLabel(l6, "Tipo:", registro);
        JPanel ta = new JPanel();
        ta.setBackground(null);
        registro.add(ta);
        cTipo = Componente.crearChoice(cTipo, ta);
        controla.llenarComboBox(cTipo, "Tipo");
        l7 = Componente.crearJLabel(l7, "Estado:", registro);
        JPanel ua = new JPanel();
        ua.setBackground(null);
        registro.add(ua);
        cEstado = Componente.crearChoice(cEstado, ua);
        controla.llenarComboBox(cEstado, "Estado");
        btnRegistrarDVD = Componente.crearButton(btnRegistrarDVD, "Registrar", registro);
        btnPedidos = Componente.crearButton(btnPedidos, "Pedidos", registro);
        //Panel Opciones
        opciones = Componente.crearJPanel(opciones, Color.orange, new GridLayout(1, 3));
        JPanel p1 = new JPanel();
        p1 = Componente.crearJPanel(p1, null, new FlowLayout(), opciones);
        p1.setBorder(new LineBorder(Color.black));
        l11 = Componente.crearJLabel(l11, "Resumen:", p1);
        areaResumen = Componente.crearJTextArea(areaResumen, 6, 18, p1);
        btnVentas = Componente.crearButton(btnVentas, "Ventas", p1);
        initResumen();
        JPanel p2 = new JPanel();
        p2 = Componente.crearJPanel(p2, null, new FlowLayout(), opciones);
        rNombre = Componente.crearJRadioButton(rNombre, "Nombre", p2);
        rGenero = Componente.crearJRadioButton(rGenero, "Género", p2);
        rProductora = Componente.crearJRadioButton(rProductora, "Productora", p2);
        rDuracion = Componente.crearJRadioButton(rDuracion, "Duración", p2);
        rEstado = Componente.crearJRadioButton(rEstado, "Estado", p2);
        initGrupo();
        txtConsulta = Componente.crearTextField(txtConsulta, 27, p2);
        btnConsultarDVD = Componente.crearButton(btnConsultarDVD, "Ver", p2);
        btnMostrarTodo = Componente.crearButton(btnMostrarTodo, "Todo", p2);
        JPanel p3 = new JPanel();
        p3 = Componente.crearJPanel(p3, null, new FlowLayout(), opciones);
        p3.setBorder(new LineBorder(Color.black));        
        l12 = Componente.crearJLabel(l12, "Nombre:   ", p3);
        txtNombreVenta = Componente.crearTextField(txtNombreVenta, 30, p3);
        //txtNombreVenta.setEditable(false);
        l13 = Componente.crearJLabel(l12, "Unidades:  ", p3);
        spinner.setSize(10, 10);
        p3.add(spinner);
        p3.add(new JLabel("                "));
        btnVender = Componente.crearButton(btnVender, "Vender", p3);
        //Panel Consulta
        consultas = Componente.crearJPanel(consultas, Color.red, new BorderLayout());
        initTabla();
        modelo = (DefaultTableModel) tbDVD.getModel();        
        consultas.add(scroll);
        //agrego los paneles
        this.add(registro);
        this.add(opciones);
        this.add(consultas);
        this.setVisible(true);
        btnRegistrarDVD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(registrarPelicula()==false) {
                    Util.mensaje(Controlador.mensajeError, "Exito", 
                                 JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Util.mensaje("Registro Exitoso", "Exito", 
                             JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();               
                modelo.addRow(nuevaFila());
                matrizDatos = control.getArrayDatos("consultarDVD");
                nPeliculas = "Películas: "+peliCon.numeroFilas();
            }
        });
        btnPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        btnConsultarDVD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                       
                String seleccionado = grupo.nombreItemSeleccionado();
                //System.out.println(seleccionado);
                String dato = txtConsulta.getText();
                String[] datos = peliCon.consultarPelicula(seleccionado, dato);
                eliminarFilas();
                for(byte i=0;i<datos.length;i++){                    
                    modelo.addRow(Util.rowToArray(datos[i]));
                }          
            }
        });
        btnMostrarTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tbDVD.getRowCount()>0) { eliminarFilas(); }
                modelo.setRowCount(peliCon.numeroFilas());
                llenarTabla(tbDVD);
            }
        });
        tbDVD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {                
                if(e.getButton()!=3){
                    popup.setVisible(false);
                    return;
                }
                initPopup();
                popup.setLocation(e.getLocationOnScreen());
                popup.setVisible(true);
            }
        });
        btnVender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pelicula pelicula = new Pelicula();
                short id = pelicula.getPelicula_id();
                byte cant = Byte.parseByte(spinner.getValue().toString());                
                if(registrarVenta()==false) {
                    Util.mensaje(Controlador.mensajeError, "Error", 
                                 JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Util.mensaje("Venta Exitosa!", "Exito",
                             JOptionPane.INFORMATION_MESSAGE);
                peliCon.actualizarCantidad(id, cant);
                llenarTabla(tbDVD);
                nVentas="Ventas: "+vCon.numeroVentas();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                //int salir = Util.confirmacion("Desea Salir?", "Aviso", 
                //      JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                //if(salir==1) { return; }
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });
    }
    ///// Metodos
    public final void initTabla(){        
        tbDVD.setAutoResizeMode(1);
        llenarTabla(tbDVD);
        util.Columna col = new util.Columna(tbDVD);
        col.textoAIzquierda(tbDVD, 0);
        col.anchoColumna(tbDVD, tbDVD.getColumnName(0), 25, 25);
        col.anchoColumna(tbDVD, tbDVD.getColumnName(2), 118, 118);
        col.anchoColumna(tbDVD, tbDVD.getColumnName(3), 110, 110);
        col.anchoColumna(tbDVD, tbDVD.getColumnName(4), 65, 65);
        col.anchoColumna(tbDVD, tbDVD.getColumnName(5), 25, 25);
        col.anchoColumna(tbDVD, tbDVD.getColumnName(6), 63, 63);
        col.anchoColumna(tbDVD, tbDVD.getColumnName(7), 73, 73);
    }
    public void llenarTabla(JTable tabla){
        matrizDatos = control.getArrayDatos("consultarDVD");
        for(int i=0;i<peliCon.numeroFilas();i++){
            for(int j=0;j<control.getCols();j++){
                tabla.setValueAt(matrizDatos[i][j], i, j);
            }
        }
    }    
    public final void initGrupo(){       
       grupo.add(rNombre);
       grupo.add(rEstado);
       grupo.add(rDuracion);
       grupo.add(rGenero);
       grupo.add(rProductora);
       rNombre.setName("Nombre");
       rEstado.setName("Estado");
       rDuracion.setName("Duracion");
       rGenero.setName("Genero");
       rProductora.setName("Productora");
       rNombre.setSelected(true);
    }
    public final void initResumen(){        
        nPeliculas = "Películas: "+peliCon.numeroFilas();
        nVentas = "Ventas: "+vCon.numeroVentas();
        timy = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                getReloj();
                areaResumen.setText(nPeliculas+"\n"+nVentas+
                        "\nFecha: "+getFechaActual()+
                        "\nHora: "+hora_actual);
            }
        };
        timy.scheduleAtFixedRate(tarea, 0, 1000);
    }
    public String getFechaActual(){
        String fechaActual;
        byte dia = (byte)calendario.get(Calendar.DAY_OF_MONTH);
        byte mes = (byte)(calendario.get(Calendar.MONTH)+1);
        short año = (short)calendario.get(Calendar.YEAR);
        fechaActual = ((dia<10)? "0":"")+dia+"/"+((mes<10)? "0":"")+mes+"/"+año;
        return fechaActual;
    }
    public String getFechaActualBD(){
        String fechaActual;
        byte dia = (byte)calendario.get(Calendar.DAY_OF_MONTH);
        byte mes = (byte)(calendario.get(Calendar.MONTH)+1);
        short año = (short)calendario.get(Calendar.YEAR);
        fechaActual = año+"/"+((mes<10)? "0":"")+mes+"/"+((dia<10)? "0":"")+dia;
        return fechaActual;
    }
    public void getReloj(){
        hora_actual = ((hora<10) ? "0":"")+hora+":"+
                      ((minuto<10) ? "0":"")+minuto+":"+
                      ((segundo<10) ? "0":"")+segundo;
        segundo++;
        if(segundo==60) { 
            segundo = 0;
            minuto++;
        }
        if(minuto==60){
            minuto=0;
            hora++;
        }
        if(hora==24){
            hora=0;
        }
    }
    public final void initPopup(){
        popup.removeAll();
        JMenuItem itAñadirVenta = new JMenuItem("Añadir a Venta"); //puedes añadir un icono        
        JMenuItem itAñadirCant = new JMenuItem("Añadir Cantidad");
        popup.add(itAñadirVenta);
        popup.add(new JSeparator());
        popup.add(itAñadirCant);
        itAñadirVenta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                short selectedRow = (short) tbDVD.getSelectedRow();
                Pelicula.pelicula_id = (short)peliCon.
                                        getIdPeliculaSeleccionada(selectedRow);
                txtNombreVenta.setText(peliCon.
                                    getNombrePeliculaSeleccionada(selectedRow));
                popup.setVisible(false);
            }
        });
        itAñadirCant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                peliCon.incrementarUnidades(peliCon.
                        getIdPeliculaSeleccionada((short)tbDVD.getSelectedRow()));
                popup.setVisible(false);
                matrizDatos = control.getArrayDatos("consultarDVD");               
                eliminarFilas();
                modelo.setRowCount(peliCon.numeroFilas());
                llenarTabla(tbDVD);
            }
        });
    }            
    public final void icono(String url){
        ImageIcon image = new ImageIcon(url);
        setIconImage(image.getImage());
    }
    public void limpiarCampos(){
        txtNombre.setText("");
        txtDuracion.setText("");
        txtUnidades.setText("");
        cGenero.select(0);
        cProductora.select(0);
        cTipo.select(0);
    }
    public boolean registrarPelicula(){
        peliCon = new PeliculaCon();
        boolean resultado = false;
        if(verificarRegPelicula()==false) { return resultado; }
        setValoresPelicula();
        resultado = peliCon.insertar();
        return resultado;
    }
    public boolean verificarRegPelicula(){
        try{
            if(txtNombre.getText().isEmpty())
            { throw new Exception("Ponle un nombre\na la Película"); }
            else if(txtUnidades.getText().isEmpty())
            { throw new Exception("Ingresa la cantidad de\nunidades que tienes"); }
            else if(txtDuracion.getText().isEmpty())
            { throw new Exception("Especifica la duración\nde la película"); }
            else if(peliCon.existePelicula(txtNombre.getText()))
            { throw new Exception("Ya existe esa película,\ningresa otra"); }
            else if(Util.hayLetras(txtUnidades.getText()))
            { throw new Exception("Ingrese las unidades\nen numeros"); }
            else if(Util.hayLetras(txtDuracion.getText()))
            { throw new Exception("La duración de la\npelícula debe ser"+
                                  "\nespecificada en minutos"); }
        }catch(Exception ex){
            Controlador.mensajeError = ex.getMessage();
            return false;
        }
        return true;
    }
    public void setValoresPelicula(){
        short ultimaFila = (short)peliCon.numeroFilas();
        Pelicula.pelicula_id = (short)(ultimaFila+1);
        Pelicula.nombre = txtNombre.getText();
        Pelicula.genero_id = (byte)(cGenero.getSelectedIndex()+1);
        Pelicula.productora_id = (byte)(cProductora.getSelectedIndex()+1);
        Pelicula.duracion = txtDuracion.getText();
        Pelicula.unidades = (short)Integer.parseInt(txtUnidades.getText());
        Pelicula.tipo_id = (byte)(cTipo.getSelectedIndex()+1);
        Pelicula.estado_id = (byte)(cEstado.getSelectedIndex()+1);
    }
    public boolean registrarVenta(){
        DetalleCon dCon = new DetalleCon();
        boolean resultado = false;
        if(verificarVenderPelicula()==false) { return resultado; }
        setValoresVenta();
        if(vCon.insertar()==false) { return resultado; }
        resultado = dCon.insertar();
        return resultado;
    }
    public boolean verificarVenderPelicula(){
        try{
            if(txtNombreVenta.getText().isEmpty())
            { throw new Exception("El campo de venta\nestá vacío"); }
            else if(Byte.parseByte(spinner.getValue().toString())==0)
            { throw new Exception("Agrega una cantidad \nde Peliculas"); }
            else if(Byte.parseByte(spinner.getValue().toString())<0)
            { throw new Exception("La cantidad de peliculas\n"+
                                  "no puede ser negativa"); }
            else if(Byte.parseByte(spinner.getValue().toString())>peliCon
                            .getUnidadesPelicula(peliCon.
                                    getIdPeliculaPorNombre(txtNombreVenta.
                                            getText())))
            { throw new Exception("No hay tantas existencias de\n"+
                                  "la película ingresada"); }
        }catch(Exception ex){
            Controlador.mensajeError = ex.getMessage();
            return false;
        }
        return true;
    }
    public void setValoresVenta(){
        short id = peliCon.getIdPeliculaSeleccionada((short)tbDVD.getSelectedRow());
        Detalle.pelicula_id = id;
        Venta.venta_id = (short) (vCon.getUltimoId()+1);
        Detalle.fecha = getFechaActualBD();
        Detalle.unidades = (byte) Integer.parseInt(spinner.getValue().toString());
        Detalle.precio = peliCon.getPrecio(peliCon.getTipo_id(id));
        Venta.ganancia_total = Detalle.unidades*Detalle.precio;
    }
    public String[] nuevaFila(){
        Pelicula pel = new Pelicula();
        return new String[]{pel.getPelicula_id()+"",
                            pel.getNombre()+"",
                            controla.getInfo("Genero", pel.getGenero_id())+"",
                            controla.getInfo("Productora", pel.getProductora_id())+"",
                            pel.getDuracion()+" min",
                            pel.getUnidades()+"",
                            controla.getInfo("Tipo", pel.getTipo_id())+"",
                            controla.getInfo("Estado", pel.getEstado_id())+""};
    }
    public void eliminarFilas(){        
        while(tbDVD.getRowCount()!=0){
            modelo.removeRow(0);
        }
    }
}