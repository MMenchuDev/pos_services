package com.itgt.pos.model;

import javax.persistence.*;

@Entity
@Table(name = "tt_usuario")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "usuario")
  private String usuario;

  @Column(name = "password")
  private String password;

  public Usuario() {

  }

  public Usuario(String nombre, String usuario, String password) {
    this.nombre = nombre;
    this.usuario = usuario;
    this.password = password;
  }

  public long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Usuario [id=" + id + ", nombre=" + nombre + ", desc=" + usuario + ", password=" + password + "]";
  }
}
