package entityBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
    @NamedQuery(name = "Produit.findByIdProduit", query = "SELECT p FROM Produit p WHERE p.idProduit = :idProduit"),
    @NamedQuery(name = "Produit.findByNom", query = "SELECT p FROM Produit p WHERE p.nomProduit LIKE :nomProduit"),
    @NamedQuery(name = "Produit.findByLangue", query = "SELECT p FROM Produit p WHERE p.langueVoix = :langue")})
public class Produit implements Serializable {
	private int idProduit;
	private String nomProduit;
	private String langueVoix;
	private String langueEcran;
	private String description;
	private byte[] photo;
        private String numeroProduit;
	private List<Avis> listAvis;
	private List<Article> listArticle;
	private Categorie categorie;
        private Fournisseur fournisseur;
	
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
	
	
        @OneToMany(mappedBy = "produit")
	public List<Article> getListArticle() {
            return listArticle;
	}
	public void setListArticle(List<Article> listArticle) {
            this.listArticle = listArticle;
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
	public String getDescription() {
            return description;
	}
	public void setDescription(String description) {
            this.description = description;
	}
	
	@Lob
	public byte[] getPhoto() {
            return photo;
	}
	public void setPhoto(byte[] photo) {
            this.photo = photo;
	}

    /**
     * @return the numeroProduit
     */
    @NotNull
    public String getNumeroProduit() {
        return numeroProduit;
    }

    /**
     * @param numeroProduit the numeroProduit to set
     */
    public void setNumeroProduit(String numeroProduit) {
        this.numeroProduit = numeroProduit;
    }

    /**
     * @return the fournisseur
     */
    @ManyToOne 
    @JoinColumn (name = "fournisseur_FK", referencedColumnName = "idFournisseur") 
    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    /**
     * @param fournisseur the fournisseur to set
     */
    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
}
