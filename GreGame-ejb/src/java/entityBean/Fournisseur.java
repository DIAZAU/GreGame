package entityBean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javax.validation.constraints.NotNull;


@Entity
public class Fournisseur implements Serializable {
    private List<Produit> produits;
    private int idFournisseur;
    private String nomFournisseur;
    private String emailFournisseur;
    private long telFournisseur;
    private Adresse adrFournisseur;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getIdFournisseur() {
        return idFournisseur;
    }
    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    @NotNull
    public String getNomFournisseur() {
        return nomFournisseur;
    }
    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    @NotNull
    public String getEmailFournisseur() {
        return emailFournisseur;
    }
    public void setEmailFournisseur(String emailFournisseur) {
        this.emailFournisseur = emailFournisseur;
    }

    @NotNull
    public long getTelFournisseur() {
        return telFournisseur;
    }
    public void setTelFournisseur(long telFournisseur) {
        this.telFournisseur = telFournisseur;
    }
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_FK" , referencedColumnName = "idAdresse")
    public Adresse getAdrFournisseur(){
            return adrFournisseur;
    }
    public void setAdrFournisseur(Adresse adr){
        adrFournisseur = adr;
    }

    @OneToMany(mappedBy = "fournisseur")
    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}
