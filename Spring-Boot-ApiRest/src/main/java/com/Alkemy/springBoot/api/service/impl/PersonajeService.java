package com.Alkemy.springBoot.api.service.impl;

import java.util.List;

import com.Alkemy.springBoot.api.service.Interface.IntPersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Alkemy.springBoot.api.model.Personaje;
import com.Alkemy.springBoot.api.repository.PersonajeRepository;

@Service
public class PersonajeService implements IntPersonajeService {
	
	@Autowired
	private PersonajeRepository personajeDao;
	
	
	// buscar todos los registros de personajes
	@Override
	@Transactional(readOnly = true)
	public List<Personaje> findAll(){		
		return (List<Personaje>) personajeDao.findAll();
	}
	
	// buscar personajes por nombres
	@Override
	@Transactional(readOnly = true)
	public List<Personaje> buscarPorNombre(String name) throws Exception {
		try {			
			List<Personaje> personaje = personajeDao.findByNombreContaining(name);
			return personaje;			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	// buscar personajes por edad
	@Override
	@Transactional(readOnly = true)
	public List<Personaje> buscarPorEdad(String age) throws Exception {
		try {
			List<Personaje> personaje = personajeDao.findByEdadContaining(age);
			return personaje;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	// buscar personajes por peliculas
	@Override
	@Transactional(readOnly = true)
	public List<Personaje> buscarPorPeliculas(String movies) throws Exception {
		try {
			List<Personaje> personaje = personajeDao.findByPeliculasContaining(movies);
			return personaje;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	

	// crear personaje
	@Override
	@Transactional
	public Personaje save(Personaje personaje) {
		
		return personajeDao.save(personaje);
	}

	// borrar personaje
	@Override
	@Transactional
	public void delete(Long id) {
		personajeDao.deleteById(id);
		
	}

	//buscar personaje por id
	@Override
	@Transactional(readOnly = true)
	public Personaje findById(long id) {
		return personajeDao.findById(id).get();
	}


	
}
