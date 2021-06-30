package spring_boot.dto;

import spring_boot.entity.User;

public class UserLoginDTO {
	private String username;
	private String password;
	
	
	public UserLoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UserLoginDTO() {
		super();
	}

	public User mapToUser() {
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		return user;
	}
	
	public void mapToUserLogin(User user) {
		this.setPassword(user.getPassword());
		this.setUsername(user.getUsername());;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}
