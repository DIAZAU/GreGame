package sessionBean.stateful.panier;

import entityBean.Produit;

/**
 *
 * @author LABED
 */
class ListeProduit {
    
    private Produit produit;
    private Integer quantite;

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
