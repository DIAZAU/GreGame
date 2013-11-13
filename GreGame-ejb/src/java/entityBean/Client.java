package entityBean;

import java.util.ArrayList;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;


@Entity
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findClient", query = "SELECT c FROM Client c WHERE c.login = :login and c.motDepasse = :password"),
    @NamedQuery(name = "Client.findByIdclient", query = "SELECT c FROM Client c WHERE c.idClient = :idClient"),
    @NamedQuery(name = "Client.findByNom", query = "SELECT c FROM Client c WHERE c.nomClient = :nomClient"),
    @NamedQuery(name = "Client.findByPrenom", query = "SELECT c FROM Client c WHERE c.prenomClient = :prenomClient"),
    @NamedQuery(name = "Client.findByLogin", query = "SELECT c FROM Client c WHERE c.login = :login"),
    @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.emailClient = :emailClient")})
public class Client implements Serializable {
    private int idClient;
    private String nomClient;
    private String prenomClient;
    private String emailClient;
    private Date dateNaissance;
    private String motDepasse;
    private String login;
    private Adresse adrClient;
    private Adresse adrLivraison;
    private ArrayList<Avis> listAvis;
    private ArrayList<Commande> listCommande;


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

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    public ArrayList<Avis> getListAvis() {
        return listAvis;
    }
    public void setListAvis(ArrayList<Avis> listAvis) {
        this.listAvis = listAvis;
    }

    @OneToMany (mappedBy = "client", cascade = CascadeType.REMOVE)
    public ArrayList<Commande> getListCommande() {
        return listCommande;
    }
    public void setListCommande(ArrayList<Commande> listCommande) {
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


    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getMotDepasse() {
        return motDepasse;
    }
    public void setMotDepasse(String motDepasse) {
        this.motDepasse = motDepasse;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
}
