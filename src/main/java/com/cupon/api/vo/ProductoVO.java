package com.cupon.api.vo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity(name="producto")
public class ProductoVO {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String producto;
    @ManyToOne
    @JoinColumn(name = "peticion_id")
    private PeticionVO peticion;
    
    @Transient
    private double precio;
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public PeticionVO getPeticion() {
		return peticion;
	}
	public void setPeticion(PeticionVO peticionId) {
		this.peticion = peticionId;
	}

	
	
}
