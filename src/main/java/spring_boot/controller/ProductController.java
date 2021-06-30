package spring_boot.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring_boot.dto.AddProduct;
import spring_boot.dto.FileDTO;
import spring_boot.dto.ProductDTO;
import spring_boot.dto.ProductRequestDTO;
import spring_boot.entity.Product;
import spring_boot.service.ProductService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public ResponseEntity<Page<?>> listCustomer(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("id").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("id").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);

		Page<Product> products = productService.findProducts(pageable);
		List<ProductDTO> listDTO = products.stream().map(t -> {
			ProductDTO p = new ProductDTO();
			p.mapToProductDTO(t);
			return p;
		}).collect(Collectors.toCollection(ArrayList::new));

		Page<ProductDTO> productDTOs = new PageImpl<>(listDTO);

		return new ResponseEntity<>(productDTOs, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<?> post(@RequestBody ProductRequestDTO product) {
		Product newProduct;
		try {
			newProduct = productService.save(product.mapToProduct());
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
	}
	
	@Transactional
	@PostMapping("/add")
	public ResponseEntity<?> postProduct(@RequestBody FileDTO file) {
	    XSSFWorkbook workbook = null;
	    XSSFSheet worksheet = null;
	    Integer add = 0;
	    Integer update = 0;
		try {
			workbook = new XSSFWorkbook(file.getFilename());
			worksheet = workbook.getSheetAt(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    try {
	    	for(int i=1; i < worksheet.getPhysicalNumberOfRows(); i++) {
	    		XSSFRow row = worksheet.getRow(i);
		        String name = (String)row.getCell(1).getStringCellValue();
		        Optional<Product> productO = productService.findByName(name);
		        Product p = new Product();
		        p.setName(name);
		        p.setImage_url((String)row.getCell(2).getStringCellValue());
		        p.setDescription((String)row.getCell(3).getStringCellValue());
		        p.setPrice((Long)Math.round(row.getCell(4).getNumericCellValue()));
		        String x = row.getCell(5).getStringCellValue();
		        p.setGuarantee((int)Math.round(Integer.parseInt(x)));
		        p.setQuantity((int)Math.round(row.getCell(6).getNumericCellValue()));
		        p.setGuarantees(new ArrayList<>());
		        Product product = null;
		        if(productO.isPresent()) {
		        	product = productO.get();
			        if(p.equals(product)) {
			        	product.setQuantity(product.getQuantity() + p.getQuantity());
			        	productService.update(product);
			        	update++;
			        }else {
			        	productService.save(p);
			        	add++;
			        }
		        }else {
		        	productService.save(p);
		        	add++;
		        } 
		    }
	    }catch (Exception e) {
//	    	e.printStackTrace();
		}
	    try {
			workbook.close();
		} catch (IOException e) {
		}
	    return new ResponseEntity<>(new AddProduct(add, update), HttpStatus.OK);
	    
	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @RequestBody ProductRequestDTO productRequest) {
		
		Product product = productService.findById(id).orElseThrow(() -> new RuntimeException("Error : not exists product"));;
		if(product == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(productService.update(productRequest.mapToProduct()), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
		Product product = productService.findById(id).orElseThrow(() -> new RuntimeException("Error : not exists product"));;
		if(product == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		productService.deleteById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
}
