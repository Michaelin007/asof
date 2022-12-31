package com.energ.solar.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("brand-logos",registry);
	}
	public void exposeDirectory(String dirName,ResourceHandlerRegistry registry) {
		Path uploadDir=Paths.get(dirName);
		String uploadPath = uploadDir.toFile().getAbsolutePath();
		if(dirName.startsWith("../")) dirName=dirName.replace("../", "");
		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
	}
	/*
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("index");
		registry.addViewController("/admin-page").setViewName("index2");
		registry.addViewController("/posts/blog/{id}/brand-logos/{id}/{image}").setViewName("index4");
	}
/*
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path uploadDir=Paths.get("./brand-logos");
		String uploadPath = uploadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/brand-logos/**").addResourceLocations("file:/" + uploadPath + "/");
	}
	*/
}
