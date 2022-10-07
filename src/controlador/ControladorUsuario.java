/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.ClsConexion;
import modelo.Usuario;

/**
 *
 * @author Admin
 */
public class ControladorUsuario {

    ClsConexion conexion = new ClsConexion();

    public ControladorUsuario() {
    }

    public boolean guardar(String nombre,
        String apellido, String cedula, String correo, String contraseña) {
        Usuario usuario = new Usuario(nombre, apellido, cedula, correo, contraseña);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute("insert into usuario(nombre,apellido,cedula,correo,contraseña) "
                    + "values('" + usuario.getNombre()+ "','"
                    + usuario.getApellido()+ "','"
                    + usuario.getCedula()+ "','"
                    + usuario.getCorreo()+ "','"
                    + usuario.getContraseña()+ "')");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }

    public Usuario buscar(String cedula) {
        
        
        Usuario usuario = new Usuario();
        conexion.conectar();

        try {
            
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select nombre,apellido,cedula,correo,"
                            + "contraseña from usuario where "
                            + "cedula='" + cedula + "'"));//consulta        

            if (conexion.getResultadoDB().next()) {
                usuario.setNombre(conexion.getResultadoDB().getString("nombre"));
                usuario.setApellido(conexion.getResultadoDB().getString("apellido"));
                
                usuario.setCedula(conexion.getResultadoDB().getString("cedula"));
                usuario.setCorreo(conexion.getResultadoDB().getString("correo"));
                usuario.setContraseña(conexion.getResultadoDB().getString("contraseña"));
                return usuario;
            }
            conexion.desconectar();//se desconecta de la base de datos                
        } catch (SQLException ex) {            
            Logger.getLogger(ControladorUsuario.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }
        return null;
    }
    
    public List<Usuario> obtenerLista() {
        
        List<Usuario> temp = new ArrayList<>();
        
        conexion.conectar();

        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select nombre, apellido, cedula, correo,"
                            + "contraseña from usuario"));       

            while (conexion.getResultadoDB().next()) {
                String nombre = conexion.getResultadoDB().getString("nombre");
                String apellido =conexion.getResultadoDB().getString("apellido");
                String cedula = conexion.getResultadoDB().getString("cedula");
                String correo = conexion.getResultadoDB().getString("correo");
                String contraseña = conexion.getResultadoDB().getString("contraseña");
                Usuario usuario = new Usuario(nombre, apellido, cedula, correo, contraseña);
                temp.add(usuario);
            }
            conexion.desconectar();//se desconecta de la base de datos                
        } catch (SQLException ex) {            
            Logger.getLogger(ControladorUsuario.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }
        return temp;
    }
    
    public boolean modificar(String nombre, String apellido, String cedula, String correo, String contraseña) {
        Usuario usuario = new Usuario(nombre, apellido, cedula, correo, contraseña);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute("update usuario set nombre='" + usuario.getNombre()
                    + "',apellido='" + usuario.getApellido() + "',"
                    + "correo='" + usuario.getCorreo() + "',"
                    + "contraseña='" + usuario.getContraseña()
                    + "' where cedula='" + usuario.getCedula() + "'");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }

    public boolean eliminar(String cedula) {

        conexion.conectar();

        try {
            conexion.getSentenciaSQL().execute("delete from usuario where cedula='" + cedula + "'");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }

    
}
