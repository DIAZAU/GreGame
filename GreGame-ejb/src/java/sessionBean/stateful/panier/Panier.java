/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateful.panier;

import entityBean.Produit;
import entityBean.Client;
import entityBean.Commande;
import entityBean.LigneCommande;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;

/**
 *
 * @author FALL && LABED
 */
@Stateful
public class Panier implements PanierLocal {
    
    @PersistenceContext(unitName = "GreGame_Persistence")
    private EntityManager em;
    
    private List<ListeProduit> panierProduits;
    
    public Panier(){
        super();
    }
    
    @PostConstruct
    @Override
    public void initialize() {
        panierProduits = new ArrayList<ListeProduit>();
    }
    
    @PreDestroy
    @Override
    public void clear() {
        panierProduits = null;
    }

    @Override
    public void addProduit(int idProduit, int quantite) {
        int i = 0;
        Produit produit = (Produit) em.createNamedQuery("Produit.findByIdProduit").setParameter("idProduit", idProduit).getSingleResult();
        //verifier si l'produit se trouve dans le panier et si c'est le cas incrementer la quantité de l'produit de quantité
        if (produit != null){
            for (ListeProduit pa : panierProduits) {
                if (pa.getProduit().equals(produit)) {
                    if (produit.getQuantite() >= quantite+pa.getQuantite() ){
                        pa.setQuantite(pa.getQuantite() + quantite);
                        produit.setQuantite(produit.getQuantite()-quantite-pa.getQuantite());
                        panierProduits.set(i, pa);// le changement se fait sur la liste
                        return;
                    }
                }
                i++;
            }
            // Sinon ajouté une nouvelle entrée dans la liste des produits du client
           if (produit.getQuantite() >= quantite){
                produit.setQuantite(produit.getQuantite()-quantite);
                panierProduits.add(new ListeProduit(produit, quantite));
           }
           else
               throw  new ValidationException("Quantite restante insuffisante");
            
        }
    }

    @Override
    public void updateQuantiteProduit(int idProduit, int quantite) {
        int i = 0;
        Produit produit = (Produit) em.createNamedQuery("Produit.findByIdProduit").setParameter("idProduit", idProduit).getSingleResult();
        if (produit !=null){
            for (ListeProduit pa : panierProduits) {
                if (pa.getProduit().getIdProduit() == idProduit  && produit.getQuantite() >= quantite) {
                    pa.setQuantite(quantite);
                    produit.setQuantite(produit.getQuantite()-quantite);
                    panierProduits.set(i, pa);
                }
                else
                    i++;
            }
        }
        else
            throw  new ValidationException("L'produit n'existe pas");
    }

    @Override
    public void removeProduit(int idProduit) {
        for (ListeProduit pa : panierProduits) {
            if (pa.getProduit().getIdProduit() == idProduit) {
                Produit produit = (Produit) em.createNamedQuery("Produit.findByIdProduit").setParameter("idProduit", idProduit).getSingleResult();
                produit.setQuantite(produit.getQuantite()+pa.getQuantite());
                panierProduits.remove(pa);
                return;
            }
        }
    }

    @Override
    public Double getTotal() {
        if (!isEmpty()) {
            return new Double(0);
        }
        Double total = new Double(0);
        for (ListeProduit pa : panierProduits) {
            total += pa.getSousTotal();
        }
        total = Math.round( total * 100.0 ) / 100.0;
        return total;
    }

    @Override
    public boolean isEmpty() {
        return panierProduits != null;
    }

    @Override
    public List<ListeProduit> getpanierProduits() {
        List listProduit = panierProduits;
        return listProduit;
    }

    @Override
    @Remove
    public void validatePanier(int idClient) {
        Client client = (Client) em.createNamedQuery("Client.findByIdclient").setParameter("idClient", idClient).getSingleResult();
        if (isEmpty() && client!=null){
            Commande commandeClient = new Commande();
            commandeClient.setClient(client);
            ArrayList<LigneCommande> listCommande = new ArrayList<LigneCommande>();
            for (ListeProduit pa : panierProduits){
                LigneCommande lc = new LigneCommande();
                lc.setProduit(pa.getProduit());
                lc.setQuantite(pa.getQuantite());
                lc.setCommande(commandeClient);
                listCommande.add(lc);
                em.persist(lc);
      
            }
            commandeClient.setDateCommande(new Date());
            commandeClient.setListCommande(listCommande);
            em.persist(commandeClient);
            clear();
        }
        else
            throw new ValidationException("Erreur panier vide");
    }

    @Override
    public void invalidatePanier(int idClient) {
        
    }

    

}
