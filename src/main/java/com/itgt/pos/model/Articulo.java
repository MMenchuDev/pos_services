package com.itgt.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "articulo")
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "codigo")
    private String codigo;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "existencia")
    private int existencia;
    
    @Column(name = "precio_venta")
    private float precio_venta; 
    
    @Column(name = "precio_compra")
    private float precio_compra; 
        
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "imagen")
    private String imagen;
    
    @Column(name = "estado")
    private int estado;
    
    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn(name="id_presenacion")
    private Presentacion presentacion;    
    
    @Column(name = "marca")
    private String marca;
    
    @Column(name = "stokminimo")
    private int stokminimo;

    
    @Column(name = "modelo")
    private String modelo;
    
    @Column(name = "monto_descuento")
    private float montoDescuento;
    
    @Column(name = "porcentaje_descuento")
    private int porcentajeDescuento;
    
    @Column(name = "tipo_descuento")
    private int tipoDescuento;
    
	public Articulo() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Presentacion getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(Presentacion presentacion) {
		this.presentacion = presentacion;
	}

	public float getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(float precio_venta) {
		this.precio_venta = precio_venta;
	}

	public float getPrecio_compra() {
		return precio_compra;
	}

	public void setPrecio_compra(float precio_compra) {
		this.precio_compra = precio_compra;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getStokminimo() {
		return stokminimo;
	}

	public void setStokminimo(int stokminimo) {
		this.stokminimo = stokminimo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public float getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(float montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public int getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(int porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public int getTipoDescuento() {
		return tipoDescuento;
	}

	public void setTipoDescuento(int tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}
	

}
