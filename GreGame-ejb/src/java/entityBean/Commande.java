package entityBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

import javax.validation.constraints.NotNull;

@Entity
public class Commande implements Serializable {
	
    private int idCommande;
    private Date dateCommande;
    private Client client;
    private ArrayList<LigneCommande> listCommande;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getIdCommande() {
            return idCommande;
    }
    public void setIdCommande(int idCommande) {
            this.idCommande = idCommande;
    }

    @ManyToOne(optional = false)
    @JoinColumn (name = "client_FK", referencedColumnName = "idClient")
    public Client getClient() {
            return client;
    }
    public void setClient(Client clientFK) {
            this.client = clientFK;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn (name = "commande")
    public ArrayList<LigneCommande> getListCommande() {
            return listCommande;
    }
    public void setListCommande(ArrayList<LigneCommande> listCommande) {
            this.listCommande = listCommande;
    }
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDateCommande() {
            return dateCommande;
    }
    public void setDateCommande(Date dateCommande) {
            this.dateCommande = dateCommande;
    }

}
