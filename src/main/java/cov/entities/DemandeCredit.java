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
import javax.persistence.Table;

import cov.validation.MontantV;

@Entity
@Table(name="demandes_credit")
public class DemandeCredit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id_demande")
	private int id_demande;
	
	@Column(name="Date_demande")
	private String dateDemande;
	
	private String commentaire;
	
	@MontantV
	@Column(name="Montant_demande")
	private double montantDemande;
	
	@Column(name="Etat_demande")
	private String etatDemande;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Id_Client", nullable=false)
	private Client client;
	
	@OneToMany(fetch = FetchType.EAGER, targetEntity = HistoriqueDemande.class, mappedBy = "demande", cascade = CascadeType.ALL)
	private Set<HistoriqueDemande> lesAvances; 
	
	@OneToMany(fetch = FetchType.EAGER, targetEntity = NotificationEmploye.class, mappedBy = "demande", cascade = CascadeType.ALL)
	private Set<NotificationEmploye> lesNotifications; 

	public Set<NotificationEmploye> getLesNotifications() {
		return lesNotifications;
	}

	public void setLesNotifications(Set<NotificationEmploye> lesNotifications) {
		this.lesNotifications = lesNotifications;
	}

	public DemandeCredit() {}
	
	public DemandeCredit( String dateDemande, long montantDemande, String etatDemande,Client client) {
		super();
		this.client=client;
		this.dateDemande = dateDemande;
		this.montantDemande = montantDemande;
		this.etatDemande = etatDemande;	
	}

	public int getId_demande() {
		return id_demande;
	}

	public void setId_demande(int id_demande) {
		this.id_demande = id_demande;
	}

	public String getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}

	public double getMontantDemande() {
		return montantDemande;
	}

	public void setMontantDemande(double montantDemande) {
		this.montantDemande = montantDemande;
	}

	public String getEtatDemande() {
		return etatDemande;
	}

	public void setEtatDemande(String etatDemande) {
		this.etatDemande = etatDemande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client idClient) {
		this.client = idClient;
	}

	
	
	public Set<HistoriqueDemande> getLesAvances() {
		return lesAvances;
	}

	public void setLesAvances(Set<HistoriqueDemande> lesAvances) {
		this.lesAvances = lesAvances;
	}

	@Override
	public String toString() {
		return "DemandeCredit [id_demande=" + id_demande + ", dateDemande=" + dateDemande + ", montantDemande="
				+ montantDemande + ", etatDemande=" + etatDemande + ", Client=" + client + " ]";
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	
	
	

}
