package com.Alkemy.springBoot.api.service.impl;

import java.util.List;

import com.Alkemy.springBoot.api.service.Interface.IntPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Alkemy.springBoot.api.model.Pelicula;
import com.Alkemy.springBoot.api.repository.PeliculaRepository;

@Service
public class PeliculaService implements IntPeliculaService {
	
	@Autowired
	private PeliculaRepository peliculaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Pelicula> findAll() {		
		return peliculaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Pelicula findById(Long id) {		
		return peliculaDao.findById(id).get();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pelicula> buscarPorTitulo(String name) throws Exception {
		try {
			List<Pelicula> pelicula = peliculaDao.findByTituloContaining(name);
			return pelicula;
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
	}

//	@Override
//	@Transactional(readOnly = true)
//	public List<Pelicula> buscarPorGenero(String genre) throws Exception {
//		try {
//			List<Pelicula> pelicula = peliculaDao.findByGeneroContaining(genre);
//			return pelicula;
//		} catch (Exception e) {
//			throw new Exception(e.getMessage());
//		}
//		
//	}

	@Override
	@Transactional(readOnly = true)
	public List<Pelicula> buscarPorFechaDeCreacion(String fecha) throws Exception {
		try {
			List<Pelicula> pelicula = peliculaDao.findByFechaDeCreacionContaining(fecha);
			return pelicula;
					
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	@Transactional
	public Pelicula save(Pelicula pelicula) {
		
		return peliculaDao.save(pelicula);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		peliculaDao.deleteById(id);
		
	}

}
