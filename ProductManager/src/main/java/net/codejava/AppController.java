package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AppController {

	  @Autowired
	  private ProductService service;
	  
	  @Autowired
	  private ProductRepository repo;
	  
	  @RequestMapping("/")
	  public String viewHomePage(Model model) {
		  String keyword = "iPhone";
	      List<product> listProduct = service.listAll(keyword);
	      model.addAttribute("listProducts", listProduct);
	      
	      return "index";
	  }
	  
	  @RequestMapping("/new")
	  public String showNewProductForm(Model model) {
		  product product = new product();
		  model.addAttribute("product", product);
		  
		  return "new_product";
	  }
	  
	  @RequestMapping(value="/save", method = RequestMethod.POST)
	  public String saveProduct(@ModelAttribute("product") product product) {
		  service.save(product);
		  
		  return "redirect:/";
	  }
	  
	  @RequestMapping("/edit/{id}")
	  public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		  ModelAndView mav = new ModelAndView("edit_product");
		  
		  product product = service.get(id);
		  mav.addObject("product", product);
		  
		  return mav;
	  }
	  
	  @RequestMapping("/delete/{id}")
	  public String deleteProduct(@PathVariable(name="id") Long id) {
		  service.delete(id);
		  
		  return "redirect:/";
	  }
	  
	  @PostMapping("**/pesquisarproduct")
	  public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		  ModelAndView mav = new ModelAndView("index");
		  mav.addObject("listProducts", repo.findProductByName(nomepesquisa));
		  mav.addObject("product", new product());
		  return mav;
	  }
	  
}
