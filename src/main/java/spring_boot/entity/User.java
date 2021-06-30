package spring_boot.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends BaseEntity{
	
	@Column(nullable = false, unique = true, length = 100)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column
	private String name;
	
	@Column
	private Integer active = 1;
	
	@Column
	private String credit_card;

	@Column
	private String email;
	
	@Column
	private String address;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "user")
	private List<ListBroken> listBroken;
	
	@OneToMany(mappedBy = "user")
	private List<Guarantee> listGuarantee;

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

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getCredit_card() {
		return credit_card;
	}

	public void setCredit_card(String credit_card) {
		this.credit_card = credit_card;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ListBroken> getListBroken() {
		return listBroken;
	}

	public List<Guarantee> getListGuarantee() {
		return listGuarantee;
	}

	public void setListGuarantee(List<Guarantee> listGuarantee) {
		this.listGuarantee = listGuarantee;
	}

	public void setListBroken(List<ListBroken> listBroken) {
		this.listBroken = listBroken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
