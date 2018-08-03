package com.takuba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.takuba.model.Editorial;

public interface EditorialRepository extends JpaRepository<Editorial, Integer> {
		Editorial findByNombre(String nombre);
}
