/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.catalogue;

import entityBean.Article;
import entityBean.Categorie;
import entityBean.Produit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FALL
 */
@Stateless
public class CatalogueBean implements CatalogueBeanLocal {
    @PersistenceContext (unitName = "GreGame_Persistence")
    private EntityManager em;


    @Override
    public Produit findProduit(int idProduit) {
        return em.find(Produit.class, idProduit);
    }

    @Override
    public Article findArticle(int idArticle) {
        return em.find(Article.class, idArticle);
    }

    @Override
    public Categorie findCategorie(int idCategorie) {
        return em.find(Categorie.class, idCategorie);
    }

    @Override
    public List search(String critereSearch) {
        StringBuilder query = new StringBuilder(
                "SELECT a FROM Article a WHERE a.nomArticle LIKE :critereSearch"
                + "UNION"
                + "SELECT a FROM Article a WHERE a.produit.idProduit IN "
                + "SELECT p.idProduit FROM Produit p WHERE p.nomProduit LIKE :critereSearch"
                + "OR p.categorie.idCategorie IN SELECT c.idCategorie FROM Categorie c WHERE c.genre LIKE :critereSearch");
        Query searchQuery;
        searchQuery = em.createQuery(query.toString());
        searchQuery.setParameter("critereSearch", critereSearch);
        return searchQuery.getResultList();
    }

}