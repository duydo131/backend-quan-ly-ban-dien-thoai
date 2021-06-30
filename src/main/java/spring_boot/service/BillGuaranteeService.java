package spring_boot.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring_boot.entity.Bill;
import spring_boot.entity.BillGuarantee;
import spring_boot.repository.BillGuaranteeRepository;
import spring_boot.utils.Utils;

@Service
public class BillGuaranteeService{
	
	@Autowired
	private BillGuaranteeRepository billGuaranteeRepository;
	
	public BillGuarantee save(BillGuarantee billGuarantee) {
		Utils.addCreate(billGuarantee);
		addCode(billGuarantee);
		return billGuaranteeRepository.save(billGuarantee);
	}
	
	private void addCode(Bill bill) {
		UUID code = UUID.randomUUID();
		bill.setCode(code);
	}
}
