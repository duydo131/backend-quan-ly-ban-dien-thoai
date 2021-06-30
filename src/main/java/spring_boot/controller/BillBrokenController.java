package spring_boot.controller;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring_boot.dto.BillBrokenDTO;
import spring_boot.entity.BillBroken;
import spring_boot.entity.User;
import spring_boot.security.JwtUtil;
import spring_boot.service.BillBrokenService;
import spring_boot.service.UserService;
import spring_boot.utils.Utils;

@RestController
@RequestMapping("bill_broken")
public class BillBrokenController {
	@Autowired
	private JwtUtil jwtUtils;

	@Autowired
	private BillBrokenService billBrokenService;
	
	@Autowired
	private UserService userService;
	
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
		
		Page<BillBroken> list= billBrokenService.findBillBrokenByUser(customer, pageable);
		List<BillBrokenDTO> listDTO = list.stream().map(t -> {
					BillBrokenDTO p = new BillBrokenDTO();
					p.mapToBillBrokenDTO(t);
					return p;
				}).collect(Collectors.toCollection(ArrayList::new));
		
		Page<BillBrokenDTO> pageDTO = new PageImpl<>(listDTO);
		return new ResponseEntity<>(pageDTO, HttpStatus.OK);
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
