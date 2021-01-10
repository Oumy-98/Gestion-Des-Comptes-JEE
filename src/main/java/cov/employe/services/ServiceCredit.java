package cov.employe.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cov.dao.CreditRepository;
import cov.dao.HistoriqueCreditRepository;
import cov.entities.Credit;
import cov.entities.HistoriqueCredit;
import cov.validation.CreditException;

@Service
public class ServiceCredit {

	@Autowired
	private CreditRepository creditdao;
	@Autowired
	private HistoriqueCreditRepository histoCreditdao;

	public List<Credit> listeCredits() {
		return creditdao.findAll();
	}

	public List<HistoriqueCredit> listeOperations(Integer idCredit) {
		return creditdao.findOperations(idCredit);
	}

	public Credit creditExistant(Integer idCredit) throws CreditException {
		if (idCredit != null) {

			Credit credit = creditdao.findById(idCredit).orElse(null);
			if (credit == null)
				throw new CreditException("ce credit n'exite pas !");
			else
				return credit;
		} else {
			return new Credit();
		}

	}

	public Credit findParId(Integer id) {
		return creditdao.findById(id).get();
	}

	public void payerTraite(Integer idCredit, String libelle, String montant) throws CreditException {
		Credit creditV = findParId(idCredit);
		try {
			double montantV = Double.parseDouble(montant);
			if (montantV < 0)
				throw new CreditException("Merci de mettre une valeur positive");
			else {
				double NVmontantRestant = creditV.getMontantRestant() - montantV;
				creditV.setMontantRestant(NVmontantRestant);
				HistoriqueCredit operation = new HistoriqueCredit(toDayMySQLDateString(), libelle, creditV, montantV);
				histoCreditdao.save(operation);
				System.out.println("operation enregistré : " + operation);
			}
		} catch (NumberFormatException e) {
			throw new CreditException("impossible de payer la traite !");
		}
	}

	public String toDayMySQLDateString() {
		Date now = new Date();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String mysqlDateString = formatter.format(now);
		return mysqlDateString;
	}

}
