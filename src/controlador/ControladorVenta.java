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
import modelo.Venta;

/**
 *
 * @author Admin
 */
public class ControladorVenta {
    ClsConexion conexion = new ClsConexion();

    public ControladorVenta() {
    }

    
    public boolean guardar( String codigo, String fechaVenta, int unidaVendi, String nombreArticulo) {
        Venta venta = new Venta(codigo, fechaVenta, unidaVendi, nombreArticulo);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute
        ("insert into venta(codigo, fechaVenta, unidaVendi, nombreArticulo) "
                + "values('" + venta.getCodigo() + "','" +
                venta.getFechaVenta() + "','" +
                venta.getUnidaVendi() + "','" +
                venta.getNombreArticulo() + "')");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }

    public Venta buscar(String codigo) {
        
        
        Venta venta = new Venta();
        conexion.conectar();

        try {
            
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select codigo, fechaVenta, unidaVendi,"
                            + " nombreArticulo from venta where "
                            + "codigo='" + codigo + "'"));//consulta        

            if (conexion.getResultadoDB().next()) {
                venta.setCodigo(conexion.getResultadoDB().getString("codigo"));
                venta.setFechaVenta(conexion.getResultadoDB().getString("fechaVenta"));
                venta.setUnidaVendi(conexion.getResultadoDB().getInt("unidaVendi"));
                venta.setNombreArticulo(conexion.getResultadoDB().getString("nombreArticulo"));
                return venta;
            }
            conexion.desconectar();//se desconecta de la base de datos                
        } catch (SQLException ex) {            
            Logger.getLogger(ControladorVenta.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }
        return null;
    }
    
    public List<Venta> obtenerLista() {
        
        List<Venta> temp = new ArrayList<>();
        
        conexion.conectar();

        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select codigo, fechaVenta, unidaVendi,"
                            + " nombreArticulo from venta"));       

            while (conexion.getResultadoDB().next()) {
                String codigo = conexion.getResultadoDB().getString("codigo");
                String fechaVenta =conexion.getResultadoDB().getString("fechaVenta");
                int unidaVendi = conexion.getResultadoDB().getInt("unidaVendi");
                String nombreArticulo = conexion.getResultadoDB().getString("nombreArticulo");
                Venta venta = new Venta(codigo, fechaVenta, 0, nombreArticulo);
                temp.add(venta);
            }
            conexion.desconectar();//se desconecta de la base de datos                
        } catch (SQLException ex) {            
            Logger.getLogger(ControladorUsuario.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }
        return temp;
    }

    public boolean modificar( String codigo, String fechaVenta, int unidaVendi, String nombreArtículo) {
        Venta venta = new Venta(codigo, fechaVenta, unidaVendi, nombreArtículo);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute
        ("update venta set nombreArtículo='" + venta.getNombreArticulo() + 
                "',unidaVendi='" + venta.getUnidaVendi() + "'," +
                "fechaVenta="+ venta.getFechaVenta()
                + " where codigo='" + venta.getCodigo()+"'");//consulta
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
        ("delete from venta where codigo='" + codigo+"'");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }

}
