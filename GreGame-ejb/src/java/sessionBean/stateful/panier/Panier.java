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
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;

/**
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
    
   
    @Override
    public void clear() {
        panierProduits.clear();
    }

    @Override
    public void addProduit(int idProduit, int quantite) {
        int i = 0;
        Produit produit = (Produit) em.createNamedQuery("Produit.findByIdProduit").setParameter("idProduit", idProduit).getSingleResult();
        //verifier si l'produit se trouve dans le panier et si c'est le cas incrementer la quantité de l'produit de quantité
        if (produit != null){
            for (ListeProduit pa : panierProduits) {
                if (pa.getProduit().getIdProduit() == idProduit) {
                    pa.setQuantite(pa.getQuantite() + quantite);
                    panierProduits.set(i, pa);// le changement se fait sur la liste
                    return;
                }
                i++;
            }
            // Sinon ajouté une nouvelle entrée dans la liste des produits du client
            panierProduits.add(new ListeProduit(produit, quantite));
        }
 }

    @Override
    public void updateQuantiteProduit(int idProduit, int quantite) throws ValidationException{
        int i = 0;
        Produit produit = (Produit) em.createNamedQuery("Produit.findByIdProduit").setParameter("idProduit", idProduit).getSingleResult();
        if (produit !=null){
            for (ListeProduit pa : panierProduits) {
                if (pa.getProduit().getIdProduit() == idProduit) {
                    pa.setQuantite(quantite);
                    panierProduits.set(i, pa);
                    return ;
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
        return panierProduits.isEmpty();
    }

    @Override
    public List<ListeProduit> getpanierProduits() {
        List listProduit = panierProduits;
        return listProduit;
    }

    @Override
    public void validatePanier(int idClient) throws ValidationException{
        Client client = (Client) em.createNamedQuery("Client.findByIdclient").setParameter("idClient", idClient).getSingleResult();
        if (!isEmpty() && client!=null){
            Commande commandeClient = new Commande();
            commandeClient.setClient(client);
            commandeClient.setEtatCommande("En cours");
            commandeClient.setDateCommande(new Date());
            em.persist(commandeClient);
            //ArrayList<LigneCommande> listCommande = new ArrayList<LigneCommande>();
            Produit produit = null;
            ListeProduit pa = null;
            int i = 0;
            for (i = 0; i < panierProduits.size(); i++){
                pa  = panierProduits.get(i);
                produit = (Produit)em.createNamedQuery("Produit.findByIdProduit").setParameter("idProduit", pa.getProduit().getIdProduit()).getSingleResult();
                if (produit.getQuantite()>= pa.getQuantite()){
                    LigneCommande lc = new LigneCommande();
                    lc.setProduit(pa.getProduit());
                    lc.setQuantite(pa.getQuantite());
                    lc.setCommande(commandeClient);
                    //listCommande.add(lc);
                    produit.setQuantite(produit.getQuantite()-pa.getQuantite());
                    em.persist(lc);
                }
                else
                    throw new ValidationException("Commande non validée");
                produit = null;
                pa = null;
            }
            //commandeClient.setListCommande(listCommande);
            
            clear();
        }
        else
            throw new ValidationException("Erreur panier vide");
    }

    @Override
    public void invalidatePanier(int idClient) {
        
    }
}
