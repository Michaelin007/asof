package com.energ.solar.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.energ.solar.model.Product;
import com.energ.solar.repository.ProductRepository;
import com.energ.solar.service.*;





@Controller
public class RabbitController {
	
	
    private static final String bucket_name="mikevistabucket";
	
	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping("/")
	public String homepage(Model model) {
		
		return "index";
	}
	@RequestMapping("/login")
	public String  homex() {
		return "login";
		
	}

	
	@RequestMapping("/admin")
	public String homepagey(Model model) {
		
		return "store";
	}
	

	
	@RequestMapping("/store-x")
	public String admin2(Model model,@Param ("keyword") String keyword) {

		return listbyPage(model,1,keyword);
		
	}
	@RequestMapping("/index2")
	public String admin22(Model model,@Param ("keyword") String keyword) {

		return listbyPage2(model,1,keyword);
		
	}
	
	@RequestMapping("/store/{pageNumber}")
	public String listbyPage (Model model, @PathVariable("pageNumber") int currentPages, @Param ("keyword") String keyword) {
		
		
		Sort sort= Sort.by("date").descending();
		Pageable pageable=PageRequest.of(currentPages-1,10,sort);
		Page <Product> page;
		if(keyword !=null) {
		 page=productRepository.findAll(keyword,pageable);
		} else {
		 page=productRepository.findAll(pageable);}
		
		long totalItems=page.getTotalElements();
		int totalpages=page.getTotalPages();
		
		List<Product> product=page.getContent();
		
		model.addAttribute("keyword",keyword);
		model.addAttribute("currentPages", currentPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalpages", totalpages);
		model.addAttribute("product", product);
		
		return "store2";
		
	}
	@RequestMapping("/admin/{pageNumber}")
	public String listbyPage2 (Model model, @PathVariable("pageNumber") int currentPages, @Param ("keyword") String keyword) {
		
		
		Sort sort= Sort.by("date").descending();
		Pageable pageable=PageRequest.of(currentPages-1,10,sort);
		Page <Product> page;
		if(keyword !=null) {
		 page=productRepository.findAll(keyword,pageable);
		} else {
		 page=productRepository.findAll(pageable);}
		
		long totalItems=page.getTotalElements();
		int totalpages=page.getTotalPages();
		
		List<Product> product=page.getContent();
		
		model.addAttribute("keyword",keyword);
		model.addAttribute("currentPages", currentPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalpages", totalpages);
		model.addAttribute("product", product);
		
		return "index2";
		
	}
/*
	@RequestMapping("/admin-page")
	public String admin(Model model) {
		List<Product> user=(List<Product>) productRepository.findAll();
		model.addAttribute("product", product);
		return "index2";
		
	}
	*/
	@GetMapping("/proadmin")
	public String ads(Model model) {
		model.addAttribute("product",new Product());
		return "productad";
	}
	@PostMapping("/admin-product")
	public String save2(@Validated @ModelAttribute("product") Product product, BindingResult bindingResult) throws IOException {
		if(bindingResult.hasErrors()) {
			return "productad";
		}
		
		
		//String uploadDir= "brand-logos/" + savesUser.getId();
		//fileUpload.saveFile(uploadDir,fileName,file);
		
		
	        
	        
		
		
		productRepository.save(product);
	
		
		
		return"redirect:/proadmin?success";
	}
	@GetMapping("/product/{id}")
	public String addQiin(@PathVariable("id") Long id,Model model) {
		productRepository.deleteById(id);
		return "redirect:/index2";
	}
	@RequestMapping("/product/u/{id}")
	public String view(@PathVariable("id") Long id, Model model ) {
		Product product = productRepository.findById(id).orElseThrow();
		//List<Ask2> ask22=(List<Ask2>) recruit.findAll();
		
	    model.addAttribute("product", product);
	   
	    return "productad2";
	}

	@GetMapping("/product/f/{id}")
	public String vijjew(@PathVariable("id") Long id, Model model ) {
		//Ask2 ask2 = recruit.findById(id).orElseThrow();
		Product product=productRepository.findById(id).orElseThrow();
	    model.addAttribute("ask2", product);
	    return "index2";
	}
	@PostMapping("/update/{id}")
	public String updateBlog(@PathVariable("id") Long id,@Validated Product product,BindingResult result,Model model) {
			if(result.hasErrors()) {
				product.setId(id);
				return "productad2";
			}
			productRepository.save(product);
			model.addAttribute("ask2",productRepository.findAll());
			return "redirect:/product/u/{id}?success";
	}
	

}
