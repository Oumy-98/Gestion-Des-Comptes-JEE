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
public class NotificationClient extends Notification implements Comparable<NotificationClient>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNotification;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_demande")
	private DemandeCredit demande;
	
	private String dateNotifcations;
	
	private boolean isRead;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_client")
	private Client client;
	
	public NotificationClient() {
		
	}
	
	public NotificationClient(String dateNotifcations, boolean isRead, Client client,
			DemandeCredit demande) {
		super();
		
		this.dateNotifcations = dateNotifcations;
		this.isRead = isRead;
		this.client = client;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public DemandeCredit getDemande() {
		return demande;
	}

	public void setDemande(DemandeCredit demande) {
		this.demande = demande;
	}
	
	@Override
	public int compareTo(NotificationClient o) {
		if(this.idNotification>o.getIdNotification())
			return -1;
		else if(this.idNotification<o.getIdNotification())
			return 1;
		else 
			return 0;
	}
}
