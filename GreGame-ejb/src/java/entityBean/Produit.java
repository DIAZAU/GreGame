package entityBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotNull;

/**
 * Cette classe represente un produit.
 * @author FALL
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p"),
    @NamedQuery(name = "Produit.findByIdProduit", query = "SELECT p FROM Produit p WHERE p.idProduit LIKE :idProduit"),
    @NamedQuery(name = "Produit.findByNom", query = "SELECT p FROM Produit p WHERE p.nomProduit LIKE :nomProduit"),
    @NamedQuery(name = "Produit.findByLangue", query = "SELECT p FROM Produit p WHERE p.langueVoix LIKE :langue")})
public class Produit implements Serializable {
	private int idProduit;
	private String nomProduit;
	private String langueVoix;
	private String langueEcran;
	private String description;
	private String photo;
        private int prixUnitaire;
        private int quantite;
        
	private List<Avis> listAvis;
	private Categorie categorie;
        private List<Fournisseur> listFournisseur;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getIdProduit() {
            return idProduit;
	}
	public void setIdProduit(int idProduit) {
            this.idProduit = idProduit;
	}
	
	@OneToMany (cascade = CascadeType.REMOVE, mappedBy = "produit")
	public List<Avis> getListAvis() {
            return listAvis;
	}
	public void setListAvis(List<Avis> listAvis) {
            this.listAvis = listAvis;
	}
	
	
	@ManyToOne
	@JoinColumn (name = "categorie_FK", referencedColumnName = "idCategorie")
	public Categorie getCategorie() {
            return categorie;
	}
	public void setCategorie(Categorie categorie) {
            this.categorie = categorie;
	}
	@NotNull
	public String getNomProduit() {
            return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
            this.nomProduit = nomProduit;
	}
	
	@NotNull
	public String getLangueVoix() {
            return langueVoix;
	}
	public void setLangueVoix(String langueVoix) {
            this.langueVoix = langueVoix;
	}
	
	@NotNull
	public String getLangueEcran() {
            return langueEcran;
	}
	public void setLangueEcran(String langueEcran) {
            this.langueEcran = langueEcran;
	}
	
	@NotNull
        @Column(length = 1250)
	public String getDescription() {
            return description;
	}
	public void setDescription(String description) {
            this.description = description;
	}
	
	
	public String getPhoto() {
            return photo;
	}
	public void setPhoto(String photo) {
            this.photo = photo;
	}

    /**
     * @return the prixUnitaire
     */
    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    /**
     * @param prixUnitaire the prixUnitaire to set
     */
    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    /**
     * @return the quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * @return the listFournisseur
     */
   
    @ManyToMany(mappedBy = "produits")
    public List<Fournisseur> getListFournisseur() {
        return listFournisseur;
    }

    /**
     * @param listFournisseur the listFournisseur to set
     */
    public void setListFournisseur(List<Fournisseur> listFournisseur) {
        this.listFournisseur = listFournisseur;
    }
}
