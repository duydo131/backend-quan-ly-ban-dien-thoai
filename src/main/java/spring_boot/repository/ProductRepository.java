package spring_boot.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring_boot.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("SELECT e FROM Product e")
	Page<Product> findProducts(Pageable pageable);
	
	Optional<Product> findByName(String name);
}
