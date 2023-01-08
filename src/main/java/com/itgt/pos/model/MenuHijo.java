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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "menu_hijo")
public class MenuHijo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "estado")
    private int estado;
    
    @Column(name = "classname")
    private String classname;
    
    @Column(name = "path")
    private String path;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_menu_padre")
    @JsonBackReference
    private MenuPadre menupadre;

	public MenuHijo() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public MenuPadre getMenupadre() {
		return menupadre;
	}

	public void setMenupadre(MenuPadre menupadre) {
		this.menupadre = menupadre;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}   
	
}
