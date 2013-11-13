/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.catalogue;

import entityBean.Adresse;
import entityBean.Article;
import entityBean.Categorie;
import entityBean.Fournisseur;
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
public class CatalogueRemoteImpl implements CatalogueBeanRemote {
    @PersistenceContext (unitName = "GreGame_Persistence")
    EntityManager em;

    @Override
    public Categorie createCategorie(Categorie cat) {
        List<Categorie> listCategorie;
            if (cat !=null){
            listCategorie = em.createNamedQuery("Categorie.findByCategoriegenre").setParameter("genre", cat.getGenre()).getResultList();
            if (!listCategorie.isEmpty())
                return null;
            else{
                em.persist(cat);
            }
            return cat;
        }
        return null;
    }

    @Override
    public Produit createProduit(Produit pro) {
        if (pro != null){
            em.persist(pro);
            return pro;
        }
        return null;
    }

    @Override
    public Article createArticle(Article art) {
        if (art != null){
            em.persist(art);
            return art;
        }
        return null;
    }

    /**
     *
     * @param idCategorie
     * @return Categorie
     */
    @Override
    public Categorie findCategorie(int idCategorie) {
        return em.find(Categorie.class, idCategorie);
    }

    /**
     *
     * @param idProduit
     * @return Produit
     */
    @Override
    public Produit findProduit(int idProduit) {
        return em.find(Produit.class, idProduit);
    }

    @Override
    public Article findArticle(int idArticle) {
        return em.find(Article.class, idArticle);
    }

    @Override
    public void deleteCategorie(int idCategorie) {
        Categorie catToRemove = em.find(Categorie.class, idCategorie);
        em.remove(catToRemove);
    }

    @Override
    public void deleteProduit(int idProduit) {
        Produit proToRemove = em.find(Produit.class, idProduit);
        em.remove(proToRemove);
    }

    @Override
    public void deleteArticle(int idAricle) {
        Article artToRemove = em.find(Article.class, idAricle);
        em.remove(artToRemove);
    }

    @Override
    public List<Produit> searchProduit(String critereSearch) {
        Query searchQuery = em.createNamedQuery("Produit.findByNom");
        searchQuery.setParameter("nomProduit", critereSearch);
        return searchQuery.getResultList();
    }

    @Override
    public List<Article> searchArticle(String critereSearch) {
        Query searchQuery = em.createNamedQuery("Article.findByNomArticle");
        searchQuery.setParameter("nomArticle", critereSearch);
        return searchQuery.getResultList();
    }

    @Override
    public List<Categorie> searchCategorie(String critereSearch) {
        Query searchQuery = em.createNamedQuery("Categorie.findByCategoriegenre");
        searchQuery.setParameter("genre", critereSearch);
        return searchQuery.getResultList();
    }

    @Override
    public List<Produit> findAllProduit() {
        Query searchQuery = em.createNamedQuery("Produit.findAll");
        return searchQuery.getResultList();
    }

    @Override
    public List<Article> findAllArticle() {
        Query searchQuery = em.createNamedQuery("Article.findAll");
        return searchQuery.getResultList();
    }

    @Override
    public List<Categorie> findAllCategorie() {
        Query searchQuery = em.createNamedQuery("Categorie.findAll");
        return searchQuery.getResultList();
    }

    @Override
    public List<Fournisseur> findAllFournisseur() {
        StringBuilder query = new StringBuilder("SELECT f FROM Fournisseur f");
        Query querySearch = em.createNamedQuery(query.toString());
        List<Fournisseur> listFournisseur = querySearch.getResultList();
        listFournisseur.size();
        return listFournisseur;
    }

    @Override
    public Fournisseur createFournisseur(Fournisseur four) {
        if (four != null){
            em.persist(four);
            return four;
        }
        return null;
    }

    @Override
    public void deteleFournisseur(int idFournisseur) {
        Fournisseur four = em.find(Fournisseur.class, idFournisseur);
        em.remove(four);
    }

    @Override
    public Adresse updateAdresse(int idAdresse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Adresse createAdresse(Adresse adresse) {
        if (adresse != null){
            em.persist(adresse);
            return adresse;
        } 
        return null;
    }

    @Override
    public Fournisseur updateFournisseur(int idFournisseur, Fournisseur fournisseur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCategorie(int idCategorie, Categorie categorie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateProduit(int idProduit, Produit produit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateArticle(int idAricle, Article article) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
