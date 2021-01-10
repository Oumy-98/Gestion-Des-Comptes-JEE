package cov.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import cov.security.UsersComptes;
import cov.validation.DateV;

@Entity
@Table(name = "client")
public class Client extends UserRole{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_Client")
	private int idClient;

	@NotBlank(message = "Veuillez entrer votre nom")
	@Column(name = "Nom")
	private String nom;

	@NotBlank(message = "Veuillez entrer votre prenom")
	@Column(name = "Prenom")
	private String prenom;

	@DateV
	@Column(name = "Date_Naissance")
	private String dateNaissance;

	@NotBlank(message = "Veuillez entrer votre telephone")
	@Column(name = "Telephone")
	private String telephone;

	@Email(message = " email de la forme exemple@exemple.com")
	@Column(name = "Email")
	private String email;

//	@Column(name = "Id_agence")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Id_agence", nullable=false)
	private Agence agence;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_user")
	private UsersComptes userCompte;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Compte.class, mappedBy = "client", cascade = CascadeType.ALL)
	private Set<Compte> lesComptesBancaire;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = DemandeCredit.class, mappedBy = "client", cascade = CascadeType.ALL)
	private Set<DemandeCredit> lesDemndes;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Credit.class, mappedBy = "client", cascade = CascadeType.ALL)
	private Set<Credit> lesCredit;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = NotificationClient.class, mappedBy = "client", cascade = CascadeType.ALL)
	private Set<NotificationClient> lesNotifications;

	public Set<NotificationClient> getLesNotifications() {
		return lesNotifications;
	}

	public void setLesNotifications(Set<NotificationClient> lesNotifications) {
		this.lesNotifications = lesNotifications;
	}

	public Set<DemandeCredit> getLesDemndes() {
		return lesDemndes;
	}

	public void setLesDemndes(Set<DemandeCredit> lesDemndes) {
		this.lesDemndes = lesDemndes;
	}

	public Set<Credit> getLesCredit() {
		return lesCredit;
	}

	public void setLesCredit(Set<Credit> lesCredit) {
		this.lesCredit = lesCredit;
	}

	public Client() {

	}

	public Client(String nom, String prenom, String dateNaissance, String tele, String email, Agence idAgence) {

		this.nom = nom;
		this.prenom = prenom;
		this.telephone = tele;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.agence = idAgence;

	}

	public Set<Compte> getLesComptesBancaire() {
		return lesComptesBancaire;
	}

	public void setLesComptesBancaire(Set<Compte> lesComptesBancaire) {
		this.lesComptesBancaire = lesComptesBancaire;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence idAgence) {
		this.agence = idAgence;
	}

	public UsersComptes getUserCompte() {
		return userCompte;
	}

	public void setUserCompte(UsersComptes idUser) {
		this.userCompte = idUser;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
//		int i=1;
//		StringBuffer laliste =  new StringBuffer();
//		String  num = "Compte N�: ";
//		laliste.append(num);
//		for(CompteBancaire comptae: lesComptesBancaire )
//		{
//			laliste.append(num);
//			laliste.append(i);
//			laliste.append(" "+comptae.toString()+"\n");
//			i++;
//		}

		return "Client [idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance="
				+ dateNaissance + ", telephone=" + telephone + ", email=" + email + ", Agence=" + agence
				+ ", userComptes=" + userCompte;
//		, nbr lesComptesBancaire=" + lesComptesBancaire.size()
//				+ ", nbr des demandes :" + lesDemndes.size() + ",nbr des Credits octroye =" + lesCredit.size() + " ]";
	}

}
