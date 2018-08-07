package com.takuba.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.takuba.bean.ComicExchangeBean;

@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="comics-spring-boot")
public interface CurrencyExhangeServiceProxy {
	@GetMapping("/comics-spring-boot/comic/titulo/{titulo}")
	public ComicExchangeBean retrieveExchangeTituloValue(@PathVariable("titulo") String titulo);
}
