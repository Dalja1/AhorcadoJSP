/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Juan Benitez
 */
public class Usuario {
    
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getP_ganados() {
        return p_ganados;
    }

    public void setP_ganados(int p_ganados) {
        this.p_ganados = p_ganados;
    }

    public int getP_perdidos() {
        return p_perdidos;
    }

    public void setP_perdidos(int p_perdidos) {
        this.p_perdidos = p_perdidos;
    }
 private String id;
    private String password;
    private String nombre;
    private String user;
    private int p_ganados;
    private int p_perdidos;
    
    
    
}
