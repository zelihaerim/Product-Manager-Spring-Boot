package com.zelihaerim.ProductEditDeleteCreateEtc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zelihaerim.ProductEditDeleteCreateEtc.Entities.Product;
import com.zelihaerim.ProductEditDeleteCreateEtc.Services.ProductService;
// notlar :
// https://thecodeprogram.com/java-springboot-nedir- 

@Controller
public class AppController {

	@Autowired
	private ProductService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts",listProducts);
		
		return "index";
	}
	
	@RequestMapping("/newProduct")
	public String showNewProductForm(Model model) {
		
		Product product = new Product();
		model.addAttribute("product",product);
		
		return "new_product";
	}
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);	
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name="id") Long id) {
		ModelAndView modelAndView = new ModelAndView("edit_product");
		Product product=service.get(id);
		modelAndView.addObject("product",product);
		
		
		return modelAndView;
	}
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name="id") Long id) {
		service.delete(id);
		
		return "redirect:/";
	}
	
	
}
