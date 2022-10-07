/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Admin
 */
public class Venta {
    private String codigo;
    private String fechaVenta; 
    private int unidaVendi;
    private String nombreArticulo; 
    private double total;

    public Venta() {
    }

    
    public Venta(String codigo, String fechaVenta, int unidaVendi, String nombreArticulo) {
        this.codigo = codigo;
        this.fechaVenta = fechaVenta;
        this.unidaVendi = unidaVendi;
        this.nombreArticulo = nombreArticulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getUnidaVendi() {
        return unidaVendi;
    }

    public void setUnidaVendi(int unidaVendi) {
        this.unidaVendi = unidaVendi;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    
    public double calcularTotal(double precio, int cantidad){
       total = precio * cantidad;
       return total;
    }   
    
}
