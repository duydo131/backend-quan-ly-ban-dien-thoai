package spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring_boot.entity.Guarantee;

public interface GuaranteeRepository extends JpaRepository<Guarantee, Long>{
}
