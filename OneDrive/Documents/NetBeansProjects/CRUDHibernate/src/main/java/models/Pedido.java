/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author david
 */
@Entity
@Table(name="pedidos")
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="producto_id")
    private Long producto_id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="precio")
    private double precio;
    @Column(name="fecha")
    private Date fecha;
    @Column(name="estado")
    private String estado;

    public Pedido() {
    }

    public Pedido(Long id, Long producto_id, String nombre, double precio, Date fecha, String estado) {
        this.id = id;
        this.producto_id = producto_id;
        this.nombre = nombre;
        this.precio = precio;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Long producto_id) {
        this.producto_id = producto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", producto_id=" + producto_id + ", nombre=" + nombre + ", precio=" + precio + ", fecha=" + fecha + ", estado=" + estado + '}';
    }
    
    
    
}
