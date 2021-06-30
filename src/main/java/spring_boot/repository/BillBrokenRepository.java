package spring_boot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring_boot.entity.BillBroken;
import spring_boot.entity.User;

public interface BillBrokenRepository extends JpaRepository<BillBroken, Long>{
	
	@Query("Select distinct a from BillBroken a  inner join ListBroken b on a.list_broken=b.id WHERE b.user = :user")
	Page<BillBroken> findBillBrokenByUser(@Param("user") User user, Pageable pageable);
	
	@Query("Select a from BillBroken a")
	Page<BillBroken> findAll(Pageable pageable);
}
