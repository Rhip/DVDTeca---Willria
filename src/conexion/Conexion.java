/*
 */

package conexion;

import util.Util;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private final String DEFAULT_DRIVER="com.mysql.jdbc.Driver";
    private final String SOURCE_URL="jdbc:mysql://localhost/";
    private final String DATABASE="dvdteca"+"?";
    private final String USER="user=root";
    private final String PASSWORD="password=12345";
    private final String driver=DEFAULT_DRIVER;
    public String sourceUrl=SOURCE_URL+DATABASE+USER+"&"+PASSWORD;
    public static Connection conexion;
    public static Statement statement;
    
    public Conexion(){
        try { Class.forName(this.driver);
        } catch(ClassNotFoundException e) {
            System.err.println(e);
        }
        try {
            conexion=DriverManager.getConnection(this.sourceUrl);
            System.out.print("Conectó\n");
        } catch(SQLException ex) {
            Util.mensaje(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    
}