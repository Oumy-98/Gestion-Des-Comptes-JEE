package cov.client;

import javax.servlet.http.HttpSession;
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
import cov.entities.Compte;
import cov.entities.DemandeCredit;
import cov.validation.ClientException;
import cov.validation.DemandeException;

@Controller
public class ControllerForClient {

	@Autowired
	private ServiceForClient serviceclient;

	@Autowired
	private ServiceDemande serviceDemande;

//	@Autowired
//	private ServiceCompteBancaire serviceCompte;

	@GetMapping("/ProfilC")
	public ModelAndView profil(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("client", serviceclient.returnClientfromSession(session));
		mv.setViewName("client/ProfilClient");
		return mv;
	}

	@GetMapping("/MesComptes")
	public ModelAndView mescomptes(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("client", serviceclient.returnClientfromSession(session));
		mv.setViewName("client/Compte/MesComptes");
		return mv;
	}

	@GetMapping("/CompteDetails")
	public ModelAndView detailscompte(@RequestParam(name = "idCompte", required = false) Integer idCompte) {
		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("compte", serviceclient.CompteById(idCompte));
		} catch (ClientException e) {
			System.out.println(e.getMessage());
		}
		mv.setViewName("client/Compte/CompteDetails");
		return mv;
	}

	@GetMapping("/EffectuerVirement")
	public ModelAndView creervirement(@RequestParam(name = "idCompte", required = false) Integer idCompte,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("compte", serviceclient.validerCompteExpediteur(idCompte, session));
			System.out.println("effectuer virment");
		} catch (ClientException e) {
			mv.addObject("exception", e);
			System.out.println(e.getMessage());
			mv.addObject("compte", new Compte());
		}
		System.out.println("effectuer virment");
		mv.setViewName("client/Compte/Virement");
		return mv;
	}

	@PostMapping("/ValiderVirement")
	public ModelAndView validervirment(@RequestParam(name = "montant") double montant,
			@RequestParam(name = "idCompte") Integer idCompteExpediteur,
			@RequestParam(name = "idCompteDestinataire") Integer idCompteDestinataire) {
		ModelAndView mv = new ModelAndView();
		RedirectView redirect = new RedirectView();
		try {
			System.out.println("valider virment");
			serviceclient.enregistrerVirment(montant, serviceclient.CompteById(idCompteExpediteur),
					serviceclient.CompteById(idCompteDestinataire));
			redirect.setUrl("/EffectuerVirement?success=1&msg=Operation+avec+success+!&idCompte="+idCompteExpediteur);
		} catch (ClientException e) {
			redirect.setUrl("/EffectuerVirement?success=0&msg="+e.getMessage()+"&idCompte="+idCompteExpediteur);
		}
		
		mv.setView(redirect);
		return mv;
	}

	@GetMapping("/MesCredits")
	public ModelAndView mescredits(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("client", serviceclient.returnClientfromSession(session));
		mv.setViewName("client/Credit/MesCredits");
		return mv;
	}

	@GetMapping("/CreditDetails")
	public ModelAndView detailscredit(@RequestParam(name = "idCredit", required = false) Integer idCredit) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("credit", serviceclient.CreditById(idCredit));
		mv.setViewName("client/Credit/CreditDetails");
		return mv;
	}

	@GetMapping("/MesDemandes")
	public ModelAndView mesdemandes(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("client", serviceclient.returnClientfromSession(session));
		mv.setViewName("client/Demande/MesDemandes");
		return mv;
	}

	@GetMapping("/DemandeDetails")
	public ModelAndView detailsdemandes(@RequestParam(name = "idDemande", required = false) Integer idDemande) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("demande", serviceclient.DemandeById(idDemande));
		mv.setViewName("client/Demande/DemandeDetails");
		return mv;
	}

	@GetMapping("/createDemande")
	public ModelAndView creerDemande(@RequestParam(name = "idDemande", required = false) Integer idDemande,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		DemandeCredit demande = new DemandeCredit("", 0, "", serviceclient.returnClientfromSession(session));
		System.out.println(demande);
		mv.addObject("demande", demande);
		mv.setViewName("client/Demande/CreerDemande");
		return mv;
	}

	@PostMapping("/ValiderDemande")
	public ModelAndView validerDemande(@Valid @ModelAttribute(name = "demande") DemandeCredit demande,
			BindingResult binding) {
		ModelAndView mv = new ModelAndView();
		RedirectView redirect = new RedirectView();
		System.out.println(demande);
		if (binding.hasErrors()) {
			mv.setViewName("client/Demande/CreerDemande");
			return mv;
		}
		try {
			Client client = serviceDemande.clientExistant(demande.getClient().getIdClient());
			serviceDemande.enregistrerDemande(demande, client);
			redirect.setUrl("/createDemande?success=1&msg=Demande+cree+avec+success+!");
		} catch (DemandeException e) {
			redirect.setUrl("/createDemande?error=" + e.getMessage());
		}
		mv.setView(redirect);
		return mv;
	}

	@GetMapping("/mesNotifications")
	public ModelAndView mesNotifs(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("notifications", serviceclient.notificationsByClientWithinSession(session));
		mv.setViewName("client/Notifications/Notifications");
		serviceclient.notificationEstVu(session);
		return mv;
	}
	
	

}
