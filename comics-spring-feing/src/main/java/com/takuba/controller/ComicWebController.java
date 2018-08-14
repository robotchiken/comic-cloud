package com.takuba.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.takuba.bean.CalendarioExchangeBean;
import com.takuba.bean.ComicExchangeBean;
import com.takuba.bean.FormBean;
import com.takuba.service.ComicExhangeServiceProxy;

@Controller
public class ComicWebController {
	
	private static Logger log = LoggerFactory.getLogger(ComicWebController.class);
	@Autowired
	ComicExhangeServiceProxy proxy;
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		model.put("formBean", new FormBean());
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
}
