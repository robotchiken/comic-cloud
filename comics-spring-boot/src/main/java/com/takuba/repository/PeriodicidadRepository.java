package com.takuba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.takuba.model.Periodicidad;

public interface PeriodicidadRepository extends JpaRepository<Periodicidad, Integer> {
	Periodicidad findByDescripcion(String descripcion);
	@Query(value="SELECT p.* FROM PERIODICIDAD p, COMIC c WHERE p.ID_PERIODICIDAD = c.ID_PERIODICIDAD AND c.ID_COMIC = :idComic",nativeQuery=true)
	Periodicidad buscarPeriodicidad(@Param("idComic")Integer idComic);
}
