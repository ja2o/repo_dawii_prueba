package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@RequestMapping("/rest/alumno")
@CommonsLog //Ver los logs, esto es de Lombok
public class AlumnoController {
	
	//private Logger log = Logger getLogger( AlumnoController.class.getName());
	
	@Autowired
	private AlumnoService service;

	@GetMapping
	public ResponseEntity<List<Alumno>> lista() {
		//log.info(">>>> Lista")
		System.out.println("Lista");
		return ResponseEntity.ok( service.listaAlumno() );
	}
	
	@PostMapping
	public ResponseEntity<Alumno> registrar( @RequestBody Alumno alumno ) {
		System.out.println("Registrar OK");
		return ResponseEntity.ok( service.insertaActualizaAlumno( alumno ) );
	}
	
	/*@PutMapping
	public ResponseEntity<Alumno> actualiza( @RequestBody Alumno alumno ) {
		Optional<Alumno> optionalAlumno = service.obtienePorId( alumno.getIdAlumno());
		
		if ( optionalAlumno.isPresent() ) {
			return ResponseEntity.ok( service.insertaActualizaAlumno( alumno ) );
		} else {
			return ResponseEntity.notFound().build();
		}
	} */
	@PutMapping
	public ResponseEntity<Alumno> actualiza(@RequestBody Alumno obj){

		Optional<Alumno> optAlumnmo =  service.obtienePorId(obj.getIdAlumno());

		if (optAlumnmo.isPresent()) {
			System.out.println("Actualiza OK");
			return ResponseEntity.ok(service.insertaActualizaAlumno(obj));	

		}else {

			return ResponseEntity.notFound().build();

		}	

	}
	/*
	@DeleteMapping("/{id}")
	public ResponseEntity<Alumno> elimina( @PathVariable("id") int idAlumno) {
		
		Optional<Alumno> optAlumnmo =  service.obtienePorId( idAlumno );

		if (optAlumnmo.isPresent()) {
			service.eliminaAlumno( idAlumno );
			System.out.println("Elimina OK");
			return ResponseEntity.ok( optAlumnmo.get());

		}else {

			return ResponseEntity.notFound().build();

		}	
	}*/
	
	@DeleteMapping("/{id}")

	public ResponseEntity<Alumno> elimina(@PathVariable("id") int idAlumno){
		System.out.println("Entró al método eliminar");
		//log.info(">>> elimina " + idAlumno);

		Optional<Alumno> optAlumno =  service.obtienePorId(idAlumno);

		if (optAlumno.isPresent()) {
			System.out.println("Entro a eliminar");
			service.eliminaAlumno(idAlumno);

			return ResponseEntity.ok(optAlumno.get());

		}else {
			System.out.println("No elimina");
			//log.error(">>> elimina " + idAlumno + " no encontrado");

			return ResponseEntity.notFound().build();

		}

	}

	



}
