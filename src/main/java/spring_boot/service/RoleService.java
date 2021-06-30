package spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring_boot.entity.ERole;
import spring_boot.entity.Role;
import spring_boot.repository.RoleRepository;

@Service
public class RoleService{
	
	@Autowired
	private RoleRepository roleRepository;

	public Role findByname(ERole name) {
		return roleRepository.findByName(name);
	}

}
