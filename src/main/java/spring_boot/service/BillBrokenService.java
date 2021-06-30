package spring_boot.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import spring_boot.entity.Bill;
import spring_boot.entity.BillBroken;
import spring_boot.entity.User;
import spring_boot.repository.BillBrokenRepository;
import spring_boot.utils.Utils;

@Service
public class BillBrokenService{
	
	@Autowired
	private BillBrokenRepository billBrokenRepository;
	
	public Page<BillBroken> findBillBrokenByUser(User user, Pageable pageable){
		return billBrokenRepository.findBillBrokenByUser(user, pageable);
	}
	
	public Page<BillBroken> findAll(Pageable pageable){
		return billBrokenRepository.findAll(pageable);
	}
	
	public List<BillBroken> findAll(){
		return billBrokenRepository.findAll();
	}
	
	public Optional<BillBroken> findById(Long id){
		return billBrokenRepository.findById(id);
	}
	
	public BillBroken save(BillBroken billBroken) {
		if(billBroken.getList_guarantee().size() == 0) {
			Utils.addCreate(billBroken);
			addCode(billBroken);
		}else Utils.addUpdate(billBroken);
		return billBrokenRepository.save(billBroken);
	}
	
	private void addCode(Bill bill) {
		UUID code = UUID.randomUUID();
		bill.setCode(code);
	}
}
