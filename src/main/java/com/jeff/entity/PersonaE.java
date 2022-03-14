package com.jeff.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.lang.NonNull;

@MappedSuperclass
public class PersonaE {

	
	     @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long idcliente ;

	    @NonNull
	    @Column( name = "nompre_per")
	    private String nombre ;

	    @Column( name = "genero_per")
	    private char genero ;

	    @Column( name = "edad_per")
	    private int edad ;

	    @Column( name = "identificacion_per")
	    private String identificacion;

	    @Column( name = "direccion_per")
	    private String direccion;

	    @Column( name = "telefono_per")
	    private String telefono ;

		

		public Long getIdcliente() {
			return idcliente;
		}

		public void setIdcliente(Long idcliente) {
			this.idcliente = idcliente;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public char getGenero() {
			return genero;
		}

		public void setGenero(char genero) {
			this.genero = genero;
		}

		public int getEdad() {
			return edad;
		}

		public void setEdad(int edad) {
			this.edad = edad;
		}

		public String getIdentificacion() {
			return identificacion;
		}

		public void setIdentificacion(String identificacion) {
			this.identificacion = identificacion;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
	    
	    
	
}
