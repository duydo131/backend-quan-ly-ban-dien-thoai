package spring_boot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring_boot.entity.BillBroken;
import spring_boot.entity.BrokenProduct;
import spring_boot.entity.Product;
import spring_boot.generate.ProductSales;
import spring_boot.generate.Sales;
import spring_boot.generate.Total;
import spring_boot.generate.DOCX.GenerateDocx;
import spring_boot.generate.DOCX.ProductDocx;
import spring_boot.generate.DOCX.ProductGuaranteeDocx;
import spring_boot.generate.DOCX.SalesDocx;
import spring_boot.service.BillBrokenService;
import spring_boot.service.ProductService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("statistic")
public class StatisticController {
	
	@Autowired
	private BillBrokenService billBrokenService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("sales")
	public ResponseEntity<?> listCustomer(
			@RequestParam(name = "start_date", required = false) String startDateStr,
			@RequestParam(name = "end_date", required = false) String endDateStr) {
		Date startDate = null, endDate = null;
		if(startDateStr != null) {
			startDate = mapToDate(startDateStr);
		}
		if(endDateStr != null) {
			endDate = mapToDate(endDateStr);
		}
		final Long startL = startDate.getTime();
		final Long endL = endDate.getTime();
		if (!checkDate(startDate, endDate)) 
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		List<BillBroken> bills = billBrokenService.findAll();
		bills = bills.stream().filter(b -> {
			return b.getCreatedDate().getTime() > startL &&  b.getCreatedDate().getTime() < endL;
		}).collect(Collectors.toCollection(ArrayList::new));
		Map<String, Integer> maps = new HashMap<>();
		List<Sales> list = new ArrayList<>();
		for(BillBroken bill: bills) {
			for(BrokenProduct broken: bill.getList_broken().getBrokenProduct()) {
				if(maps.containsKey(broken.getProduct().getName())) {
					int index = maps.get(broken.getProduct().getName());
					Sales sale = list.get(index);
					sale.setQuantity(sale.getQuantity() + broken.getQuantity());
					sale.setCustomer(sale.getCustomer() + 1);
					sale.setRevense(sale.getRevense() + broken.getProduct().getPrice() * broken.getQuantity());
				}else {
					int index = list.size();
					maps.put(broken.getProduct().getName(), index);
					Sales sale = new Sales();
					sale.setName(broken.getProduct().getName());
					sale.setQuantity(broken.getQuantity());
					sale.setCustomer(1);
					sale.setRevense(broken.getProduct().getPrice() * broken.getQuantity());
					list.add(sale);
				}
			}
		}
		Long total = 0L;
		for(Sales sale : list) {
			total += sale.getRevense();
		}
		GenerateDocx bill = new SalesDocx(new Date(), list, new Total("", total));
		String filename = bill.generateDocx();
		return new ResponseEntity<>(filename, HttpStatus.OK);
	}
	
	@GetMapping("product")
	public ResponseEntity<?> listProduct(){
		List<Product> products = productService.findAll();
		List<ProductSales> list = products.stream().filter(b -> {
			return b.getQuantity() > 0;
		}).map(p ->{
			ProductSales newP = new ProductSales();
			newP.setName(p.getName());
			newP.setCode(p.getCode().toString());
			newP.setPrice(p.getPrice());
			newP.setRate(p.getRating());
			newP.setQtt(p.getQuantity());
			return newP;
		})
		.collect(Collectors.toCollection(ArrayList::new));
		GenerateDocx bill = new ProductDocx(new Date(), list);
		String filename = bill.generateDocx();
		return new ResponseEntity<>(filename, HttpStatus.OK);
	}
	
	@GetMapping("product_guarantee")
	public ResponseEntity<?> listProducGuaranteet(){
		List<Product> products = productService.findAll();
		List<ProductSales> list = products.stream().filter(b -> {
			return b.getGuarantees().size() > 0;
		}).map(p ->{
			ProductSales newP = new ProductSales();
			newP.setName(p.getName());
			newP.setCode(p.getCode().toString());
			newP.setPrice(p.getPrice());
			newP.setRate(p.getRating());
			newP.setQtt(p.getGuarantees().size());
			return newP;
		})
		.collect(Collectors.toCollection(ArrayList::new));
		
		Collections.sort(list, new Comparator<ProductSales>() {
			@Override
            public int compare(ProductSales o1, ProductSales o2) {
                return o1.getQtt() < o2.getQtt() ? 1 : -1;
            }
		});
		GenerateDocx bill = new ProductGuaranteeDocx(new Date(), list);
		String filename = bill.generateDocx();
		return new ResponseEntity<>(filename, HttpStatus.OK);
	}
	
	private boolean checkDate(Date start, Date end) {
		if((start == null && end != null) || (start != null && end == null)) return false;
		if (start.getTime() > end.getTime()) return false;
		return true;
	}
	
	private Date mapToDate(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = format.parse(dateStr);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}
}
