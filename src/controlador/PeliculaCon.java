/*
 */

package controlador;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.Pelicula;
import util.Control;
import vistas.Ventana_Inicio;

public class PeliculaCon implements Gestionar{
    Control control = new Control();    
    public PeliculaCon() {}
    public PeliculaCon(boolean c){
        Conexion con = new Conexion();
    }
    
    @Override
    public boolean insertar(){
        Pelicula pel = new Pelicula();
        String sql = "insert into Pelicula values ('"+(pel.getPelicula_id())+"','"
                                                    +pel.getNombre()+"', '"
                                                    +pel.getGenero_id()+"', '"
                                                    +pel.getProductora_id()+"', '"
                                                    +pel.getDuracion()+" min', '"
                                                    +pel.getUnidades()+"', '"
                                                    +pel.getTipo_id()+"', '"
                                                    +pel.getEstado_id()+"')";
        try{
            Statement sentencia = conexion.Conexion.conexion.createStatement();
            sentencia.executeUpdate(sql);
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
            return false;
        }
        return true;
    }
    public boolean actualizar(){
        Pelicula pel = new Pelicula();
        String sql = "";
        try{
            Statement sentencia = conexion.Conexion.conexion.createStatement();
            sentencia.executeUpdate(sql);
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
            return false;
        }
        return true;
    }    
    public boolean existePelicula(String pelicula){
        String[][] datos = Ventana_Inicio.matrizDatos;
        for(byte i=0;i<numeroFilas();i++){
            if(datos[i][1].equalsIgnoreCase(pelicula)){ return true; }
        }
        return false;
    }
    public int numeroFilas(){
        int filas = 0;
        try{
            Statement sentencia = Conexion.conexion.createStatement();
            ResultSet set = sentencia.executeQuery("select pelicula_id from Pelicula");
            while(set.next()){                
                filas++;
            }
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
        }
        return filas;
    }
    public String[] consultarPelicula(String columna, String dato){
        String consulta = "select id, "+columna+" from consultarDVD";
        List lista = new ArrayList();
        try{
            Statement sentencia = Conexion.conexion.createStatement();
            ResultSet res = sentencia.executeQuery(consulta);
            //System.err.print("esta3");
            while(res.next()){
                //System.out.print("hola1");
                String resultado  = res.getString(2);
                if(dato.equalsIgnoreCase(resultado)){
                    //System.err.println(res.getByte(1));
                    lista = agregarFila(lista, res.getByte(1));                    
                }
            }
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
        }        
        String[] a = util.Util.aUnArreglo(lista);
        return a;
    }
    private List agregarFila(List lista, byte id){
        // System.err.println(id);
        String consulta = "select * from consultarDVD where Id = "+id;
        String fila = "";
        byte cols = 8;
        try{
            Statement sentencia = Conexion.conexion.createStatement();
            ResultSet set = sentencia.executeQuery(consulta);
            if(set.next()){ 
                for(byte i=0;i<cols;i++){
                    fila += set.getString(i+1)+",";
                    //fila += set.getString(i+1)+((i!=7) ? ",":"");
                }
            }
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
        }
        lista.add(fila);
        return lista;
    }
    public void actualizarCantidad(short id, byte cantidad){
        PeliculaCon pCon = new PeliculaCon();
        String sql = "update Pelicula set unidades = "+(pCon.getUnidadesPelicula(id)-cantidad)+
                     " where pelicula_id = "+id;
        try{
            Statement sentencia = Conexion.conexion.createStatement();
            sentencia.executeUpdate(sql);
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
        }
    }
    public String getNombrePeliculaSeleccionada(int filaSeleccionada){        
        return Ventana_Inicio.matrizDatos[filaSeleccionada][1];
    }
    public double getPrecio(byte opcion){
        if (opcion == 1) { return 1.5; }
        return 2.5;
    }
    public byte getUnidadesPelicula(short id){
        String[][] datos = Ventana_Inicio.matrizDatos;
        byte unidades = 0;
        for(byte i = 0;i<numeroFilas();i++){
            if(id == Short.parseShort(datos[i][0])){
                unidades = Byte.parseByte(datos[i][5]);
                break;
            }
        }
        return unidades;
    }
    public short getIdPeliculaSeleccionada(short filaSeleccionada){
        String[][] datos = Ventana_Inicio.matrizDatos;
        return Short.parseShort(datos[filaSeleccionada][0]);
    }
    public short getIdPeliculaPorNombre(String pelicula){
        String[][] datos = Ventana_Inicio.matrizDatos;
        short id = 0;
        for(byte i=0;i<numeroFilas();i++){
            if(pelicula.equalsIgnoreCase(datos[i][1])){
                id = Short.parseShort(datos[i][0]);
            }
        }
        return id;
    }
    public byte getNumeroPendientes(){
        String consulta = "select * from consultarDVD where estado = pendiente";
        byte pendientes = 0;
        try{
            Statement sentencia = Conexion.conexion.createStatement();
            ResultSet set = sentencia.executeQuery(consulta);
            while(set.next()){
                pendientes++;
            }
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
        }
        return pendientes;
    }
    public byte getTipo_id(short id){
        byte estado_id = 0;
        String consulta = "select Tipo_id from pelicula where id = "+id;
        try{
            Statement sentencia = Conexion.conexion.createStatement();
            ResultSet set = sentencia.executeQuery(consulta);
            while(set.next()){
                estado_id = set.getByte(1);
            }
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
        }
        return estado_id;
    }
    public void incrementarUnidades(short id){
        String sql = "update Pelicula set unidades = "+(getUnidadesPelicula(id)+1)+
                     " where pelicula_id = "+id;
        try{
            Statement sentencia = Conexion.conexion.createStatement();
            sentencia.executeUpdate(sql);
        }catch(SQLException ex){
            Controlador.mensajeError = ex.getMessage();
        }
    }
}