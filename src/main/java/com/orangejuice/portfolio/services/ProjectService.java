package com.orangejuice.portfolio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orangejuice.portfolio.dtos.ProjectDTO;
import com.orangejuice.portfolio.entities.Project;
import com.orangejuice.portfolio.repositories.ProjectRepository;
import com.orangejuice.portfolio.services.exceptions.DatabaseException;
import com.orangejuice.portfolio.services.exceptions.ResourceNotFoundException;

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
		Project entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new ProjectDTO(entity);
	}
	
	@Transactional
	public ProjectDTO insert(Project body) {
		
		var project = repository.save(body);
		
		return new ProjectDTO(project);
	}
	
	@Transactional
	public ProjectDTO update(Long id, Project body) {
		
		
		try {
			Project entity = repository.getReferenceById(id);
			if(entity != null) {
				entity = body;
				entity.setId(id);
				entity = repository.save(entity);
				return new ProjectDTO(entity);				
			}
			else {
				throw new ResourceNotFoundException("Id not found: " + id);
			}		

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Database Integrity Violation!");
		}
		
	}
	
	

}
