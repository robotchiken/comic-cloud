package com.takuba.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.takuba.bean.BiblioDto;
import com.takuba.bean.Biblioteca;
import com.takuba.bean.Event;
import com.takuba.bean.FormBean;

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
	
	public List<BiblioDto> buscarBibliotecaCalendario(Integer idusuario){
		String sql ="SELECT c.ID_COMIC,c.TITULO,p.DESCRIPCION periodicidad,e.NOMBRE EDITORIAL,b.FALTANTES,cal.FECHA_PUBLICAR FROM BIBLIOTECA b "+
					"INNER JOIN COMIC c ON c.ID_COMIC = b.ID_COMIC AND b.ID_USUARIO = :idusuario "+
					"INNER JOIN PERIODICIDAD p ON c.ID_PERIODICIDAD = p.ID_PERIODICIDAD "+
					"INNER JOIN EDITORIAL e ON c.ID_EDITORIAL = e.ID_EDITORIAL "+
					"INNER JOIN CALENDARIO cal ON cal.ID_USUARIO = b.ID_USUARIO AND cal.ID_COMIC=b.ID_COMIC";
		Map<String,Object>paramMap = new HashMap<>();
		paramMap.put("idusuario", idusuario);
		return jdbcTemplate.query(sql, paramMap, new RowMapper<BiblioDto>() {

			@Override
			public BiblioDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				return new BiblioDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(3), rs.getString(4), rs.getDate(5));
			}
		});
	}
	
	public List<Event>buscarComicsCalendario(Integer idusuario,String start,String end){
		String sql ="SELECT com.TITULO,cal.FECHA_PUBLICAR,cal.NUMERO,cal.ID_COMIC FROM CALENDARIO cal "+
					"INNER JOIN COMIC com ON com.ID_COMIC = cal.ID_COMIC AND cal.ID_USUARIO=:idusuario AND cal.FECHA_PUBLICAR BETWEEN :start AND :end "+
					"INNER JOIN PERIODICIDAD p ON com.ID_PERIODICIDAD = p.ID_PERIODICIDAD "+
					"INNER JOIN EDITORIAL e ON com.ID_EDITORIAL = e.ID_EDITORIAL";
		Map<String,Object>paramMap = new HashMap<>();
		paramMap.put("idusuario", idusuario);
		paramMap.put("start", start);
		paramMap.put("end", end);
		return jdbcTemplate.query(sql, paramMap, new RowMapper<Event>() {

			@Override
			public Event mapRow(ResultSet rs, int rownumber) throws SQLException {
				String title=rs.getString(1) +" No. " +String.valueOf(rs.getInt(3));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date fechaPublicar= rs.getDate(2);
				String start=sdf.format(fechaPublicar);
				String end=start;
				int id=rs.getInt(4);
				return new Event(title,start,end,id);
			}
		});
	}

	public Integer actualizarCalendario(FormBean formBean) {
		String sql ="UPDATE CALENDARIO SET FECHA_PUBLICAR = :fechaPublicar WHERE ID_COMIC =:idComic AND ID_USUARIO =:idUsuario ";
		Map<String,Object>paramMap = new HashMap<>();
		paramMap.put("idUsuario", formBean.getIdUsuario());
		paramMap.put("idComic",formBean.getIdComic());
		paramMap.put("fechaPublicar",formBean.getFecha());
		return jdbcTemplate.update(sql,paramMap);
		
	}
	
}
