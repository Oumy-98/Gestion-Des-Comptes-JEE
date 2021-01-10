package cov.employe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cov.employe.services.ServiceCredit;
import cov.entities.Credit;
import cov.validation.CreditException;

@Controller
public class ControllerGestionCredit {

	@Autowired
	private ServiceCredit serviceCredit;

	@GetMapping("/listeCredits")
	public ModelAndView listeCredit() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("credits", serviceCredit.listeCredits());
		mv.setViewName("employe/GestionCredit/listeCredits");
		return mv;
	}

	@GetMapping("/PayerTraite")
	public ModelAndView FormPayerTraite(@RequestParam(name = "idCredit", required = false) Integer idCredit) {
		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("credit", serviceCredit.creditExistant(idCredit));
			mv.addObject("operations", serviceCredit.listeOperations(idCredit));
		} catch (CreditException e) {
			mv.addObject("exception", e);
			mv.addObject("credit", new Credit());
		}
		mv.setViewName("employe/GestionCredit/PayerTraite");
		return mv;
	}

	@PostMapping("/ValiderTraite")
	public ModelAndView validerTraite(@RequestParam(name = "idCredit") Integer idCredit,
			@RequestParam(name = "libelle") String libelle, @RequestParam(name = "montant") String montant) {
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView();
		try {
			serviceCredit.payerTraite(idCredit, libelle, montant);
			rv.setUrl("/PayerTraite?idCredit="+ idCredit+"&success=1&msg=Operation+effectue+avec+success");
		} catch (CreditException e) {
			rv.setUrl("/PayerTraite?idCredit=" + idCredit + "&error=" + e.getMessage()+"&success=0&msg=Operation+a+echoue+!");
		}
		mv.setView(rv);
		return mv;
	}


	@GetMapping("/ChercherCredit")
	public ModelAndView chercherCredit(@RequestParam(name = "idCredit", required = false) Integer idCredit) {
		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("credit", serviceCredit.creditExistant(idCredit));
			mv.addObject("operations", serviceCredit.listeOperations(idCredit));
		} catch (CreditException e) {
			mv.addObject("exception", e);
			mv.addObject("credit", new Credit());
		}
		mv.setViewName("employe/GestionCredit/ChercherCredit");
		return mv;
	}

}
