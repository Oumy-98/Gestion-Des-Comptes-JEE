package cov.employe.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cov.dao.ClientRepository;
import cov.dao.CompteBancaireRepository;
import cov.dao.HistoriqueCompteRepository;
import cov.entities.Client;
import cov.entities.Compte;
import cov.entities.HistoriqueCompte;
import cov.validation.CompteException;

@Service
@Transactional
public class ServiceCompteBancaire {

	@Autowired
	private CompteBancaireRepository comptedao;
	@Autowired
	private ClientRepository clientdao;
	@Autowired
	private HistoriqueCompteRepository operationsdao;

	public ServiceCompteBancaire() {

	}

	public Client envoyerClient(Integer idClient) {
		idClient = idClient == null ? 0 : idClient;
		Client client = clientdao.findById(idClient).orElse(new Client());
		return client;
	}

	public Client clientExistant(Integer idClient) throws CompteException {
		if (idClient != null) {
			Client client = clientdao.findById(idClient).orElse(null);
			if (client == null)
				throw new CompteException("Client inexistant !");
			else
				return client;
		} else {
			return new Client();
		}
	}

	public Compte compteExistant(Integer idCompte) throws CompteException {

		if (idCompte != null) {
			Compte compte = comptedao.findById(idCompte).orElse(null);
			if (compte == null)
				throw new CompteException("ce compte n'existe pas !");
			else
				return compte;
		} else {
			return new Compte();
		}
	}

	public Compte envoyerCompteBancaire(Integer idCompte) {
		idCompte = idCompte == null ? 0 : idCompte;
		Compte compte = comptedao.findById(idCompte).orElse(new Compte());
		return compte;
	}

	public void creerCompteAncienClient(Compte compte) {
		compte.setDateCreation(toDayMySQLDateString());
		System.out.println("le compte que je vais enregister est :" + compte);
		comptedao.save(compte);
		System.out.println("done ... !");
	}

	public void effectuerDepot(Integer idCompte, String libelle, String soldeS) throws CompteException {

		try {
			double solde = Double.parseDouble(soldeS);
			if (solde < 0)
				throw new CompteException("le montant à verser ne pas etre négatif");
			else {
				HistoriqueCompte operation = new HistoriqueCompte(this.toDayMySQLDateString(), libelle, 0, solde);
				Compte compte1 = comptedao.findById(idCompte).orElse(new Compte());
				double NVsolde = compte1.getSolde() + solde;
				compte1.setSolde(NVsolde);
				operation.setLecompte(compte1);
				operationsdao.save(operation);
				System.out.println("on veut ajouter cette somme : " + solde + "avec le titre : " + libelle
						+ " Pour le compte : " + compte1);
				System.out.println("Opertion a enregistré : " + operation);
			}
		} catch (Exception e) {
			throw new CompteException("impossible d'effectuer ce versement");
		}
	}

	public List<Compte> listeCompte() {
		return comptedao.findAll();
	}

	public List<HistoriqueCompte> lesOpertions(Integer idCompte) {
		idCompte = idCompte == null ? 0 : idCompte;
		return comptedao.findOperations(idCompte);
	}

	public String toDayMySQLDateString() {
		Date now = new Date();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String mysqlDateString = formatter.format(now);
		return mysqlDateString;
	}

}
