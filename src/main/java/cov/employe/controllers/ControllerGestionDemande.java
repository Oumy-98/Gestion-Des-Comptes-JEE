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

import cov.employe.services.ServiceDemande;
import cov.entities.Client;
import cov.entities.DemandeCredit;
import cov.validation.DemandeException;

@Controller
public class ControllerGestionDemande {

	@Autowired
	private ServiceDemande serviceDemande;

	@GetMapping("/listeDemandes")
	public ModelAndView listeDemandes() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("demandes", serviceDemande.listeDemandes());
		mv.setViewName("employe/GestionDemande/listeDemandes");
		return mv;
	}

	@GetMapping("/creerDemande")
	public ModelAndView creerDemande(@RequestParam(name = "idDemande", required = false) Integer idDemande) {
		ModelAndView mv = new ModelAndView();
		DemandeCredit demande = new DemandeCredit("", 0, "", new Client());
		System.out.println(demande);
		mv.addObject("demande", demande);
		mv.setViewName("employe/GestionDemande/CreerDemande");
		return mv;
	}

	@PostMapping("/CreerDemandeV")
	public ModelAndView validerDemande(@Valid @ModelAttribute(name="demande") DemandeCredit demande, BindingResult binding) {
		ModelAndView mv = new ModelAndView();
		RedirectView redirect = new RedirectView();
		if (binding.hasErrors()) {
			mv.setViewName("employe/GestionDemande/CreerDemande");
			return mv;
		}
		try {
			Client client = serviceDemande.clientExistant(demande.getClient().getIdClient());
			serviceDemande.enregistrerDemande(demande, client);
			redirect.setUrl("/creerDemande?idDemande="+demande.getId_demande()+"&success=1&msg=Demande+cree+avec+success");
		} catch (DemandeException e) {
			redirect.setUrl("/creerDemande?idDemande="+demande.getId_demande()+"error="+e.getMessage()+"success=0&msg=la+creation+de+la+demande+a+echoue+!");
		}
		mv.setView(redirect);
		return mv;
	}

	@GetMapping("/avancerEtat")
	public ModelAndView avancerEtat(@RequestParam(name = "idDemande", required = false) Integer idDemande) {
		ModelAndView mv = new ModelAndView();
		try {
			System.out.println("avancer dEmande");
			mv.addObject("demande", serviceDemande.demandeExistant(idDemande));
			mv.addObject("lesAvances", serviceDemande.listAvances(idDemande));
			System.out.println("demande trouvé");
		} catch (DemandeException e) {
			mv.addObject("exception", e);
			mv.addObject("demande", new DemandeCredit());
			System.out.println("demande n'est pas trouvé");
		}
		mv.setViewName("employe/GestionDemande/AvancerDemande");
		return mv;
	}

	@PostMapping("/validerEtat")
	public ModelAndView ValiderEtat(@RequestParam(name = "commentaire") String commentaire,
			@RequestParam(name = "etat") String etat, @RequestParam(name = "idDemande") Integer idDemande) {
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		try {
			serviceDemande.enregistrerAvancement(commentaire, etat, idDemande);
			rv.setUrl("/avancerEtat?idDemande=" + idDemande+"&success=1&msg=Evolution+enregistre+avec+success");
		} catch (DemandeException e) {
			rv.setUrl("/avancerEtat?idDemande=" + idDemande + "&error=" + e.getMessage()+"success=0&msg=la+creation+de+la+demande+a+echoue+!");
		}
		mv.setView(rv);
		return mv;
	}

	@GetMapping("/chercherDemande")
	public ModelAndView chercherDemande(@RequestParam(name = "idDemande", required = false) Integer idDemande) {
		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("demande", serviceDemande.demandeExistant(idDemande));
			mv.addObject("lesAvances", serviceDemande.listAvances(idDemande));
			System.out.println("demande trouvé");
		} catch (DemandeException e) {
			mv.addObject("exception", e);
			mv.addObject("demande", new DemandeCredit());
			System.out.println("demande n'est pas trouvé");
		}
		mv.setViewName("employe/GestionDemande/ChercherDemande");
		return mv;
	}

}
