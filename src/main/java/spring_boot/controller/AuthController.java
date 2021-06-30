package spring_boot.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring_boot.dto.JwtResponse;
import spring_boot.dto.MessageResponse;
import spring_boot.dto.SignupDTO;
import spring_boot.dto.UserLoginDTO;
import spring_boot.entity.ERole;
import spring_boot.entity.Role;
import spring_boot.entity.User;
import spring_boot.security.JwtUtil;
import spring_boot.security.UserPrincipal;
import spring_boot.service.RoleService;
import spring_boot.service.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil JwtUtil;
    
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Validated @RequestBody UserLoginDTO userLogin) {
    	
    	Authentication authentication = authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
    	);
    	
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	
    	String jwt = JwtUtil.generateJwtToken(authentication);
    	
    	UserPrincipal userDetail = (UserPrincipal)authentication.getPrincipal();
    	List<String> roles = userDetail.getAuthorities().stream()
    			.map(item -> item.getAuthority())
    			.collect(Collectors.toList());
    	
    	return ResponseEntity.ok(new JwtResponse(jwt, userDetail.getId(), userDetail.getUsername(), userDetail.getEmail(), roles));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupDTO signup) {
    	if(userService.existsByUsername(signup.getUsername())) {
    		return ResponseEntity
    				.badRequest()
    				.body(new MessageResponse("Error! Username is already!"));
    	}
    	
    	if(userService.existsByEmail(signup.getEmail())) {
    		return ResponseEntity
    				.badRequest()
    				.body(new MessageResponse("Error! Email is already!"));
    	}
    	
    	User user = new User();
    	user.setUsername(signup.getUsername());
    	user.setEmail(signup.getEmail());
    	user.setPassword(passwordEncoder.encode(signup.getPassword()));
    	
    	Set<String> strRoles = signup.getRole();
    	Set<Role> roles = new HashSet<>();
    	
    	if(strRoles == null) {
    		Role userRole = roleService.findByname(ERole.ROLE_USER);
    		roles.add(userRole);
    	}else {
    		strRoles.forEach(role -> {
    			switch (role) {
				case "admin":
					Role adminRole = roleService.findByname(ERole.ROLE_ADMIN);
		    		roles.add(adminRole);
					break;

				default:
					Role userRole = roleService.findByname(ERole.ROLE_USER);
					roles.add(userRole);
					break;
				}
    		});
    	}
    	user.setRoles(roles);
    	userService.save(user);
    	return ResponseEntity.ok(new MessageResponse("User Register successfully!"));
    }
}