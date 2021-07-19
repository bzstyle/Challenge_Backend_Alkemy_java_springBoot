package com.Alkemy.springBoot.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alkemy.springBoot.api.model.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
	
    // public List<Pelicula> findByGeneroContaining(String genero);
	 
	 public List<Pelicula> findByTituloContaining(String nombre);
	 
	 public List<Pelicula> findByFechaDeCreacionContaining(String fecha);

}
