package com.itgt.pos.model;

import java.sql.Date;

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
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "abono")
    private float abono;
    
    @Column(name = "tipo_pago")
    private int tipopago;
    
    @Column(name = "fecha_pago")
    private Date fechapago;
    
    @Column(name = "saldo")
    private float saldo;
    
    @Column(name = "observaciones")
    private float observaciones;
    
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_egreso")
    @JsonBackReference(value="pagos")
    private Egreso egreso;


    
    
	public Pago() {
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public float getAbono() {
		return abono;
	}


	public void setAbono(float abono) {
		this.abono = abono;
	}


	public int getTipopago() {
		return tipopago;
	}


	public void setTipopago(int tipopago) {
		this.tipopago = tipopago;
	}


	public Date getFechapago() {
		return fechapago;
	}


	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}


	public float getSaldo() {
		return saldo;
	}


	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}


	public float getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(float observaciones) {
		this.observaciones = observaciones;
	}


	public Egreso getEgreso() {
		return egreso;
	}


	public void setEgreso(Egreso egreso) {
		this.egreso = egreso;
	}    
}
