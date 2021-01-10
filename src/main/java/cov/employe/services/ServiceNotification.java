package cov.employe.services;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cov.dao.EmployeRepository;
import cov.dao.NotificationEmployeRepository;
import cov.entities.Employe;
import cov.entities.NotificationEmploye;

@Service
public class ServiceNotification {

	
	@Autowired
	private NotificationEmployeRepository notifEmployeDAO;
	
	
	@Autowired
	private EmployeRepository employeDAO;
	
	
	public List<NotificationEmploye> lesNotifications(HttpSession session)
	{
		Employe employe = (Employe) session.getAttribute("sessionUserInfos");
		List<NotificationEmploye> notifs = employeDAO.findLesNotifications(employe.getIdEmploye());
		Collections.sort(notifs);
		return notifs;
	}
	
	
	public void notificationEstVu(HttpSession session)
	{
		lesNotifications(session).forEach(notif ->{
			notif.setIsRead(true);
			notifEmployeDAO.save(notif);
		});
		session.removeAttribute("nbrNotifNonVu");
		session.setAttribute("nbrNotifNonVu", 0);
	}
	
	
	
	
	
	
}
