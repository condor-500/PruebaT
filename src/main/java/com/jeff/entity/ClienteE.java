package com.jeff.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;




@Entity(name = "clientes")
public class ClienteE extends PersonaE  implements Serializable{
	
	 
	    private static final long serialVersionUID = 1L;		
		
	    private String contrasenia;
	    
	    private Boolean estado;
	    	    
	    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL)
	    private Set<CuentasE> cuentas ;
	    
	    		
		public ClienteE() {
			
		}
		
		
		
		
		public ClienteE(String contrasenia, Boolean estado) {
			
			this.contrasenia = contrasenia;
			this.estado = estado;
		}




		public String getContrasenia() {
			return contrasenia;
		}
		public void setContrasenia(String contrasenia) {
			this.contrasenia = contrasenia;
		}
		public Boolean getEstado() {
			return estado;
		}
		public void setEstado(Boolean estado) {
			this.estado = estado;
		}
		public Set<CuentasE> getCuentas() {
			return cuentas;
		}
		public void setCuentas(Set<CuentasE> cuentas) {
			this.cuentas = cuentas;
		}

	    
	    
	    
}
