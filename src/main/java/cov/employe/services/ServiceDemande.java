package cov.employe.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cov.dao.ClientRepository;
import cov.dao.CreditRepository;
import cov.dao.DemandeCreditRepository;
import cov.dao.HistoriqueDemandeRepository;
import cov.dao.EmployeRepository;
import cov.dao.NotificationClientRepository;
import cov.dao.NotificationEmployeRepository;
import cov.entities.Client;
import cov.entities.Credit;
import cov.entities.DemandeCredit;
import cov.entities.Employe;
import cov.entities.HistoriqueDemande;
import cov.entities.NotificationClient;
import cov.entities.NotificationEmploye;
import cov.validation.DemandeException;

@Service
public class ServiceDemande {

	@Autowired
	private DemandeCreditRepository demandedao;

	@Autowired
	private HistoriqueDemandeRepository histoDemandeDAO;

	@Autowired
	private CreditRepository creditdao;
	
	@Autowired
	private ClientRepository clientDAO;
	
	@Autowired
	private NotificationClientRepository notifClientDAO;
	
	@Autowired
	private NotificationEmployeRepository notifEmployeDAO;
	
	@Autowired
	private EmployeRepository employeDAO;
	
	
	
	

	public List<DemandeCredit> listeDemandes() {
		System.out.println("lamethode findall est appelé");
		return demandedao.findAll();
	}
	
	public Client clientExistant(Integer idclient)throws DemandeException
	{
		Client client = clientDAO.findById(idclient).orElse(null);
		if(client == null)
			throw new DemandeException("ce client n'existe pas");
		else return client;
	}

	public DemandeCredit demandeExistant(Integer idDemande) throws DemandeException {
		if (idDemande != null) {
			DemandeCredit demande = demandedao.findById(idDemande).orElse(null);
			if (demande == null)
				throw new DemandeException("demande n'existe pas !");
			else
				return demande;
		} else {

			return new DemandeCredit();
		}
	}

	public DemandeCredit envoyerDemande(String id) {
		int idDemande = id == null ? 0 : Integer.parseInt(id);
		System.out.println("envoyer demande called");
		DemandeCredit demande = demandedao.findById(idDemande).orElse(null);
		System.out.println("la demande envoyer est : " + demande);
		return demande;
	}

	public void enregistrerAvancement(String com, String etat, Integer id) throws DemandeException {
		int etatInt = Integer.parseInt(etat);
		DemandeCredit demande = demandeExistant(id);
		if (etatInt == 0)
			throw new DemandeException("veuillez choisir l'état suivante");
		else {
			String etatActuel = demande.getEtatDemande();
			demande.setEtatDemande(etat);
			HistoriqueDemande avanceDemande = new HistoriqueDemande(toDayMySQLDateString(), etatActuel, etat, com, demande);
			NotificationClient notification = new NotificationClient(toDayMySQLDateString(),false,demande.getClient(),demande);
			histoDemandeDAO.save(avanceDemande);
			notifClientDAO.save(notification);
			if (etatInt > 3) {
				Credit credit = new Credit(toDayMySQLDateString(), demande.getMontantDemande(), demande.getMontantDemande(), demande.getClient());
				creditdao.save(credit);
			}
		}
	}

	public List<HistoriqueDemande> listAvances(Integer id) {
		int idDemande = id == null ? 0 : id;
		return demandedao.findAvances(idDemande);
	}

	public String toDayMySQLDateString() {
		Date now = new Date();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String mysqlDateString = formatter.format(now);
		return mysqlDateString;
	}
	
	public void enregistrerDemande(DemandeCredit demande,Client client)
	{
		demande.setDateDemande(toDayMySQLDateString());
		demande.setEtatDemande("1");
		demande.setClient(client);
		System.out.println("demande a enrigistré est : "+demande);
		demandedao.save(demande);
		for(Employe employe : employeDAO.findAll())
		{
			NotificationEmploye notif = new NotificationEmploye(demande,toDayMySQLDateString(),false,employe);
			notifEmployeDAO.save(notif);
		}
		System.out.println("");
	}
	
}
