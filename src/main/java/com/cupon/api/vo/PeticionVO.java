package com.cupon.api.vo;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="peticion")
public class PeticionVO {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date  fecha;
	private double valorCupon;
	
    @OneToMany(mappedBy = "peticion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductoVO> productoVO;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getValorCupon() {
		return valorCupon;
	}
	public void setValorCupon(double valorCupon) {
		this.valorCupon = valorCupon;
	}
	public List<ProductoVO> getProductoVO() {
		return productoVO;
	}
	public void setProductoVO(List<ProductoVO> productoVO) {
		this.productoVO = productoVO;
	}
	
}
