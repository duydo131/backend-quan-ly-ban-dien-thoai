package spring_boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring_boot.entity.User;
import spring_boot.service.UserService;

@Service
public class UserPrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username).get();
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}

}
