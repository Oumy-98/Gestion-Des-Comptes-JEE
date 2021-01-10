package cov.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cov.dao.ClientRepository;
import cov.dao.CompteBancaireRepository;
import cov.dao.CreditRepository;
import cov.dao.DemandeCreditRepository;
import cov.dao.HistoriqueCompteRepository;
import cov.dao.NotificationClientRepository;
import cov.entities.Client;
import cov.entities.Compte;
import cov.entities.Credit;
import cov.entities.DemandeCredit;
import cov.entities.HistoriqueCompte;
import cov.entities.NotificationClient;
import cov.validation.ClientException;

@Service
@Transactional
public class ServiceForClient {

	@Autowired
	private CompteBancaireRepository comptedao;

	@Autowired
	private ClientRepository clientdao;

	@Autowired
	private HistoriqueCompteRepository historiqueDAO;

	@Autowired
	private CreditRepository creditdao;

	@Autowired
	private DemandeCreditRepository demandeDAO;

	@Autowired
	private NotificationClientRepository notifClientDAO;

	public Client returnClientfromSession(HttpSession session) {
		Client client = (Client) session.getAttribute("sessionUserInfos");
		return clientdao.findById(client.getIdClient()).get();
	}

	public Compte CompteById(Integer idCompte) throws ClientException {
		Compte compte = comptedao.findById(idCompte).orElse(null);
		if (compte == null)
			throw new ClientException("Ce compte n'existe pas");
		else
			return compte;
	}

	public Credit CreditById(Integer idCredit) {
		Credit credit = creditdao.findById(idCredit).orElse(new Credit());
		return credit;
	}

	public DemandeCredit DemandeById(Integer idDemande) {
		DemandeCredit demande = demandeDAO.findById(idDemande).orElse(new DemandeCredit());
		return demande;
	}

	public List<NotificationClient> notificationsByClientWithinSession(HttpSession session) {
		Client client = returnClientfromSession(session);
		List<NotificationClient> notifs = clientdao.findLesNotifications(client.getIdClient());
		Collections.sort(notifs);
		return notifs;
	}

	public void notificationEstVu(HttpSession session) {
		notificationsByClientWithinSession(session).forEach(notif -> {
			notif.setIsRead(true);
			notifClientDAO.save(notif);
		});
		session.removeAttribute("nbrNotifNonVu");
		session.setAttribute("nbrNotifNonVu", 0);
	}

	public Compte validerCompteExpediteur(Integer idCompte, HttpSession session) throws ClientException {
		System.out.println("valider compte");
		if (idCompte != null) {
			System.out.println("c'est pas null");
			Client client = returnClientfromSession(session);
			Compte compte = CompteById(idCompte);
			List<Compte> lesComptesduClient = clientdao.findLesComptesBancaires(client.getIdClient());
			if (!lesComptesduClient.contains(compte))
				throw new ClientException("Vous ne possédez aucun compte avec le Numéro :" + idCompte);
			else
				return compte;
		} else
			return new Compte();
	}

	public void enregistrerVirment(double montant, Compte compteExpediteur, Compte compteDestinataire)
			throws ClientException {
		if (compteExpediteur.getIdCompte() == compteDestinataire.getIdCompte())
			throw new ClientException("le compte destinaire doit etre different du compte expediteur !");
		else if (compteExpediteur.getSolde() < montant)
			throw new ClientException("Ce montant dépasse votre solde");
		else if (montant < 0)
			throw new ClientException("Ce transfert est impossible");
		else {
			System.out.println("operation logique ");
			saveOperationforDestinataire(montant, compteExpediteur, compteDestinataire);
			saveOperationforExpediteur(montant, compteExpediteur, compteDestinataire);
		}
	}

	public void saveOperationforExpediteur(double montant, Compte compteExpediteur, Compte compteDestinataire) {
		// le debit de le compte expéditeur
		double montantfinal = compteExpediteur.getSolde() - montant;
		compteExpediteur.setSolde(montantfinal);
		// enregistremnt de l'opération pour le compte expéditeur
		String libelleExpediteur = "virement vers Compte N°: " + compteDestinataire.getIdCompte() + " de "
				+ compteDestinataire.getClient().getNom() + " " + compteDestinataire.getClient().getPrenom();
		HistoriqueCompte operationExpediteur = new HistoriqueCompte(toDayMySQLDateString(), libelleExpediteur, montant,
				0);
		operationExpediteur.setLecompte(compteExpediteur);
		System.out.println("Expediteur :   " + operationExpediteur);
		historiqueDAO.save(operationExpediteur);
		System.out.println("operation expediteur savved !");
	}

	public void saveOperationforDestinataire(double montant, Compte compteExpediteur, Compte compteDestinataire) {
		// le credit de le compte destinataire
		double montantfinal = compteDestinataire.getSolde() + montant;
		compteDestinataire.setSolde(montantfinal);
		// Enregistremnt de l'opération pour le compte destinataire
		String libelleDestinataire = "virement depuis " + compteExpediteur.getClient().getNom().toUpperCase() + " "
				+ compteExpediteur.getClient().getPrenom().toUpperCase();
		HistoriqueCompte operationDestinataire = new HistoriqueCompte(toDayMySQLDateString(), libelleDestinataire, 0,
				montant);
		operationDestinataire.setLecompte(compteDestinataire);
		System.out.println("destinataire :  " + operationDestinataire);
		historiqueDAO.save(operationDestinataire);
		System.out.println("operation destinataire saved !");
	}
	
	public Integer nbrNotificationNonVu(Integer IdClient) {
		List<NotificationClient> notifsTotal = clientdao.findLesNotifications(IdClient);
		int nbrNonVu = 0;
		for (NotificationClient notif : notifsTotal) {
			if (!notif.getIsRead()) {
				nbrNonVu++;
			}
		}
		return nbrNonVu;
	}
	

	public String toDayMySQLDateString() {
		Date now = new Date();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String mysqlDateString = formatter.format(now);
		return mysqlDateString;
	}

}
