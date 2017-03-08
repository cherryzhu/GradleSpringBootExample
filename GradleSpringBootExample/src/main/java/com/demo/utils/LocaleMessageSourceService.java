package com.demo.utils;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocaleMessageSourceService {

	@Resource
	private MessageSource messageSource;
	
	public String getMessage(String code){
		return this.getMessage(code, "");
	}
	
	public String getMessage(String code, Object[] args){
		return this.getMessage(code, args, "");
	}
	
	public String getMessage(String code, Locale locale){
		return this.getMessage(code, "", locale);
	}
	
	public String getMessage(String code, String defaultMessage){
		Locale locale = LocaleContextHolder.getLocale();
		return this.getMessage(code, defaultMessage, locale);
	}	
	
	public String getMessage(String code, String defaultMessage, Locale locale){
		return this.getMessage(code, null, defaultMessage, locale);
	}
	
	public String getMessage(String code, Object[] args, Locale locale){
		return this.getMessage(code, args, "", locale);
	}
	
	public String getMessage(String code, Object[] args, String defaultMessage){
		Locale locale = LocaleContextHolder.getLocale();
		return this.getMessage(code, args, defaultMessage, locale);
	}
	
	public String getMessage(String code, Object[] args, String defaultMessage, Locale locale){
		return messageSource.getMessage(code, args, defaultMessage, locale);
	}
	
}
