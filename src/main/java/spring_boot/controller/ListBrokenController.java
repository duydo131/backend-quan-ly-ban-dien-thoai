package spring_boot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring_boot.dto.ListBrokenDTO;
import spring_boot.entity.BillBroken;
import spring_boot.entity.BrokenProduct;
import spring_boot.entity.ListBroken;
import spring_boot.entity.Product;
import spring_boot.entity.User;
import spring_boot.generate.CustomerBill;
import spring_boot.generate.InfoPay;
import spring_boot.generate.DOCX.BillDocx;
import spring_boot.generate.DOCX.GenerateDocx;
import spring_boot.security.JwtUtil;
import spring_boot.service.BillBrokenService;
import spring_boot.service.BrokenProductService;
import spring_boot.service.ListBrokenService;
import spring_boot.service.ProductService;
import spring_boot.service.UserService;
import spring_boot.utils.Utils;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("broken")
public class ListBrokenController {
	
	@Autowired
	private JwtUtil jwtUtils;
	
	@Autowired
	private ListBrokenService listBrokenService;
	
	@Autowired
	private BrokenProductService brokenProductService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired BillBrokenService billBrokenService;
	
	@GetMapping("")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Page<?>> listBroken(HttpServletRequest request, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("createdDate").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("createdDate").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);
		
		String jwt = Utils.parseJwt(request);
		User customer = getUserByJwt(jwt);
		
		Page<ListBroken> list = listBrokenService.findListBrokenByCustomer(customer, pageable);
		List<ListBrokenDTO> listDTO = list.stream().map(t -> {
					ListBrokenDTO p = new ListBrokenDTO();
					p.mapToListBrokenDTO(t);
					return p;
				}).collect(Collectors.toCollection(ArrayList::new));
		
		Page<ListBrokenDTO> pageDTO = new PageImpl<>(listDTO);
		return new ResponseEntity<>(pageDTO, HttpStatus.OK);
	}
	
	@GetMapping("gen")
	public ResponseEntity<?> listBroken() {
		List<Product> products = productService.findAll();
		for(Product p : products) {
			if(p.getCode() == null) {
				p.setCode(UUID.randomUUID());
				productService.save(p);
			}
		}
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	@PostMapping("")
	@Transactional
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addBroken(HttpServletRequest request, @RequestBody List<BrokenProduct> broken){
		ListBroken listbroken = new ListBroken();
		listBrokenService.save(listbroken);
		List<BrokenProduct> brokenProduct = new ArrayList<>();
		List<InfoPay> list = new ArrayList<>();
		Long totalAmount = 0L;
		if(broken == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		for(BrokenProduct b : broken) {
			Product product = productService.findById(b.getProduct().getId()).get();
			b.setProduct(product);
			b.setList_broken(listbroken);
			b = brokenProductService.save(b);
			brokenProduct.add(b);
			totalAmount += product.getPrice() * b.getQuantity();
			InfoPay in = new InfoPay(product.getName(), product.getPrice(), b.getQuantity(), 
						product.getCode().toString(), product.getPrice() * b.getQuantity());
			list.add(in);
		}
		listbroken.setBrokenProduct(brokenProduct);
		
		String jwt = Utils.parseJwt(request);
		User customer = getUserByJwt(jwt);
		listbroken.setUser(customer);
		listbroken.setTotalAmount(totalAmount);
		listBrokenService.save(listbroken);
		
		BillBroken billBroken = new BillBroken();
		billBroken.setList_broken(listbroken);
		billBroken.setList_guarantee(new ArrayList<>());
		billBrokenService.save(billBroken);
		
		CustomerBill info = new CustomerBill(
				customer.getName() == null ? "None" : customer.getName(), 
						customer.getEmail(), 
						customer.getAddress() == null ? "None" : customer.getAddress(), 
						totalAmount);
		info.setCode(billBroken.getCode().toString());
		GenerateDocx bill = new BillDocx(new Date(), list, info);
		String filename = bill.generateDocx();
		return new ResponseEntity<>(filename, HttpStatus.OK);
	}
	
	private User getUserByJwt(String jwt) {
		User user = null;
		if(jwt != null && jwtUtils.validateJwtToken(jwt)) {
			String username = jwtUtils.getUsernameFromJwtToken(jwt);
			user = userService.findByUsername(username).get();
		}
		return user;
	}
}
