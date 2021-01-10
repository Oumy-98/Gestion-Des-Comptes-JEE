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
public class NotificationEmploye extends Notification implements Comparable<NotificationEmploye>{

	@Override
	public String toString() {
		return "NotificationEmploye [idNotification=" + idNotification + ", dateNotifcations=" + dateNotifcations
				+ ", isRead=" + isRead +", demande=" + demande + ", employe=" + employe + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNotification;

	private String dateNotifcations;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_demande")
	private DemandeCredit demande;

	private boolean isRead;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_employe")
	private Employe employe;

	public NotificationEmploye() {

	}

	public NotificationEmploye(DemandeCredit demande,String dateNotifcations, boolean isRead, Employe employe) {
		super();
		this.demande = demande;
		this.dateNotifcations = dateNotifcations;
		this.isRead = isRead;
		this.employe = employe;
	}

	public DemandeCredit getDemande() {
		return demande;
	}

	public void setDemande(DemandeCredit demande) {
		this.demande = demande;
	}

	public int getIdNotification() {
		return idNotification;
	}

	public void setIdNotification(int idNotification) {
		this.idNotification = idNotification;
	}

	public String getDateNotifcations() {
		return dateNotifcations;
	}

	public void setDateNotifcations(String dateNotifcations) {
		this.dateNotifcations = dateNotifcations;
	}

	public boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	@Override
	public int compareTo(NotificationEmploye o) {
		if(this.idNotification>o.getIdNotification())
			return -1;
		else if(this.idNotification<o.getIdNotification())
			return 1;
		else 
			return 0;
	}
}
