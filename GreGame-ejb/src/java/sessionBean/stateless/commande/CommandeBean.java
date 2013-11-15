/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.commande;

import entityBean.Client;
import entityBean.Commande;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
