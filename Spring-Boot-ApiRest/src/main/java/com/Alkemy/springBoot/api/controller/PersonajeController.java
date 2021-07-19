package com.Alkemy.springBoot.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Alkemy.springBoot.api.model.Personaje;
import com.Alkemy.springBoot.api.service.Interface.IntPersonajeService;

@RestController
@RequestMapping("/api")
public class PersonajeController {
	
	@Autowired
	private IntPersonajeService personajeService;
	
	// buscar todos los personajes
	@GetMapping("/characters")
	public List<Personaje> listarTodos(){
		return personajeService.findAll();
	}
	
	// buscar personajes por id
	@GetMapping("/id/characters/{id}")
	public Personaje buscarPorId(@PathVariable Long id) {
		return personajeService.findById(id);
	}
	
	// buscar personajes por nombre
	@GetMapping("name/characters")
	public ResponseEntity<?> buscarPorNombre(@RequestParam String name){
		try {			
			return ResponseEntity.status(HttpStatus.OK).body(personajeService.buscarPorNombre(name));			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
		
	}
	
	// buscar personajes por edad
	@GetMapping("/age/characters")
	public ResponseEntity<?> buscarPorEdad(@RequestParam String age){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(personajeService.buscarPorEdad(age));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}
	
	// buscar personajes por peliculas
	@GetMapping("/movies/characters")
	public ResponseEntity<?> buscarPorPeliculas(@RequestParam String movies){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(personajeService.buscarPorPeliculas(movies));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}
	
	// crear o cargar personaje 
	@PostMapping("/create/characters")
	//@ResponseStatus(HttpStatus.CREATED) 
	public Personaje create(@RequestBody Personaje personaje) {
		return personajeService.save(personaje);		
	}
	
	// modificar personaje
	@PutMapping("/update/characters/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED) 
	public Personaje modify(@RequestBody Personaje personaje , @PathVariable Long id) {
		Personaje personajeUpdate = personajeService.findById(id);
		
		personajeUpdate.setImagen(personaje.getImagen());
		personajeUpdate.setNombre(personaje.getNombre());
		personajeUpdate.setEdad(personaje.getEdad());
		personajeUpdate.setPeso(personaje.getPeso());
		personajeUpdate.setHistoria(personaje.getHistoria());
		personajeUpdate.setPeliculas(personaje.getPeliculas());
		
		return personajeService.save(personajeUpdate);
	}
	
	// eliminar personaje por id
	@DeleteMapping("/delete/characters/{id}")
	//@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void delete(@PathVariable Long id) {
		personajeService.delete(id);
	}
	

}







