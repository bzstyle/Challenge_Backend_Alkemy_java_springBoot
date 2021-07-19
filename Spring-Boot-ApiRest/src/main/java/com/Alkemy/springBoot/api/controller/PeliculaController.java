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

import com.Alkemy.springBoot.api.model.Pelicula;
import com.Alkemy.springBoot.api.service.Interface.IntPeliculaService;

@RestController
@RequestMapping("/api")
public class PeliculaController {
	
	@Autowired
	private IntPeliculaService peliculaController;
	
	// buscar todas las peliculas
		@GetMapping("/movies")
		public List<Pelicula> listarTodas(){
			return peliculaController.findAll();
		}
		
		// buscar peliculas por id
		@GetMapping("/id/movies/{id}")
		public Pelicula buscarPorId(@PathVariable Long id) {
			return peliculaController.findById(id);
		}
		
		// buscar peliculas por nombre
		@GetMapping("name/movies")
		public ResponseEntity<?> buscarPorNombre(@RequestParam String name){
			try {			
				return ResponseEntity.status(HttpStatus.OK).body(peliculaController.buscarPorTitulo(name));			
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
			}
			
		}
		
		// buscar peliculas por genero
		//@GetMapping("/genre/movies")
		//public ResponseEntity<?> buscarPorGenero(@RequestParam String genre){
			//try {
				//return ResponseEntity.status(HttpStatus.OK).body(peliculaser.buscarPorGenero(genre));
			//} catch (Exception e) {
				//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
			//}
		//}
		
		// buscar peliculas por fecha
		@GetMapping("/date/movies")
		public ResponseEntity<?> buscarPorFechaDeCreacion(@RequestParam String fecha){
			try {
				return ResponseEntity.status(HttpStatus.OK).body(peliculaController.buscarPorFechaDeCreacion(fecha));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
			}
		}
		
		// crear o cargar pelicula 
		@PostMapping("/create/movies")
		@ResponseStatus(HttpStatus.CREATED) 
		public Pelicula create(@RequestBody Pelicula pelicula) {
			return peliculaController.save(pelicula);		
		}
		
		// modificar pelicula
		@PutMapping("/update/movies/{id}")
		@ResponseStatus(HttpStatus.ACCEPTED) 
		public Pelicula modify(@RequestBody Pelicula pelicula , @PathVariable Long id) {
			Pelicula peliculaUpdate = peliculaController.findById(id);
			
			peliculaUpdate.setImagen(pelicula.getImagen());
			peliculaUpdate.setTitulo(pelicula.getTitulo());
			peliculaUpdate.setFechaDeCreacion(pelicula.getFechaDeCreacion());
			peliculaUpdate.setCalificacion(pelicula.getCalificacion());
			peliculaUpdate.setPersonajes(pelicula.getPersonajes());
			
			return peliculaController.save(peliculaUpdate);
		}
		
		// eliminar pelicula por id
		@DeleteMapping("/delete/movies/{id}")
		//@ResponseStatus(HttpStatus.NO_CONTENT) 
		public void delete(@PathVariable Long id) {
			peliculaController.delete(id);
		}
}
