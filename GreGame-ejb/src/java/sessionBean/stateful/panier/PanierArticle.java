package sessionBean.stateful.panier;

import entityBean.Article;

/**
 *
 * @author LABED
 */
class PanierArticle {
    
    private Article article;
    private Integer quantite;

    /**
     * constructeur PanierArticle
     *
     * @param article
     * @param quantite
     */
    public PanierArticle(Article article, Integer quantite) {
        this.article = article;
        this.quantite = quantite;
    }

    /**
     * retourne le sous total d'un article du panier
     *
     * @return
     */
    public int getSousTotal() {
        return article.getPrixUnitaire() * quantite;
    }

    /**
     * getter sur Article
     * @return
     */
    public Article getArticle() {
        return article;
    }

    /**
     * setter sur Article
     * @param article
     */
    public void setArticle(Article article) {
        this.article = article;
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
