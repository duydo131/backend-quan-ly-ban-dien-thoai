package spring_boot.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import spring_boot.entity.Product;
import spring_boot.repository.ProductRepository;
import spring_boot.utils.Utils;

@Service
public class ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(){
		List<Product> list = productRepository.findAll();
		return list;
	}
	
	public Product save(Product product) {
		Utils.addCreate(product);
		addCode(product);
		return productRepository.save(product);
	}
	
	public Product update(Product product) {
		Utils.addUpdate(product);
		return productRepository.save(product);
	}
	
	public Page<Product> findProducts(Pageable pageable){
		return productRepository.findProducts(pageable);
	}
	
	public Optional<Product> findById(Long id){
		return productRepository.findById(id);
	}
	
	public Optional<Product> findByName(String name){
		return productRepository.findByName(name);
	}
	
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
	
	private void addCode(Product product) {
		UUID code = UUID.randomUUID();
		product.setCode(code);
	}
}
