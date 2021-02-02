package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	 @Autowired
	 private ProductRepository repo;
	 
	 public List<product> listAll(String keyword){
//		if(keyword != null) {
//			return repo.findAll(keyword);
//		}
		 return repo.findAll();
	 }
	 
	 public void save(product product) {
		 repo.save(product);
	 }
	 
	 public product get(Long id) {
		 return repo.findById(id).get();
	 }
	 
	 public void delete(Long id) {
		 repo.deleteById(id);
	 }
}
