package com.takuba.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
@Component
public class ZuulLoggingFilter extends ZuulFilter {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public Object run() throws ZuulException {
		final RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("request->{} request uri->{}",request,request.getRequestURI());
		logger.info("Autorization {}",requestContext.getRequest().getHeader("Authorization"));
		if(requestContext.getRequest().getHeader("Authorization") != null){
			Enumeration<String> parameterNames = requestContext.getRequest().getParameterNames();
			while(parameterNames.hasMoreElements()){
				logger.info("parameterNames:{}",parameterNames.nextElement());
			}
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
