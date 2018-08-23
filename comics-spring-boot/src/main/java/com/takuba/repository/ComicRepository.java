package com.takuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.takuba.model.Comic;
import com.takuba.model.Editorial;
import com.takuba.model.Periodicidad;

public interface ComicRepository extends JpaRepository<Comic, Integer> {
	Comic findByTitulo(String titulo);
	List<Comic>findByEditorial(Editorial editorial);
	List<Comic> findByPeriodicidad(Periodicidad periodicidad);
	
	@Query(value="SELECT c.* FROM BIBLIOTECA B ,USUARIOS u, COMIC c WHERE u.ID_USUARIO = b.ID_USUARIO AND c.ID_COMIC = b.ID_COMIC AND b.ID_USUARIO = :idUsuario",nativeQuery=true)
	List<Comic> findComicsInLibrary(@Param("idUsuario") Integer idUsuario);
	
	@Query(value="SELECT c FROM Comic c WHERE c.idComic NOT IN (SELECT cal.id.idComic FROM Calendario cal WHERE cal.id.idUsuario =:idUsuario)")
	List<Comic>findComicsNotInCalendar(@Param("idUsuario") Integer idUsuario);
}
