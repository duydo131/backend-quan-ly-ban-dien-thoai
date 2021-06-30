package spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring_boot.entity.BrokenProduct;
import spring_boot.repository.BrokenProductRepository;
import spring_boot.utils.Utils;

@Service
public class BrokenProductService{
	
	@Autowired
	private BrokenProductRepository brokenProductRepository;
	
	public BrokenProduct save(BrokenProduct brokenProduct) {
		Utils.addCreate(brokenProduct);
		return brokenProductRepository.save(brokenProduct);
	}
}
