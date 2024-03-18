package com.orangejuice.portfolio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orangejuice.portfolio.dtos.ProjectDTO;
import com.orangejuice.portfolio.entities.Project;
import com.orangejuice.portfolio.repositories.ProjectRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository repository;
	
	@Transactional(readOnly = true)
	public List<Project> findAll() {		
		
		List<Project> project = repository.findAll();
		
		return project;
	}
	
	//findByTag
	@Transactional(readOnly = true)
	public List<ProjectDTO> findByTag(String tag) {	
		
		List<ProjectDTO> listByTag = new ArrayList<>();
		
		List<Project> project = repository.findByTag(tag);
		project.forEach(x->{
			listByTag.add(new ProjectDTO(x));
		});
		
		return listByTag;
	}
	
	@Transactional
	public ProjectDTO findById(@Valid Long id) {
		
		Optional<Project> obj = repository.findById(id);
		// Product entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity
		// not found"));
		Project entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		
		return new ProjectDTO(entity);
	}
	
	@Transactional
	public Project insert(Project body) {
		
		var project = repository.save(body);
		
		return project;
	}
	
	

}
