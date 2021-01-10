package cov.employe.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cov.employe.services.ServiceCompteBancaire;
import cov.entities.Client;
import cov.entities.Compte;
import cov.validation.CompteException;

@Controller
public class ControllerGestionCompte {

	@Autowired
	private ServiceCompteBancaire serviceCompte;

	@GetMapping("/CreationCompte")
	public ModelAndView FormCreationCompte(@RequestParam(name = "client.idClient", required = false) Integer idClient,
			@RequestParam(name = "idCompte", required = false) Integer idCompte) {
		ModelAndView mv = new ModelAndView();
		Compte compte;
		if (idCompte != null) {
			//condition pour la modification
			compte = serviceCompte.envoyerCompteBancaire(idCompte);
		} else {
			//nouvelle creation d'un compte
			compte = new Compte();
			try {
			Client client = serviceCompte.clientExistant(idClient);
			compte.setClient(client);
			}catch(CompteException e)
			{
				mv.addObject("exception", e);
			}
			
		}
		mv.addObject("compte", compte);
		mv.setViewName("employe/GestionCompte/CreationCompteOldClient");
		return mv;
	}

	@GetMapping("/ValiderCompte")
	public ModelAndView creerCompteAncienClient(@Valid @ModelAttribute Compte compte, BindingResult bind) {
		ModelAndView mv = new ModelAndView();
		System.out.println("creation compte : "+compte);
		if (bind.hasErrors()) {
			System.out.println("has error !");
			mv.setViewName("employe/GestionCompte/CreationCompteOldClient");
			mv.addObject("compte", compte);
			return mv;
		} else {
			System.out.println(compte);
			serviceCompte.creerCompteAncienClient(compte);
			System.out.println("le Compte que je vais crée : " + compte);
			RedirectView redirect = new RedirectView();
			redirect.setUrl("/listeComptes");
			mv.setView(redirect);
			return mv;
		}
	}

	@GetMapping("/listeComptes")
	public ModelAndView listeComptes(@ModelAttribute Compte compte) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employe/GestionCompte/listeComptes");
		mv.addObject("comptes", serviceCompte.listeCompte());
		return mv;
	}

	@PostMapping("/ValiderDepot")
	public ModelAndView effectuerDepot(@RequestParam(name = "idCompte") Integer idCompte,
			@RequestParam(name = "depot") String depot, @RequestParam(name = "libelle") String libelle) {
		ModelAndView mv = new ModelAndView();
		RedirectView re = new RedirectView();
		try {
			serviceCompte.effectuerDepot(idCompte, libelle, depot);
			mv.addObject("operations", serviceCompte.lesOpertions(idCompte));
			mv.addObject("compte", serviceCompte.compteExistant(idCompte));
			re.setUrl("/UnDepot?idCompte="+idCompte+"&success=1&msg=Operation+avec+Success");
		} catch (CompteException e) {
			mv.addObject("exception", e);
			re.setUrl("/UnDepot?idCompte="+idCompte+"&error="+e.getMessage()+"&success=0&msg=Operation+a+Echoue+!");
		}
		
		mv.setView(re);
		return mv;
	}

	@GetMapping("/lesOperations")
	public ModelAndView lesOperations(@RequestParam(required = false) Integer idCompte) {
		ModelAndView mv = new ModelAndView();
		System.out.println("Id compte passé : " + idCompte);
		Compte compteA = serviceCompte.envoyerCompteBancaire(idCompte);
		mv.addObject("compte", compteA);
		mv.addObject("operations", serviceCompte.lesOpertions(idCompte));
		mv.setViewName("employe/GestionCompte/listeOperations");
		return mv;
	}

	@GetMapping("/ChercherCompte")
	public ModelAndView chercherCompte(@RequestParam(name = "idCompte", required = false) Integer idCompte) {
		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("operations", serviceCompte.lesOpertions(idCompte));
			mv.addObject("compte", serviceCompte.compteExistant(idCompte));
		} catch (CompteException e) {
			mv.addObject("exception", e);
			mv.addObject("compte", serviceCompte.envoyerCompteBancaire(idCompte));
		}
		mv.setViewName("employe/GestionCompte/ChercherCompte");
		return mv;
	}

	@GetMapping("/UnDepot")
	public ModelAndView UnDEPot(@RequestParam(name = "idCompte", required = false) Integer idCompte) {
		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("operations", serviceCompte.lesOpertions(idCompte));
			mv.addObject("compte", serviceCompte.compteExistant(idCompte));
		} catch (CompteException e) {
			mv.addObject("exception", e);
			mv.addObject("compte", serviceCompte.envoyerCompteBancaire(idCompte));
		}
		mv.setViewName("employe/GestionCompte/UnDepot");
		return mv;
	}

}
