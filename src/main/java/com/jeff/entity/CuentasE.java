package com.jeff.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "cuenta")
public class CuentasE implements Serializable {


	private static final long serialVersionUID = 1L;


	    @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private Long idcuenta;
	    
	    private String numCuenta;
	    
	    private String tipocuenta;
	    
	    private Double saldoinicial;
	    
	    private boolean estado ;
	    
	    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "id_cliente", nullable = false)
	    private ClienteE cliente;
		
	    
	    
	    
	    
	    public CuentasE() {
			
		}
	    
	    
	    
		public CuentasE(Long idcuenta, String numCuenta, String tipocuenta, Double saldoinicial, boolean estado,
				ClienteE cliente) {
		
			this.idcuenta = idcuenta;
			this.numCuenta = numCuenta;
			this.tipocuenta = tipocuenta;
			this.saldoinicial = saldoinicial;
			this.estado = estado;
			this.cliente = cliente;
		}



		public Long getIdcuenta() {
			return idcuenta;
		}
		public void setIdcuenta(Long idcuenta) {
			this.idcuenta = idcuenta;
		}
		public String getNumCuenta() {
			return numCuenta;
		}
		public void setNumCuenta(String numCuenta) {
			this.numCuenta = numCuenta;
		}
		public String getTipocuenta() {
			return tipocuenta;
		}
		public void setTipocuenta(String tipocuenta) {
			this.tipocuenta = tipocuenta;
		}
		public Double getSaldoinicial() {
			return saldoinicial;
		}
		public void setSaldoinicial(Double saldoinicial) {
			this.saldoinicial = saldoinicial;
		}
		public boolean isEstado() {
			return estado;
		}
		public void setEstado(boolean estado) {
			this.estado = estado;
		}
		public ClienteE getCliente() {
			return cliente;
		}
		public void setCliente(ClienteE cliente) {
			this.cliente = cliente;
		}
	    
	    
	
	
}
