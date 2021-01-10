package cov.security;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthentificationController {
	
	@RequestMapping(value="/403")
	 public String accessDenied() {
		 
		 return "403";
	 }
	
	@RequestMapping(value="/login")
	 public String loginForm() {
		 
		 return "LoginPage";
	 }
	
	@GetMapping("/")
	public String homeAccordingtoRole(SecurityContextHolderAwareRequestWrapper request) {
		System.out.println("on est dans le controler volu");
		if (request.isUserInRole("ROLE_EMPLOYE")) {
			System.out.println("c'est employe");
			return "employe/AcceuilEmploye";
		} else {
			System.out.println("c'est client");
			return "client/home";
		}
	}

}
