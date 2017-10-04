/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Juan Benitez
 */
public class CRUD {
    
     private Usuario alguien;
    private ConexionBaseDatos baseDatos;

    public Usuario getAlguien() {
        return alguien;
    }

    public void setAlguien(Usuario alguien) {
        this.alguien = alguien;
    }

    public ConexionBaseDatos getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(ConexionBaseDatos baseDatos) {
        this.baseDatos = baseDatos;
    }
    
    
    public void agregarUsuario() throws Exception{
        
        if(alguien.getId() == null || alguien.getId().isEmpty()){
            throw new Exception("El id del usuario es necesario");
        }
        
        String sqlInsert = "INSERT INTO Usuarios "
                + "(id, password, nombre, user, p_ganados, p_perdidos)"
                + "VALUES(?,?,?,?,?,?)";
        
        try{
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlInsert);
            
            sentenciaSQL.setString(1, alguien.getId());
            sentenciaSQL.setString(2, alguien.getPassword());
            sentenciaSQL.setString(3, alguien.getNombre());
            sentenciaSQL.setString(4, alguien.getUser());
            sentenciaSQL.setInt(5, alguien.getP_ganados());
            sentenciaSQL.setInt(6, alguien.getP_perdidos());
            
            baseDatos.actualizar(sentenciaSQL);
        }catch(Exception error){
            throw new Exception("Error al agregar al usuario "+ alguien.getId()
                        + "<br/>Explicacion: "+ error.getMessage());
        }finally{
            baseDatos.desconectar();
        }
  
    }
    
    public void modificarUsuario() throws Exception{
        
       if(alguien.getId() == null || alguien.getId().isEmpty()){
            throw new Exception("El id del usuario es necesario");
        } 
       ConexionBaseDatos baseDatos = null;
       String sqlUpdate = "UPDATE usuarios "
               + "SET password=?, nombre=?, user=?, p_ganados=?, p_perdidos=? "
               + "WHERE id=? ";
       
       try{
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlUpdate);
             
            sentenciaSQL.setString(1, alguien.getPassword());
            sentenciaSQL.setString(2, alguien.getNombre());
            sentenciaSQL.setString(3, alguien.getUser());
            sentenciaSQL.setInt(4, alguien.getP_ganados());
            sentenciaSQL.setInt(5, alguien.getP_perdidos());
            sentenciaSQL.setString(6, alguien.getId());
            
            baseDatos.actualizar(sentenciaSQL);
        }catch(Exception error){
            throw new Exception("Error al actualizar al usuario "+ alguien.getId()
                        + "<br/>Explicacion: "+ error.getMessage());
        }finally{
            baseDatos.desconectar();
        }
    }
    
    public void eliminarUsuario() throws Exception{
        
        if(alguien.getId() == null || alguien.getId().isEmpty()){
            throw new Exception("El id del usuario es necesario");
        }
        
        String sqlDelete = "DELETE FROM Usuarios "
                + "WHERE id=?";
        
        try{
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlDelete);
            
            sentenciaSQL.setString(1, alguien.getId());
            baseDatos.actualizar(sentenciaSQL);
        }catch(Exception error){
            throw new Exception("Error al eliminar al usuario "+ alguien.getId()
                        + "<br/>Explicacion: "+ error.getMessage());
        }finally{
            baseDatos.desconectar();
        }
    }
    
    public static Usuario iniciarSesion(String user, String password) throws Exception{
        if(user == null || user.isEmpty() || password == null || password.isEmpty()){
            throw new Exception("El id y el password del usuario son necesarios");
        }
        
        Usuario alguien;
        ConexionBaseDatos baseDatos = null;
        
        String sqlSelect = "SELECT * FROM usuarios WHERE user=? AND password=?";
        
        try{
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            sentenciaSQL.setString(1, user);
            sentenciaSQL.setString(2, password);
            
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            if(resultado.next() == true){
                alguien = new Usuario();
                alguien.setId(resultado.getString("id"));
                alguien.setPassword(resultado.getString("password"));
                alguien.setNombre(resultado.getString("nombre"));
                alguien.setUser(resultado.getString("user"));
                alguien.setP_ganados(resultado.getInt("p_ganados"));
                alguien.setP_perdidos(resultado.getInt("p_perdidos"));
                return alguien;
            }else{
                throw new Exception("Error al consultar el usuario");
            }
            
        }catch(Exception error){
            throw new Exception(error.getMessage()+" Error el id o passwprd estan erradps");
        }finally{
            if(baseDatos != null){
                baseDatos.desconectar();
            }
        }
        
    }
    
    public static Usuario consultarUsuario(String id) throws Exception{
        if(id == null || id.isEmpty()){
            throw new Exception("El Id del usuario es necesario");
        }
        Usuario alguien; ConexionBaseDatos baseDatos = null;
        
        String sqlSelect = "SELECT * FROM Usuarios WHERE id = ?";
        
        try{
            
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            sentenciaSQL.setString(1, id);
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            
            if(resultado.next() == true){
               alguien = new Usuario();
                alguien.setId(resultado.getString("id"));
                alguien.setPassword(resultado.getString("password"));
                alguien.setNombre(resultado.getString("nombre"));
                alguien.setUser(resultado.getString("user"));
                alguien.setP_ganados(resultado.getInt("p_ganados"));
                alguien.setP_perdidos(resultado.getInt("p_perdidos"));
                return alguien;
            }else{
                throw new Exception("Error al consultar el usuario"); 
            }
         }catch(Exception error){
             throw new Exception(error.getMessage()+" Error el usuario no existe en la BD");
         }finally{
            if(baseDatos != null){
                baseDatos.desconectar();
            }
        }
    }
    
    public static Usuario[] listarTodosLosUsuarios() throws Exception{
        Usuario alguien; ConexionBaseDatos baseDatos = null;
        
        String sqlSelect = "SELECT * FROM Usuarios";
        try{
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQl = baseDatos.crearSentencia(sqlSelect);
            ResultSet resultado = baseDatos.consultar(sentenciaSQl);
            resultado.last();
            Usuario[] listado = new Usuario[resultado.getRow()];
            resultado.beforeFirst();
            while(resultado.next() == true){
                alguien = new Usuario();
                alguien.setId(resultado.getString("id"));
                alguien.setPassword(resultado.getString("password"));
                alguien.setNombre(resultado.getString("nombre"));
                alguien.setUser(resultado.getString("user"));
                alguien.setP_ganados(resultado.getInt("p_ganados"));
                alguien.setP_perdidos(resultado.getInt("p_perdidos"));
                listado[resultado.getRow()] = alguien;
            }
            if(listado.length <= 0){
                throw new Exception("Error al listar los usuarios");
            }
            return listado;        
            
        }catch(Exception error){
            throw new Exception("la bd esta vacia");
        }finally{
            if(baseDatos != null){
                baseDatos.desconectar();
            }
        }
        
    }
    
}
