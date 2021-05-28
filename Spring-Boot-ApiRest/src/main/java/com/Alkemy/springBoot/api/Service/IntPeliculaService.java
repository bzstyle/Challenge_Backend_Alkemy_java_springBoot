package com.Alkemy.springBoot.api.Service;

import java.util.List;

import com.Alkemy.springBoot.api.Entity.Pelicula;

public interface IntPeliculaService {
	
	public List<Pelicula> findAll();
	
	public Pelicula findById(Long id);
	
	public List<Pelicula> buscarPorTitulo(String name) throws Exception;
	
	//public List<Pelicula> buscarPorGenero(String genre) throws Exception;
	
	public List<Pelicula> buscarPorFechaDeCreacion(String fecha) throws Exception;
	
	public Pelicula save(Pelicula pelicula);
	
	public void delete(Long id);

}
