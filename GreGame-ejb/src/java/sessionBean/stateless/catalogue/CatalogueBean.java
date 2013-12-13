/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.catalogue;

import entityBean.Adresse;
import entityBean.Categorie;
import entityBean.Commande;
import entityBean.Fournisseur;
import entityBean.ModeLivraison;
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
public class CatalogueBean implements CatalogueBeanLocal, CatalogueBeanRemote {
    @PersistenceContext (unitName = "GreGame_Persistence")
    private EntityManager em;


    @Override
    public Produit findProduit(int idProduit) {
        return em.find(Produit.class, idProduit);
    }

    @Override
    public Categorie findCategorie(int idCategorie) {
        return em.find(Categorie.class, idCategorie);
    }

    @Override
    public List<Produit> search(String critereSearch) {
        StringBuilder query = new StringBuilder(
                "SELECT p FROM Produit p WHERE p.nomProduit LIKE '%"+critereSearch+"%'"
                + " OR p.categorie.genre LIKE '%"+critereSearch+"%' "
                + "OR p.categorie.plateforme LIKE '%"+critereSearch+"%'");
        Query searchQuery;
        searchQuery = em.createQuery(query.toString());
        if (searchQuery.getResultList() != null)
            return (List<Produit>)searchQuery.getResultList();
        else
            return null;
    }

    @Override
    public List<Produit> searchByCategorie(String categorie) {
        StringBuilder query = new StringBuilder(
                "SELECT p FROM Produit p WHERE p.categorie.plateforme LIKE :critereSearch");
        Query searchQuery;
        searchQuery = em.createQuery(query.toString());
        searchQuery.setParameter("critereSearch", categorie);
        if (searchQuery.getResultList() != null)
            return (List<Produit>)searchQuery.getResultList();
        else
            return null;
    }

    
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
    public List<Produit> searchProduit(String critereSearch) {
        Query searchQuery = em.createNamedQuery("Produit.findByNom");
        searchQuery.setParameter("nomProduit", critereSearch);
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
    public Adresse updateAdresse(int idAdresse, Adresse adresse) {
        Adresse adr = em.find(Adresse.class, idAdresse);
        adr.setCodePostale(adresse.getCodePostale());
        adr.setRue1(adresse.getRue1());
        adr.setRue2(adresse.getRue2());
        adr.setVille(adresse.getVille());
        return adr;
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
        Fournisseur four = em.find(Fournisseur.class, idFournisseur);
        four.setAdrFournisseur(fournisseur.getAdrFournisseur());
        four.setEmailFournisseur(fournisseur.getEmailFournisseur());
        return four;
    }

    @Override
    public Categorie updateCategorie(int idCategorie, Categorie categorie) {
        Categorie cat = em.find(Categorie.class, idCategorie);
        cat.setGenre(categorie.getGenre());
        return cat;
    }

    @Override
    public Produit updateProduit(int idProduit, Produit produit) {
        Produit pro = em.find(Produit.class, idProduit);
        pro.setDescription(produit.getDescription());
        pro.setNomProduit(produit.getNomProduit());
        return pro;
    }

    @Override
    public ModeLivraison createModeLivraison(ModeLivraison ml) {
        if (ml != null){
            em.persist(ml);
            return ml;
        }
        return null;
    }

    @Override
    public ModeLivraison updateModeLivraison(int idModeLivraison, ModeLivraison ml) {
        ModeLivraison m = em.find(ModeLivraison.class, idModeLivraison);
        m.setModeLivraison(ml.getModeLivraison());
        m.setNbJour(ml.getNbJour());
        m.setPrix(ml.getPrix());
        return m;
    }

    @Override
    public void deleteModeLivraison(int idModeLivraison) {
        ModeLivraison ml = em.find(ModeLivraison.class, idModeLivraison);
        em.remove(ml);
    }

    @Override
    public List<ModeLivraison> findAllModeLivraison() {
        return em.createNamedQuery("ModeLivraison.findAll").getResultList();
    }

    @Override
    public List<Commande> getListCommande(int idClient) {
        Query request =  em.createNamedQuery("Commande.getClientCommandes");
        request.setParameter("idClient", idClient);
        return request.getResultList();
    }

    @Override
    public List getListCommandeEncours(int idClient) {
        Query request =  em.createNamedQuery("Commande.getClientCommandes");
        request.setParameter("idClient", idClient);
        request.setParameter("etatCommande", "en cours");
        return request.getResultList();
    }

}