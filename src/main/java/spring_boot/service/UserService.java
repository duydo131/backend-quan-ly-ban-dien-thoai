package spring_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import spring_boot.entity.User;
import spring_boot.repository.UserRepository;
import spring_boot.utils.Utils;

@Service
public class UserService{
	
	@Autowired
	private UserRepository userRepository;

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public void saveAll(List<User> users) {
		for(User user : users) {
			Utils.addCreate(user);
		}
		userRepository.saveAll(users);
	}
	
	public void save(User user) {
		Utils.addCreate(user);
		userRepository.save(user);
	}

	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	public Page<User> findUser(Pageable pageable){
		return userRepository.findUser(pageable);
	}
	
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public User update(User user) {
		Utils.addUpdate(user);
		return userRepository.save(user);
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
}
