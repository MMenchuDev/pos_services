package com.itgt.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "menu_permiso")
public class MenuPermiso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "estado")
    private int estado;
    
    @ManyToOne
    @JoinColumn(name="id_permiso")
    @JsonBackReference
    private Permiso permiso;
    
    @ManyToOne
    @JoinColumn(name="id_menu_padre")
    private MenuPadre menu;

	public MenuPermiso() {
	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Permiso getPermiso() {
		return permiso;
	}

	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}

	public MenuPadre getMenu() {
		return menu;
	}

	public void setMenu(MenuPadre menu) {
		this.menu = menu;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}        

}
