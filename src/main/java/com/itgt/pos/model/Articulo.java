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
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "imagen")
    private String imagen;
    
    @Column(name = "estado")
    private int estado;
    
    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;
}
