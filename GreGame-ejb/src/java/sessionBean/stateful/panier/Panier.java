/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateful.panier;

import entityBean.Article;
import entityBean.Client;
import entityBean.Commande;
import entityBean.LigneCommande;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author FALL && LABED
 */
@Stateful
public class Panier implements PanierLocal {
    
    @PersistenceContext(unitName = "GreGame_Persistence")
    private EntityManager em;
    
    private List<PanierArticle> panierArticles;
    
    public Panier(){
        super();
    }
    
    @PostConstruct
    public void initialize() {
        panierArticles = new ArrayList<PanierArticle>();
    }
    
    @PreDestroy
    public void clear() {
        panierArticles = null;
    }

    @Override
    public void addArticle(Article article, int quantité) {
        boolean articleFound = false;
        int i = 0;
        //verifier si l'article se trouve dans le panier et si c'est le cas incrementer la quantité de l'article de quantité
        for (PanierArticle pa : panierArticles) {
            if (pa.getArticle().equals(article)) {
                pa.setQuantite(pa.getQuantite() + quantité);
                panierArticles.set(i, pa);// le changement se fait sur la liste
                articleFound = true;
            }
            i++;
        }
        // Sinon ajouté une nouvelle entrée dans la liste des articles du client
        if (articleFound == false) {
            panierArticles.add(new PanierArticle(article, quantité));
        }
    }

    @Override
    public void updateQuantiteArticle(Article article, int quantité) {
        int i = 0;
        for (PanierArticle pa : panierArticles) {
            if (pa.getArticle().equals(article)) {
                pa.setQuantite(quantité);
                panierArticles.set(i, pa);
            }
            i++;
        }
    }

    @Override
    public void removeArticle(Article article) {
        for (PanierArticle pa : panierArticles) {
            if (pa.getArticle().equals(article)) {
                panierArticles.remove(pa);
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
        for (PanierArticle pa : panierArticles) {
            total += pa.getSousTotal();
        }
        total = Math.round( total * 100.0 ) / 100.0;
        return total;
    }

    @Override
    public boolean isEmpty() {
        return panierArticles != null;
    }

    @Override
    public List<PanierArticle> getpanierArticles() {
        List listArticle = panierArticles;
        return listArticle;
    }

    @Override
    public void validatePanier(Client client) {
        if (isEmpty()){
            Commande commandeClient = new Commande();
            commandeClient.setClient(client);
            ArrayList<LigneCommande> listCommande = new ArrayList<LigneCommande>();
            for (PanierArticle pa : panierArticles){
                LigneCommande lc = new LigneCommande();
                lc.setArticle(pa.getArticle());
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
    }

    

}
