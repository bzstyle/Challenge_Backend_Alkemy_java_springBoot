package com.Alkemy.springBoot.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="generos")
public class Genero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String imagen;
	private String nombre;
	
	@Column(name ="peliculas_asociadas")
	private String peliculas;
	
	//@OneToMany
	//@JoinColumn(name="peliculas_asociadas")
	//List<Pelicula> listPelicula;
	
	
	public Genero() {
		
	}

	public Genero(int id, String imagen, String nombre, String peliculas) {
		super();
		this.id = id;
		this.imagen = imagen;
		this.nombre = nombre;
		this.peliculas = peliculas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(String peliculas) {
		this.peliculas = peliculas;
	}


	

}
