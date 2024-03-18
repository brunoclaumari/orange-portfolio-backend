package com.orangejuice.portfolio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orangejuice.portfolio.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query("SELECT DISTINCT obj FROM Project obj "
			+ " WHERE "			
			+ "(LOWER(obj.tags) LIKE LOWER(CONCAT('%',:tag,'%')) )")
	List<Project> findByTag(String tag);
}
