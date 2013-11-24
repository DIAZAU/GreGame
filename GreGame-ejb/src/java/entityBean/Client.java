package entityBean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import java.io.Serializable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findClient", query = "SELECT c FROM Client c WHERE c.emailClient = :emailClient and c.motDepasse = :password"),
    @NamedQuery(name = "Client.findByIdclient", query = "SELECT c FROM Client c WHERE c.idClient = :idClient"),
    @NamedQuery(name = "Client.findByNom", query = "SELECT c FROM Client c WHERE c.nomClient = :nomClient"),
    @NamedQuery(name = "Client.findByPrenom", query = "SELECT c FROM Client c WHERE c.prenomClient = :prenomClient"),
    @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.emailClient = :emailClient")})
public class Client implements Serializable {
    private int idClient;
    private String nomClient;
    private String prenomClient;
    private String emailClient;
    private String motDepasse;
    private Adresse adrClient;
    private Adresse adrLivraison;
    private List<Avis> listAvis;
    private List<Commande> listCommande;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_FK" , referencedColumnName = "idAdresse")
    public Adresse getAdrClient() {
        return adrClient;
    }
    public void setAdrClient(Adresse adrClient) {
        this.adrClient = adrClient;
    }
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresseLivraison_FK" , referencedColumnName = "idAdresse")
    public Adresse getAdrLivraison() {
        return adrLivraison;
    }
    public void setAdrLivraison(Adresse adrLivraison) {
        this.adrLivraison = adrLivraison;
    }
    
    @XmlTransient
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "client")
    public List<Avis> getListAvis() {
        return listAvis;
    }
    public void setListAvis(List<Avis> listAvis) {
        this.listAvis = listAvis;
    }
    
    @XmlTransient
    @OneToMany (mappedBy = "client", cascade = CascadeType.REMOVE)
    public List<Commande> getListCommande() {
        return listCommande;
    }
    public void setListCommande(List<Commande> listCommande) {
        this.listCommande = listCommande;
    }
    @NotNull
    public String getNomClient() {
        return nomClient;
    }
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    @NotNull
    public String getPrenomClient() {
        return prenomClient;
    }
    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }


    public String getEmailClient() {
        return emailClient;
    }
    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }


    public String getMotDepasse() {
        return motDepasse;
    }
    public void setMotDepasse(String motDepasse) {
        this.motDepasse = motDepasse;
    }

}
