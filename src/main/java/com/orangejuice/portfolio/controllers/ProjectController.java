package com.orangejuice.portfolio.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orangejuice.portfolio.dtos.ProjectDTO;
import com.orangejuice.portfolio.entities.Project;
import com.orangejuice.portfolio.services.ProjectService;

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
	
	@PostMapping
	public ResponseEntity<Project> insert(@RequestBody Project body){
		
		Project proj = projService.insert(body);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(body.getId()).toUri();
		
		return ResponseEntity.created(uri).body(proj);
	}

}
