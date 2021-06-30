package spring_boot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring_boot.entity.BillBroken;
import spring_boot.entity.BillGuarantee;
import spring_boot.entity.Guarantee;
import spring_boot.entity.Product;
import spring_boot.entity.User;
import spring_boot.service.BillBrokenService;
import spring_boot.service.BillGuaranteeService;
import spring_boot.service.GuaranteeService;
import spring_boot.service.ProductService;
import spring_boot.service.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("guarantee")
public class GuaranteeController {
	
	@Autowired
	private BillBrokenService billBrokenService;
	
	@Autowired
	private BillGuaranteeService billGuaranteeService;
	
	@Autowired
	private GuaranteeService guaranteeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("")
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addGuarantee(HttpServletRequest request, @RequestBody Guarantee g){
		if(g == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		BillGuarantee billGuarantee = new BillGuarantee();
		User customer = userService.findById(g.getUser().getId()).get();
		BillBroken billBroken = billBrokenService.findById(g.getBill_broken().getId()).get();
		Product product = productService.findById(g.getProduct().getId()).get();
		
		product.getGuarantees().add(g);
		
		g.setUser(customer);
		g.setBill_broken(billBroken);
		guaranteeService.save(g);
		
		billBroken.getList_guarantee().add(g);
		billBrokenService.save(billBroken);
		
		billGuarantee.setGuarantee(g);
		billGuaranteeService.save(billGuarantee);
		
		product.getGuarantees().add(g);
		productService.save(product);
		
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
}
















