package cov.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cov.dao.UsersComptesRepository;

@Service
public class UserCompteServiceDetails implements UserDetailsService {
	
	@Autowired
	private UsersComptesRepository userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsersComptes> user = userDAO.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("utiliseur "+username+" non trouvé !");
		else 
			return user.get();
	}

}
