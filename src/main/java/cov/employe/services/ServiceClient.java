package cov.employe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cov.dao.AgenceRepository;
import cov.dao.ClientRepository;
import cov.dao.UsersComptesRepository;
import cov.entities.Agence;
import cov.entities.Client;
import cov.security.UsersComptes;
import cov.validation.ClientException;

@Service
@Transactional
public class ServiceClient {

	@Autowired
	private ClientRepository clientdao;

	@Autowired
	private UsersComptesRepository userDAO;
	
	@Autowired
	private AgenceRepository agencedao;
	
	

	public ServiceClient() {

	}

	public Client returnClient(Integer idclient) {
		Optional<Client> client = trouverClientById(idclient);
		if (!client.isPresent()) {
			Client clientwithDefaultId = new Client();
			clientwithDefaultId.setIdClient(0);
			return clientwithDefaultId;
		} else {
			return client.get();
		}
	}

	public List<Client> listeClient() {
		return clientdao.findAll();
	}

	public Optional<Client> trouverClientById(Integer idClient) {
		idClient = idClient == null ? 0 : idClient;
		System.out.println("id Client : " + idClient);
		return clientdao.findById(idClient);
	}

	public void creerNVClient(Client client) throws ClientException {
		try {
			UsersComptes user = client.getUserCompte();
			
			validerUserCompte(user);
			user.setRole("ROLE_CLIENT");
			client.setUserCompte(user);
			// dans cette ligne j'ai triché je n'ai qu'une agence dans ma db
			client.setAgence(agencedao.findById(1).orElse(new Agence()));
			clientdao.save(client);
		} catch (ClientException e) {
			throw new ClientException("ce nom d'utilisateur est déja existant");
		}
	}

	public void validerUserCompte(UsersComptes user) throws ClientException {
		Optional<UsersComptes> userV = userDAO.findByUsername(user.getUsername());
		if (userV.isPresent()) {
			throw new ClientException("ce nom d'utilisateur est déja utilisé");
		}
	}

	public Client clientExistant(Integer idClient) throws ClientException {
		if (idClient != null) {
			Client client = clientdao.findById(idClient).orElse(null);
			if (client == null) {
				throw new ClientException("ce client n'existe pas");
			} else
				return client;
		} else {
			return new Client();
		}
	}

}
