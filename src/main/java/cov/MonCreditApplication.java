package cov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cov.dao.ClientRepository;
import cov.dao.EmployeRepository;
import cov.security.UsersComptes;

@SpringBootApplication
public class MonCreditApplication implements CommandLineRunner {

//	@Autowired
//	private INotificationEmploye comptedao;
	
	@Autowired
	private EmployeRepository comp;
	
	//@Autowired
	//private IUserCompteDAO userDAO;
	
	@Autowired
	private ClientRepository clientdao;

	public static void main(String[] args) {
		SpringApplication.run(MonCreditApplication.class, args);
	}

	public void run(String... arg0) throws Exception {

//		for (NotificationEmploye not : comptedao.findAll()) {
//			System.out.println("notif est !!!! : " + not);
//		}
	
				
		UsersComptes user = new UsersComptes("hsini@gmail.com", "hsini", "ROLE_EMPLOYE");
		user.setId_user(6);
		System.out.println("employe par défaut 4 : "+comp.findById(4));
		System.out.println("----------");
		System.out.println(comp.findByUserCompte(user));
		System.out.println("**********************************************************************");
		System.out.println(clientdao.findById(2).get());
	}

}
