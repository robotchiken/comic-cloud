package com.takuba.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.takuba.bean.CalendarioExchangeBean;
import com.takuba.bean.ComicExchangeBean;

@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="comics-spring-boot")
public interface ComicExhangeServiceProxy {
	@GetMapping("/comics-spring-boot/comic/titulo/{titulo}")
	public ComicExchangeBean buscarComicPorTitulo(@PathVariable("titulo") String titulo);
	
	@GetMapping("/comics-spring-boot/comic/editorial/{editorial}")
	public List<ComicExchangeBean> buscarComicPorEditorial(@PathVariable("editorial") String editorial);
	
	@GetMapping("/comics-spring-boot/comic/periodicidad/{descripcion}")
	public List<ComicExchangeBean>buscarComicPorPeriodicidad(@PathVariable("descripcion") String descripcion);
	
	@GetMapping("/comics-spring-boot/comic/calendario/{idusuario}")
	public List<CalendarioExchangeBean> buscarCalendarioUsuario(@PathVariable("idusuario") Integer idusuario);
	
}
