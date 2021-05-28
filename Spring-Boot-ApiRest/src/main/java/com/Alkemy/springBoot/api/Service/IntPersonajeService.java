package com.Alkemy.springBoot.api.Service;

import java.util.List;

import com.Alkemy.springBoot.api.Entity.Personaje;


public interface IntPersonajeService {
	
    public List<Personaje>findAll();
    
    public Personaje findById(long id);
    
    public List<Personaje> buscarPorNombre(String name) throws Exception;
    
    public List<Personaje> buscarPorEdad(String age) throws Exception;
    
    public List<Personaje> buscarPorPeliculas(String movies) throws Exception;
	
	public Personaje save(Personaje personaje);
	
	public void delete(Long id);

	
}
