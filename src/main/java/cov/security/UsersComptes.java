package cov.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cov.entities.Client;
import cov.entities.Employe;

@Entity
@Table(name="users_comptes")
public class UsersComptes implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id_User")
	private int idUser;
	
	@Column(name="username")
	private String username;
	
	@Column(name="Password")
	private String password;

	@Column(name="Role")
	private String role;
	
	@OneToOne(mappedBy="userCompte")
	private Employe employe;
	
	@OneToOne(mappedBy="userCompte")
	private Client client;
	
	public UsersComptes(String login, String password, String role) {
		this.username = login;
		this.password = password;
		this.role = role;
	}
	
	public UsersComptes() {}
	
	@Override
	public String toString() {
		return "UsersComptes [id_user=" + idUser + ", username=" + username + ", password=" + password + ", role=" + role
				+ "]";
	}

	public int getIdUser() {
		return idUser;
	}
	public void setId_user(int id_user) {
		this.idUser = id_user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String login) {
		this.username = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority(getRole()));		
		return list;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	

}
