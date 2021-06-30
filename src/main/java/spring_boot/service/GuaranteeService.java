package spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring_boot.entity.Guarantee;
import spring_boot.repository.GuaranteeRepository;
import spring_boot.utils.Utils;

@Service
public class GuaranteeService{
	
	@Autowired
	private GuaranteeRepository guaranteeRepository;
	
	public Guarantee save(Guarantee guarantee) {
		Utils.addCreate(guarantee);
		return guaranteeRepository.save(guarantee);
	}
}
