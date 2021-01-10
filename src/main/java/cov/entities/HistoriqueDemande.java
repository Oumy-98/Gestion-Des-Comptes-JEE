package cov.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class HistoriqueDemande implements Comparable<HistoriqueDemande> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAvancement;
	
	private String dateAvancement;
	
	private String etatAvant;
	
	private String etatApres;
	
	private String Commentaire;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_Demande")
	private DemandeCredit demande;

	public HistoriqueDemande() {
		
	}
	
	public HistoriqueDemande(String dateAvancement, String etatAvant, String etatApres, String commentaire,
			DemandeCredit demande) {
		super();
		this.dateAvancement = dateAvancement;
		this.etatAvant = etatAvant;
		this.etatApres = etatApres;
		Commentaire = commentaire;
		this.demande = demande;
	}

	public int getIdAvancement() {
		return idAvancement;
	}

	public void setIdAvancement(int idAvancement) {
		this.idAvancement = idAvancement;
	}

	public String getDateAvancement() {
		return dateAvancement;
	}

	public void setDateAvancement(String dateAvancement) {
		this.dateAvancement = dateAvancement;
	}

	public String getEtatAvant() {
		return etatAvant;
	}

	public void setEtatAvant(String etatAvant) {
		this.etatAvant = etatAvant;
	}

	public String getEtatApres() {
		return etatApres;
	}

	public void setEtatApres(String etatApres) {
		this.etatApres = etatApres;
	}

	public String getCommentaire() {
		return Commentaire;
	}

	public void setCommentaire(String commentaire) {
		Commentaire = commentaire;
	}

	public DemandeCredit getDemande() {
		return demande;
	}

	public void setDemande(DemandeCredit demande) {
		this.demande = demande;
	}

	@Override
	public int compareTo(HistoriqueDemande o) {
		if(this.idAvancement>o.getIdAvancement())
			return -1;
		else if(this.idAvancement<o.getIdAvancement())
			return 1;
		else 
			return 0;
	}

}
