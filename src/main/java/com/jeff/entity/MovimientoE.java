package com.jeff.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "movimiento")
public class MovimientoE {
	
	    @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private Long idmovimiento;

	    @Column(name="fechamovimiento")
	    @Temporal(TemporalType.DATE)
	    private Date fecha ;

	    private String tipoMovimiento;

	    private Double valor ;

	    private Double total ;

		public Long getIdmovimiento() {
			return idmovimiento;
		}

		public void setIdmovimiento(Long idmovimiento) {
			this.idmovimiento = idmovimiento;
		}

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public String getTipoMovimiento() {
			return tipoMovimiento;
		}

		public void setTipoMovimiento(String tipoMovimiento) {
			this.tipoMovimiento = tipoMovimiento;
		}

		public Double getValor() {
			return valor;
		}

		public void setValor(Double valor) {
			this.valor = valor;
		}

		public Double getTotal() {
			return total;
		}

		public void setTotal(Double total) {
			this.total = total;
		}
	    
	    
	    

}
