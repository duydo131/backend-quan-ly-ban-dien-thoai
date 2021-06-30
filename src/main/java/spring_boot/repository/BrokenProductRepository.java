package spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring_boot.entity.BrokenProduct;

public interface BrokenProductRepository extends JpaRepository<BrokenProduct, Long>{
}
