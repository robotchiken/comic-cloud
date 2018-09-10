package com.takuba.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.takuba.bean.AjaxResponseBody;
import com.takuba.bean.CalendarioExchangeBean;
import com.takuba.bean.ComicExchangeBean;
import com.takuba.bean.DeleteForm;
import com.takuba.bean.Event;
import com.takuba.bean.EventForm;
import com.takuba.bean.FormBean;
import com.takuba.service.ComicExhangeServiceProxy;

@Controller
public class ComicWebController {
	
	private static Logger log = LoggerFactory.getLogger(ComicWebController.class);
	@Autowired
	ComicExhangeServiceProxy proxy;
	private Integer userid = new Integer(47);

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("userid", this.userid);
		model.put("formBean", new FormBean());
		model.put("comics", proxy.buscarComicsFueraCalendario(userid));
		return "welcome";
	}
	@RequestMapping(value="/infoUsuario", method = RequestMethod.POST)
	public String buscarInfoUsario(@ModelAttribute FormBean formBean, Model model){
		log.info(formBean.toString());
		model.addAttribute("message", "Se selecciono el numero:"+formBean.getIdUsuario());
		List<CalendarioExchangeBean> calendarios = proxy.buscarCalendarioUsuario(formBean.getIdUsuario());
		for(CalendarioExchangeBean tmp: calendarios){
			tmp.setComic(proxy.buscarComicPorId(tmp.getId().getIdComic()));
		}
		List<ComicExchangeBean> libreriaUsuario = proxy.buscarLibreriaUsuario(formBean.getIdUsuario());
		model.addAttribute("calendarios", calendarios);
		model.addAttribute("libreria", libreriaUsuario);
		return "welcome";
	}
	@RequestMapping(value="/get-events", method = RequestMethod.POST)
	public ResponseEntity<?> buscarEventos(@ModelAttribute EventForm form,Model model){
		/*
		AjaxResponseBody result = new AjaxResponseBody();
		List<Event>events=new ArrayList<>();
		events.add(new Event("All Day Event", "2018-08-01", "2018-08-01"));
		events.add(new Event("Long Event", "2018-08-07", "2018-08-10"));
		events.add(new Event("Conference", "2018-08-11", "2018-08-13"));
		result.setEvents(events);
		*/
		List<Event> events = proxy.buscarCalendario(form);
		return ResponseEntity.ok(events);
	}
	@RequestMapping(value="/update-calendar", method = RequestMethod.POST)
	public ResponseEntity<?>  actualizarCalendario(@ModelAttribute FormBean formBean){
		AjaxResponseBody result = new AjaxResponseBody();
		result.setNumeroActualizados(proxy.actualizarCalendario(formBean));
		return ResponseEntity.ok(result);
	}
	@RequestMapping(value="/delete-event",method = RequestMethod.GET)
	public ResponseEntity<?> borrarComicCalendario(@RequestParam List<Integer> idcomic){
		AjaxResponseBody result = new AjaxResponseBody();
		idcomic.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer id) {
				FormBean formBean= new FormBean();
				formBean.setIdComic(id);
				formBean.setIdUsuario(userid);
				proxy.borrarComicCalendario(formBean);
				
			}
		});
		
		result.setMensaje("Comics eliminados");
		return ResponseEntity.ok(result);
	}
	@RequestMapping(value="/buy-event",method = RequestMethod.GET)
	public ResponseEntity<?> comprarComics(@RequestParam List<Integer> idcomic){
		AjaxResponseBody result = new AjaxResponseBody();
		result.setMensaje("Comics comprados");
		return ResponseEntity.ok(result);
	}
	@RequestMapping(value="/add-calendar", method = RequestMethod.POST)
	public ResponseEntity<?> agregarComicCalendario(@ModelAttribute FormBean comic){
		AjaxResponseBody result = new AjaxResponseBody();
		proxy.agregarComicCalendario(comic);
		result.setMensaje("Comics agregado al calendario");
		return ResponseEntity.ok(result);
	}
}
