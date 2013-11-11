package entityBean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;


@Entity
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Article.findQuantiteArticle", query = "SELECT a.quantite FROM Article a WHERE a.idArticle = :idArticle"),
    @NamedQuery(name = "Article.findByIdArticle", query = "SELECT a FROM Article a WHERE a.idArticle = :idArticle"),
    @NamedQuery(name = "Article.findByNomArticle", query = "SELECT a FROM Article a WHERE a.nomArticle LIKE :nomArticle"),
    @NamedQuery(name = "Article.findByPrixUnitaire", query = "SELECT a FROM Article a WHERE a.prixUnitaire = :prixUnitaire"),
    @NamedQuery(name = "Article.findByQuantite", query = "SELECT a FROM Article a WHERE a.quantite = :quantite")})
public class Article implements Serializable {
    private int idArticle;
    private String nomArticle;
    private int prixUnitaire;
    private int quantite;
    private Produit produit;

   
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getIdArticle() {
       return idArticle;
    }
    public void setIdArticle(int idArticle) {
       this.idArticle = idArticle;
    }


    @ManyToOne
    @JoinColumn(name = "produit_FK", referencedColumnName = "idProduit")
    public Produit getProduit() {
       return produit;
    }
    public void setProduit(Produit produitFK) {
       this.produit = produitFK;
    }
    @NotNull
    public String getNomArticle() {
       return nomArticle;
    }
    public void setNomArticle(String nomArticle) {
       this.nomArticle = nomArticle;
    }

    @NotNull
    public int getPrixUnitaire() {
       return prixUnitaire;
    }
    public void setPrixUnitaire(int prixUnitaire) {
       this.prixUnitaire = prixUnitaire;
    }

    @NotNull
    public int getQuantite() {
       return quantite;
    }
    public void setQuantite(int quantite) {
       this.quantite = quantite;
    }

}
