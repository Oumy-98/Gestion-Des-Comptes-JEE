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
public class HistoriqueCredit implements Comparable<HistoriqueCredit> {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOperation;
	
	private String dateOperation;
	
	private String libelle;
	


	private double montant;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "IdCredit")
	private Credit credit;
	
	
	public HistoriqueCredit() {
		
	}
	
	public HistoriqueCredit(String dateOperation, String libelle, Credit credit, double montant) {
		super();
		this.dateOperation = dateOperation;
		this.montant=montant;
		this.libelle = libelle;
		this.credit = credit;
	}


	public int getIdOperation() {
		return idOperation;
	}

	public void setIdOperation(int idOperation) {
		this.idOperation = idOperation;
	}

	public String getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(String dateOperation) {
		this.dateOperation = dateOperation;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}
	
	@Override
	public String toString() {
		return "CreditHistorique [idOperation=" + idOperation + ", dateOperation=" + dateOperation + ", libelle="
				+ libelle + ",montant="+montant+", credit=" + credit + "]";
	}

	@Override
	public int compareTo(HistoriqueCredit o) {
		if(this.idOperation>o.getIdOperation())
			return -1;
		else if(this.idOperation<o.getIdOperation())
			return 1;
		else 
			return 0;
	}
}
