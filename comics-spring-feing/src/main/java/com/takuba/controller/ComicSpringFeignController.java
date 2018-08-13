package com.takuba.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.takuba.bean.CalendarioExchangeBean;
import com.takuba.bean.ComicExchangeBean;
import com.takuba.service.ComicExhangeServiceProxy;

@RestController
public class ComicSpringFeignController {
	private static Logger log = LoggerFactory.getLogger(ComicSpringFeignController.class);
	@Autowired
	ComicExhangeServiceProxy proxy;
	
	@GetMapping("/comic-feign/titulo/{titulo}")
	private ComicExchangeBean obtenerTituloComic(@PathVariable String titulo){
		ComicExchangeBean comicExchangeBean = null;
		try {
			comicExchangeBean = proxy.buscarComicPorTitulo(titulo);
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return comicExchangeBean;
	}
	@GetMapping("/comic-feign/calendario/{idusuario}")
	private List<CalendarioExchangeBean> obtenerCalendarioUsuario(@PathVariable Integer idusuario){
		return proxy.buscarCalendarioUsuario(idusuario);
	}
	
	@GetMapping("/comic-feing/comic/editorial/{editorial}")
	private List<ComicExchangeBean> obtenerComicsEditorial(@PathVariable String editorial){
		return proxy.buscarComicPorEditorial(editorial);
	}
	
}
