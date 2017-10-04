/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Juan Benitez
 */
public class ConexionBaseDatos {
    
    protected String driver = "com.mysql.jdbc.Driver";
    protected String nombreIPServidorBD = "localhost";
    protected String url = "jdbc:mysql://";
    protected int puertoServidorBD = 3306;
    protected String UsuarioBD = "root";
    protected String passwordBD = "";
    protected String nombreBD = "ahorcado";
    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet filasConsulta;

    public ConexionBaseDatos() throws Exception {
        
        url = url+nombreIPServidorBD+":"+puertoServidorBD+"/"+nombreBD;
        this.conectar();
        
    }

    public ConexionBaseDatos(String driver, String servidor, String url, 
            String usuarioBD, String passwordBD, String nombreBD) throws Exception{
        
        this.driver = driver;
        this.nombreIPServidorBD = servidor;
        this.url = url;
        this.UsuarioBD = usuarioBD;
        this.passwordBD = passwordBD;
        this.nombreBD = nombreBD;
        this.conectar();
        
    }
    
    
    
    private void conectar() throws Exception {
        
        try{
            Class.forName(driver);
        }catch(ClassNotFoundException ex){
            throw new Exception("Error de driver"+ex.getMessage());
        }
        
        try{
            conexion = (Connection) DriverManager.getConnection(url, UsuarioBD, passwordBD);
        }catch(SQLException ex){
            throw new Exception("Error de Conexion \n Codigo:"
                        + ex.getErrorCode()+" Explicacion:"+ex.getMessage());
        }
         
    }
    
    public int actualizar(PreparedStatement sentencia) throws SQLException{
        
        try{
            int res = sentencia.executeUpdate();
            return res;
        }catch(SQLException ex){
            throw new SQLException("Error al ejecutar sentencia SQL \n Codigo:"
                        + ex.getErrorCode()+" Explicacion:"+ex.getMessage());
        }
      
    }
    
    public ResultSet consultar(PreparedStatement sentencia) throws SQLException{
        
        try{
            ResultSet filasBD = sentencia.executeQuery();
            return filasBD;
        }catch(SQLException ex){
            throw new SQLException("Error al ejecutar sentencia SQL"+ex.getMessage());
        }
    }
    
    public void desconectar(){
        try {
            conexion.close();
        }catch(SQLException ex){
            conexion = null;
        }
    }
    
    public PreparedStatement crearSentencia(String sql) throws SQLException{
        try{
                PreparedStatement sentencia = (PreparedStatement) conexion.prepareStatement(sql);
                return sentencia;
        }catch(SQLException ex){
            throw new SQLException("Error al ejecutar sentencia SQL \n Codigo:"
                        + ex.getErrorCode()+" Explicacion:"+ex.getMessage());
        }
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getNombreIPServidorBD() {
        return nombreIPServidorBD;
    }

    public void setNombreIPServidorBD(String nombreIPServidorBD) {
        this.nombreIPServidorBD = nombreIPServidorBD;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPuertoServidorBD() {
        return puertoServidorBD;
    }

    public void setPuertoServidorBD(int puertoServidorBD) {
        this.puertoServidorBD = puertoServidorBD;
    }

    public String getUsuarioBD() {
        return UsuarioBD;
    }

    public void setUsuarioBD(String UsuarioBD) {
        this.UsuarioBD = UsuarioBD;
    }

    public String getPasswordBD() {
        return passwordBD;
    }

    public void setPasswordBD(String passwordBD) {
        this.passwordBD = passwordBD;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public PreparedStatement getSentencia() {
        return sentencia;
    }

    public void setSentencia(PreparedStatement sentencia) {
        this.sentencia = sentencia;
    }

    public ResultSet getFilasConsulta() {
        return filasConsulta;
    }

    public void setFilasConsulta(ResultSet filasConsulta) {
        this.filasConsulta = filasConsulta;
    }
    
}
