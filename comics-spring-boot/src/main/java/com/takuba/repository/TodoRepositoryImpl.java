package com.takuba.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.takuba.bean.Biblioteca;

@Repository
public class TodoRepositoryImpl implements CustomTodoRepository {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
    TodoRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	@Override
	public void guardarBiblioteca(Biblioteca biblioteca) {
		String sql ="INSERT INTO BIBLIOTECA (ID_USUARIO,ID_COMIC) VALUES(:idUsuario,:idComic)";
		Map<String,Object>paramMap = new HashMap<>();
		paramMap.put("idUsuario", biblioteca.getIdUsuario());
		paramMap.put("idComic",biblioteca.getIdComic());
		jdbcTemplate.update(sql,paramMap);
	}
}
