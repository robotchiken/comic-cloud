package com.takuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.takuba.model.Calendario;
import com.takuba.model.CalendarioPK;

public interface CalendarioRepository extends JpaRepository<Calendario, CalendarioPK> {
	@Query(value="SELECT * FROM CALENDARIO WHERE ID_USUARIO =:idUsuario",nativeQuery=true)
	List<Calendario> buscarCalendario(@Param("idUsuario") Integer idUsuario);
}
