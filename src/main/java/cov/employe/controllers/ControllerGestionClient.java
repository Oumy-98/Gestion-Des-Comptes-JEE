package cov.employe.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cov.employe.services.ServiceClient;
import cov.entities.Client;
import cov.validation.ClientException;

@Controller
public class ControllerGestionClient {

	@Autowired
	private ServiceClient clientService;

	@GetMapping("/listeClients")
	public ModelAndView listeClients() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("clients", clientService.listeClient());
		mv.setViewName("employe/GestionClient/listeClients");
		return mv;
	}

	@GetMapping("/CreationClient")
	public ModelAndView creerClientForm(@RequestParam(required = false) Integer idClient) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("client", clientService.returnClient(idClient));
		mv.setViewName("employe/GestionClient/CreationClient");
		return mv;
	}

	@PostMapping("/CreationClientV")
	public ModelAndView creerClient(@Valid Client client, BindingResult binding) {

		RedirectView redirect = new RedirectView();
		if (binding.hasErrors()) {
			return new ModelAndView("employe/GestionClient/CreationClient");
		} else {
			try {
				clientService.creerNVClient(client);
				redirect.setUrl("/listeClients");
			} catch (ClientException e) {
				redirect.setUrl("/CreationClient?error=" + e.getMessage());
			}

		}
		return new ModelAndView(redirect);

	}

	@GetMapping("/ChercherClient")
	public ModelAndView chercherClient(@RequestParam(name = "idClient", required = false) Integer idClient) {
		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("client", clientService.clientExistant(idClient));
		} catch (ClientException e) {
			mv.addObject("exception", e);
			mv.addObject("client", new Client());
		}
		mv.setViewName("employe/GestionClient/ChercherClient");
		return mv;
	}

}
