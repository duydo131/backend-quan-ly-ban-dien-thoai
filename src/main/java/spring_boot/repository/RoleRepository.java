package spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring_boot.entity.ERole;
import spring_boot.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(ERole name);
}
