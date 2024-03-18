package com.orangejuice.portfolio.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.orangejuice.portfolio.entities.Project;

public class ProjectDTO implements  Serializable{ 
	
	private static final long serialVersionUID = 1L;	
	
	private Long id;	
	
	private String title;
	
	private String link_project;
	
	private String description;
	
	private String img_url;	
	
	private String img_data;	
	
	private List<String> tags = new ArrayList<>();		

	public ProjectDTO() {		
	}
	
	public ProjectDTO(Project project) {	
		this.id = project.getId();
		this.title = project.getTitle();
		this.description = project.getDescription();
		this.link_project = project.getLink_project();
		this.img_data = project.getImg_data();
		this.img_url = project.getImg_url();
		setTagsWithString(project.getTags());
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getLink_project() {
		return link_project;
	}


	public void setLink_project(String link_project) {
		this.link_project = link_project;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImg_url() {
		return img_url;
	}


	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}


	public String getImg_data() {
		return img_data;
	}


	public void setImg_data(String img_data) {
		this.img_data = img_data;
	}


	public List<String> getTags() {
		return tags;
	}


	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public void setTagsWithString(String tags) {
		
		String[] vetor = tags.split(",");
		if(vetor != null && vetor.length > 0) {
			
			for (String item : vetor) {
				this.tags.add(item);
			}
		}		
	}

	
	
	

}
