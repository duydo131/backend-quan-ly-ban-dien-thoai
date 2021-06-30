package spring_boot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring_boot.entity.ListBroken;
import spring_boot.entity.User;

public interface ListBrokenRepository extends JpaRepository<ListBroken, Long>{
	
	@Query("SELECT e FROM ListBroken e WHERE e.user = :user")
	Page<ListBroken> findListBrokenByCustomer(@Param("user") User user, Pageable pageable);
}
