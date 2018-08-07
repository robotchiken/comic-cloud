package com.takuba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.takuba.bean.ComicExchangeBean;
import com.takuba.service.CurrencyExhangeServiceProxy;

@RestController
public class ComicSpringFeignController {
	private static Logger log = LoggerFactory.getLogger(ComicSpringFeignController.class);
	@Autowired
	CurrencyExhangeServiceProxy proxy;
	
	@GetMapping("comic-feign/titulo/{titulo}")
	private ComicExchangeBean obtenerTituloComic(@PathVariable String titulo){
		ComicExchangeBean comicExchangeBean = null;
		try {
			comicExchangeBean = proxy.retrieveExchangeTituloValue(titulo);
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return comicExchangeBean;
	}
}
