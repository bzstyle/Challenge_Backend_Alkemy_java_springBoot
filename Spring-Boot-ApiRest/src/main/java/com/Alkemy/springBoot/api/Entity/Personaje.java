package com.Alkemy.springBoot.api.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personajes")
public class Personaje implements Serializable {
	

	 
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String imagen;
	private String nombre;
	private String edad;
	private String peso;
	private String historia;
	
	@Column(name="peliculas_asociadas")
	private String peliculas;
	
	
	  //@OneToMany
	  //@JoinColumn(name = "peliculas_asociadas")
	  //List<Pelicula> listPelicula;
	
	
	
	public Personaje() {
		
	}


	public Personaje(Long id, String imagen, String nombre, String edad, String peso, String historia, String peliculas) {
		super();
		this.id = id;
		this.imagen = imagen;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
		this.peliculas = peliculas;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEdad() {
		return edad;
	}


	public void setEdad(String edad) {
		this.edad = edad;
	}


	public String getPeso() {
		return peso;
	}


	public void setPeso(String peso) {
		this.peso = peso;
	}


	public String getHistoria() {
		return historia;
	}


	public void setHistoria(String historia) {
		this.historia = historia;
	}


	public String getPeliculas() {
		return peliculas;
	}


	public void setPeliculas(String peliculas) {
		this.peliculas = peliculas;
	}


	
}
