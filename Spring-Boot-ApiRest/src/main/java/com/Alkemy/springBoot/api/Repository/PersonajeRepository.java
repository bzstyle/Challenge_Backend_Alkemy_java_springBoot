package com.Alkemy.springBoot.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alkemy.springBoot.api.Entity.Personaje;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
	
	 public List<Personaje> findByNombreContaining(String name);
	    
	 public List<Personaje> findByEdadContaining(String age);
	    
	 public List<Personaje> findByPeliculasContaining(String movies);
	 
	
}
