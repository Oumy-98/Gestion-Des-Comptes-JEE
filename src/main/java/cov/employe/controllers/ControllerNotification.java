package cov.employe.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import cov.employe.services.ServiceNotification;

@Controller
public class ControllerNotification {

	@Autowired
	private ServiceNotification serviceNotif;
	
	
	@GetMapping("/Notifications")
	public ModelAndView notifEmploye(HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("notifications", serviceNotif.lesNotifications(session));
		mv.setViewName("employe/Notifications");
		serviceNotif.notificationEstVu(session);
		return mv;
	}
	
}
