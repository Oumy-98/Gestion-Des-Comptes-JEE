package cov.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserCompteServiceDetails userService;

	@Autowired
	SuccessAuthHandler mySuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	protected void configure(HttpSecurity http) throws Exception {

		http.formLogin().loginPage("/login").successHandler(mySuccessHandler).and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
		
		http.exceptionHandling().accessDeniedPage("/403");

		http.authorizeRequests().antMatchers("/").hasAnyRole("EMPLOYE","CLIENT");
		
		// les Clients
		http.authorizeRequests().antMatchers("/CreationClient", "/listeClients").hasRole("EMPLOYE");

		// Les comptes
		http.authorizeRequests()
				.antMatchers("/CreationCompte", "/listeComptes", "/lesOperations", "/UnDepot", "/ChercherCompte")
				.hasRole("EMPLOYE");
		// Les credits
		http.authorizeRequests().antMatchers("/PayerTraite", "/ChercherCredit").hasRole("EMPLOYE");

		// les demandes
		http.authorizeRequests().antMatchers("/employe", "/listeDemandes", "/avancerEtat", "/chercherDemande")
				.hasRole("EMPLOYE");

		
		/***************************** Client Environnement ********************************/
		
		http.authorizeRequests().antMatchers("/home", "/ProfilC","MesDemandes","MesComptes","MesCredits","creerMaDemande")
		.hasRole("CLIENT");
		
		/******************************** Session Management *************************************************/
		http.sessionManagement()
		  .invalidSessionUrl("/login");
		
		
		

	}

	// <!> La NoOpPassword class est une alternative utilisé uniquement pour le test
	// mais à priori il existe mieux en matière de sécurité !
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
