package entityBean;

import java.io.Serializable;
import java.util.ArrayList;

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
	private byte photo;
	private ArrayList<Avis> listAvis;
	private ArrayList<Article> listArticle;
	private Categorie categorie;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getIdProduit() {
            return idProduit;
	}
	public void setIdProduit(int idProduit) {
            this.idProduit = idProduit;
	}
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "produit")
	public ArrayList<Avis> getListAvis() {
            return listAvis;
	}
	public void setListAvis(ArrayList<Avis> listAvis) {
            this.listAvis = listAvis;
	}
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "produit")
	public ArrayList<Article> getListArticle() {
            return listArticle;
	}
	public void setListArticle(ArrayList<Article> listArticle) {
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
	public byte getPhoto() {
            return photo;
	}
	public void setPhoto(byte photo) {
            this.photo = photo;
	}
}
