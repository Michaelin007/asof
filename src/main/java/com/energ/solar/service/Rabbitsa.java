package com.energ.solar.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.energ.solar.model.Rabbit;
import com.energ.solar.repository.RabbitRepository;




@Service
public class Rabbitsa {
	@Autowired
	private RabbitRepository rabbitRepository;
	
	public void saveUser(MultipartFile file) throws IOException {
		
		 Rabbit p= new Rabbit();
		 String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		 if(fileName.contains("..")) {
			 System.out.println("not a valid file");
		 }
		 p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		
		
		 rabbitRepository.save(p);
		 
	}
	

}
