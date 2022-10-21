package com.itgt.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_ingreso")
public class DetalleIngreso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "cantidad")
    private int cantidad;
    
    @Column(name = "precio_compra")
    private float precio_compra;
    
    @Column(name = "precio_venta")
    private float precio_venta;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_ingreso")
    private Ingreso ingreso;
    
    @ManyToOne
    @JoinColumn(name="id_articulo")
    private Articulo articulo;

    public DetalleIngreso() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(float precio_compra) {
        this.precio_compra = precio_compra;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public Ingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    
    
}
