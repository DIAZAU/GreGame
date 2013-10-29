package entityBean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 *
 * @author FALL
 */
@Entity
public class LigneCommande implements Serializable{
    private int id;
    private Article article;
    private Commande commande;
    private int quantite;
	
	
    @ManyToOne(optional = false)
    @JoinColumn(name = "article_PK", referencedColumnName = "idArticle")
    public Article getArticle() {
            return article;
    }
    public void setArticle(Article article) {
            this.article = article;
    }


    @ManyToOne(optional = false)
    @JoinColumn(name = "commande_PK", referencedColumnName = "idCommande")
    public Commande getCommande() {
            return commande;
    }
    public void setCommande(Commande commande) {
            this.commande = commande;
    }


    public int getQuantite() {
            return quantite;
    }
    public void setQuantite(int quantite) {
            this.quantite = quantite;
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
