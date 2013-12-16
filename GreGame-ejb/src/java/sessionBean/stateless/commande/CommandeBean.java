/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.commande;

import entityBean.Client;
import entityBean.Commande;
import entityBean.LigneCommande;
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
public class CommandeBean implements CommandeBeanLocal, CommandeBeanRemote{
    
    @PersistenceContext (unitName = "GreGame_Persistence")
    private EntityManager em;

    @Override
    public void ajouterCommande(Commande commande) {
       em.persist(commande);
    }

    @Override
    public List<Commande> getCommande(Client client) {
        return em.createNamedQuery("Commande.getClientCommandes").setParameter("idClient", client.getIdClient()).getResultList();
    }

    @Override
    public List<Commande> getAllCommnades() {
        return em.createNamedQuery("Commande.getAllCommandes").getResultList();
    }   
    
     @Override
    public void supprimerCommande(int idCommande) {
        Commande commande;
        commande= em.find(Commande.class,idCommande);
        em.remove(commande);
    }

     @Override
    public List<Commande> getListCommande(int idClient) {
        Query request =  em.createNamedQuery("Commande.getClientCommandes");
        request.setParameter("idClient", idClient);
        List<Commande> liste = request.getResultList();
        for (Commande c : liste){
            List<LigneCommande> lc = null;
            lc = em.createNamedQuery("LigneCommande.getLigneCommandeByCommande").setParameter("commande", c).getResultList();
            c.setListCommande(lc);
            c.getListCommande().size();
        }
        System.out.println(liste.get(0).getListCommande().size());
        return liste;
    }

    @Override
    public List<Commande> getListCommandeEncours(int idClient) {
        Query request =  em.createNamedQuery("Commande.getClientCommandesEncours");
        request.setParameter("idClient", idClient);
        request.setParameter("etatCommande", "en cours");
        List<Commande> liste = request.getResultList();
        for (Commande c : liste){
            List<LigneCommande> lc = null;
            lc = em.createNamedQuery("LigneCommande.getLigneCommandeByCommande").setParameter("commande", c).getResultList();
            c.setListCommande(lc);
            c.getListCommande().size();
        }
        System.out.println(liste.get(0).getListCommande().size());
        return liste;
    }
}
