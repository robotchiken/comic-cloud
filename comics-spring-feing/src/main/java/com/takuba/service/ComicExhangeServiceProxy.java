package com.takuba.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.takuba.bean.CalendarioExchangeBean;
import com.takuba.bean.ComicExchangeBean;
import com.takuba.bean.Event;
import com.takuba.bean.EventForm;
import com.takuba.bean.FormBean;

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
	
	@GetMapping("/comics-spring-boot/comic/info/{id}")
	public ComicExchangeBean buscarComicPorId(@PathVariable("id")Integer id);
	
	@GetMapping("/comics-spring-boot/comic/library/{userid}")
	public List<ComicExchangeBean> buscarLibreriaUsuario(@PathVariable("userid")Integer userid);
	
	@PostMapping("/comics-spring-boot/comics/calendario/buscar/")
	public List<Event> buscarCalendario(@RequestBody EventForm eventform);
	
	@PostMapping("/comics-spring-boot/comics/calendario/actualizar/")
	public Integer actualizarCalendario(@RequestBody FormBean formBean);
	
	@GetMapping("/comics-spring-boot/comics/fuera-calendario/{idusuario}")
	public List<ComicExchangeBean> buscarComicsFueraCalendario(@PathVariable("idusuario") Integer idusuario);
	
	@PostMapping("/comics-spring-boot/comics/calendario/borrar/")
	public void borrarComicCalendario(@RequestBody FormBean formBean);
	
	@PostMapping("/comics-spring-boot/comic/titulo/{titulo}")
	public ComicExchangeBean buscarComicTitulo(@PathVariable("titulo") String titulo);
	
	@PostMapping("/comics-spring-boot/comic/")
	public void agregarComicCalendario(@RequestBody FormBean comic);
}
