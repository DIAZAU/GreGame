package entityBean;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class LigneFournisseur implements java.io.Serializable{
    private int id;
    private int quantite;
    private Date dateLivraison;
    private Produit produit;
    private Fournisseur fournisseur;


    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public Date getDateLivraison() {
        return dateLivraison;
    }
    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    @ManyToOne (optional = false)
    @JoinColumn (name = "produit_PK", referencedColumnName = "idProduit")
    public Produit getProduit() {
        return produit;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    @ManyToOne (optional = false)
    @JoinColumn (name = "fournisseur_PK", referencedColumnName = "idFournisseur")
    public Fournisseur getFournisseur() {
        return fournisseur;
    }
    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
