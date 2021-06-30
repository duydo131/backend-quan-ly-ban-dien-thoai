package spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import spring_boot.entity.ListBroken;
import spring_boot.entity.User;
import spring_boot.repository.ListBrokenRepository;
import spring_boot.utils.Utils;

@Service
public class ListBrokenService{
	
	@Autowired
	private ListBrokenRepository listBrokenRepository;
	
	public ListBroken save(ListBroken listBroken) {
		Utils.addCreate(listBroken);
		return listBrokenRepository.save(listBroken);
	}
	
	public Page<ListBroken> findListBrokenByCustomer(User user, Pageable pageable){
		return listBrokenRepository.findListBrokenByCustomer(user, pageable);
	}
}
