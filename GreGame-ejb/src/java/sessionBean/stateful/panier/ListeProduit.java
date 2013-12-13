package sessionBean.stateful.panier;

import entityBean.Produit;
import java.io.Serializable;

/**
 *
 * @author LABED
 */
public class ListeProduit implements Serializable{
    
    private Produit produit;
    private int quantite;

    /**
     * constructeur PanierProduit
     *
     * @param produit
     * @param quantite
     */
    public ListeProduit(Produit produit, Integer quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    /**
     * retourne le sous total d'un produit du panier
     *
     * @return
     */
    public int getSousTotal() {
        return produit.getPrixUnitaire() * quantite;
    }

    /**
     * getter sur Produit
     * @return
     */
    public Produit getProduit() {
        return produit;
    }

    /**
     * setter sur Produit
     * @param produit
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    /**
     * getter sur Quantite
     * @return
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * setter sur Quantite
     * @param quantite
     */
    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}
