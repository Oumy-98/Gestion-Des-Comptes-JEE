package cov.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer{

	@Override 
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/employe").setViewName("employe/AcceuilEmploye.html");
	    registry.addViewController("/home").setViewName("client/home.html");
	    registry.addViewController("/client").setViewName("client/home.html");
	    registry.addViewController("/index").setViewName("index.html");
	    registry.addViewController("/Profil").setViewName("employe/ProfilEmploye");
	}
}
