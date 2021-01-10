package cov.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import cov.dao.ClientRepository;
import cov.dao.EmployeRepository;
import cov.entities.Client;
import cov.entities.Employe;
import cov.entities.NotificationClient;
import cov.entities.NotificationEmploye;

@Component
public class SuccessAuthHandler implements AuthenticationSuccessHandler {

	private String role;
	private String targetUrl;

	@Autowired
	private ClientRepository clientDAO;

	@Autowired
	private EmployeRepository employeDAO;

	protected Log logger = LogFactory.getLog(this.getClass());

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		System.out.println(" <!> l'utilisateur principal : " + (UsersComptes) authentication.getPrincipal());

		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
		sessionCreation(request, authentication);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		determineTargetUrl(authentication);
		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected void determineTargetUrl(final Authentication authentication) {

		for (final GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
			String authorityName = grantedAuthority.getAuthority();
			System.out.println("le role trouvé est : " + authorityName);
			if (authorityName.equals("ROLE_CLIENT")) {
				this.role = "ROLE_CLIENT";
				this.targetUrl = "/client";
				System.out.println("le role : " + role + " l'url : " + targetUrl);
			} else if (authorityName.equals("ROLE_EMPLOYE")) {
				this.role = "ROLE_EMPLOYE";
				this.targetUrl = "/employe";
				System.out.println("le role : " + role + " l'url : " + targetUrl);
			} else if (authorityName.equals("ROLE_ADMIN")) {
				this.role = "ROLE_ADMIN";
				this.targetUrl = "/employe";
				System.out.println("le role : " + role + " l'url : " + targetUrl);
			} else {

				throw new IllegalStateException();
			}
		}

	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	void sessionCreation(HttpServletRequest request, final Authentication authentication) {
		HttpSession session = request.getSession(true);
		UsersComptes user = (UsersComptes) authentication.getPrincipal();
		if (role.equals("ROLE_EMPLOYE")) {
			Employe employe = employeDAO.findByUserCompte(user).get();
			session.setAttribute("sessionUserInfos", employe);
			session.setAttribute("role", role);
			session.setAttribute("nbrNotifNonVu", lesNotificationNonLu(employe).size());
			session.setAttribute("notifsNonVu", lesNotificationNonLu(employe));
		} else if (role.equals("ROLE_CLIENT")) {
			Client client = clientDAO.findByUserCompte(user).get();
			session.setAttribute("sessionUserInfos", client);
			session.setAttribute("role", role);
			session.setAttribute("nbrNotifNonVu", lesNotificationNonLu(client).size());
			session.setAttribute("notifsNonVu", lesNotificationNonLu(client));

		}

	}

	public List<NotificationEmploye> lesNotificationNonLu(Employe employe) {

		List<NotificationEmploye> notifsTotal = employeDAO.findLesNotifications(employe.getIdEmploye());
		List<NotificationEmploye> notifsNonVu = new ArrayList<NotificationEmploye>();

		for (NotificationEmploye notif : notifsTotal) {
			if (!notif.getIsRead()) {
				notifsNonVu.add(notif);
			}
		}
		return notifsNonVu;
	}

	public List<NotificationClient> lesNotificationNonLu(Client client) {
		List<NotificationClient> notifsTotal = clientDAO.findLesNotifications(client.getIdClient());
		List<NotificationClient> notifsNonVu = new ArrayList<NotificationClient>();
		for (NotificationClient notif : notifsTotal) {
			if (!notif.getIsRead()) {
				notifsNonVu.add(notif);
			}
		}
		return notifsNonVu;
	}

}
