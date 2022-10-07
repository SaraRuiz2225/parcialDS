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
import modelo.Articulo;
import modelo.ClsConexion;

/**
 *
 * @author Admin
 */
public class ControladorArticulo {
    ClsConexion conexion = new ClsConexion();

    public ControladorArticulo() {
    }

    
    public boolean guardar( String codigo, String nombre, double precio, int cantidad, 
            String descripcion, String categoria) {
        Articulo articulo = new Articulo(codigo, nombre, precio, cantidad, descripcion, categoria);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute
        ("insert into articulo(codigo, nombre, precio, cantidad, descripcion, categoria) "
                + "values('" + articulo.getCodigo() + "','" +
                articulo.getNombre() + "','" +
                articulo.getPrecio() + "','" +
                articulo.getCantidad()  + "','" + 
                articulo.getDescripcion()  + "','" +
                articulo.getCategoria() + "')");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }

    public Articulo buscar(String codigo) {
        
        
        Articulo articulo = new Articulo();
        conexion.conectar();

        try {
            
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select codigo, nombre,precio,cantidad,descripcion,"
                            + "categoria from articulo where "
                            + "codigo='" + codigo + "'"));//consulta        

            if (conexion.getResultadoDB().next()) {
                articulo.setCodigo(conexion.getResultadoDB().getString("codigo"));
                articulo.setNombre(conexion.getResultadoDB().getString("nombre"));
                articulo.setPrecio(conexion.getResultadoDB().getDouble("precio"));
                articulo.setCantidad(conexion.getResultadoDB().getInt("cantidad"));
                articulo.setDescripcion(conexion.getResultadoDB().getString("descripcion"));
                articulo.setCategoria(conexion.getResultadoDB().getString("categoria"));
                return articulo;
            }
            conexion.desconectar();//se desconecta de la base de datos                
        } catch (SQLException ex) {            
            Logger.getLogger(ControladorArticulo.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }
        return null;
    }
    
    public List<Articulo> obtenerLista() {
        
        List<Articulo> temp = new ArrayList<>();
        
        conexion.conectar();

        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select codigo, nombre, precio,cantidad,descripcion,"
                            + "categoria from articulo"));       

            while (conexion.getResultadoDB().next()) {
                String codigo = conexion.getResultadoDB().getString("codigo");
                String nombre =conexion.getResultadoDB().getString("nombre");
                double precio = conexion.getResultadoDB().getDouble("precio");
                int cantidad = conexion.getResultadoDB().getInt("cantidad");
                String descripcion = conexion.getResultadoDB().getString("descripcion");
                String categoria = conexion.getResultadoDB().getString("categoria");
                Articulo articulo = new Articulo(codigo, nombre, precio, cantidad, descripcion, categoria);
                temp.add(articulo);
            }
            conexion.desconectar();//se desconecta de la base de datos                
        } catch (SQLException ex) {            
            Logger.getLogger(ControladorArticulo.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }
        return temp;
    }
    public boolean modificar( String codigo, String nombre, double precio, int cantidad, 
            String descripcion, String categoria) {
        Articulo articulo = new Articulo(codigo, nombre, precio, cantidad, descripcion, categoria);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute
        ("update articulo set nombre='" + articulo.getNombre() + 
                "',precio='" + articulo.getPrecio() + "'," +
                "cantidad='" + articulo.getCantidad() + "'," +
                "descripcion='" + articulo.getDescripcion() + "'," +
                "categoria='"+ articulo.getCategoria()
                + "' where codigo='" + articulo.getCodigo()+"'");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }

    public boolean eliminar(String codigo) {

        conexion.conectar();

        try {
            conexion.getSentenciaSQL().execute
        ("delete from articulo where codigo='" + codigo+"'");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }

}
