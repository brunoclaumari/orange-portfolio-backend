package com.orangejuice.portfolio.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orangejuice.portfolio.dtos.ProjectDTO;
import com.orangejuice.portfolio.entities.Project;
import com.orangejuice.portfolio.services.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService projService;
	
	
	@GetMapping
	public ResponseEntity<List<Project>> findAll() {
		List<Project> projects = projService.findAll();
		
		return ResponseEntity.ok().body(projects);
	}	
	
	
	@GetMapping("/tag")
	public ResponseEntity<List<ProjectDTO>> findByTag(@RequestParam String tag) {
		List<ProjectDTO> projects = projService.findByTag(tag);
		
		return ResponseEntity.ok().body(projects);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjectDTO> findById(@PathVariable Long id) {
		ProjectDTO project = projService.findById(id);
		
		return ResponseEntity.ok().body(project);
	}
	
	
	@PostMapping
	public ResponseEntity<ProjectDTO> insert(@RequestBody Project body){
		
		ProjectDTO proj = projService.insert(body);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(body.getId()).toUri();
		
		return ResponseEntity.created(uri).body(proj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProjectDTO> update(@PathVariable Long id, @Valid @RequestBody Project body) {
		ProjectDTO project = projService.update(id, body);
		
		return ResponseEntity.ok().body(project);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProjectDTO> delete(@PathVariable Long id) {
		projService.delete(id);

		return ResponseEntity.noContent().build();
		// vai retornar 204 que é que deu certo e o corpo da
		// resposta está vazio.
	}
	
	
	

}
