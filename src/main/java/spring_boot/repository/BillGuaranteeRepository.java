package spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring_boot.entity.BillGuarantee;

public interface BillGuaranteeRepository extends JpaRepository<BillGuarantee, Long>{
}
