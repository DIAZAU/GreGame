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
    public void addArticle(int idArticle, int quantite) {
        int i = 0;
        Article article = (Article) em.createNamedQuery("Article.findByIdArticle").setParameter("idArticle", idArticle).getSingleResult();
        //verifier si l'article se trouve dans le panier et si c'est le cas incrementer la quantité de l'article de quantité
        if (article != null){
            for (PanierArticle pa : panierArticles) {
                if (pa.getArticle().equals(article)) {
                    if (article.getQuantite() >= quantite+pa.getQuantite() ){
                        pa.setQuantite(pa.getQuantite() + quantite);
                        article.setQuantite(article.getQuantite()-quantite-pa.getQuantite());
                        panierArticles.set(i, pa);// le changement se fait sur la liste
                        return;
                    }
                }
                i++;
            }
            // Sinon ajouté une nouvelle entrée dans la liste des articles du client
           if (article.getQuantite() >= quantite){
                article.setQuantite(article.getQuantite()-quantite);
                panierArticles.add(new PanierArticle(article, quantite));
           }
           else
               throw  new ValidationException("Quantite restante insuffisante");
            
        }
    }

    @Override
    public void updateQuantiteArticle(int idArticle, int quantite) {
        int i = 0;
        Article article = (Article) em.createNamedQuery("Article.findByIdArticle").setParameter("idArticle", idArticle).getSingleResult();
        if (article !=null){
            for (PanierArticle pa : panierArticles) {
                if (pa.getArticle().getIdArticle() == idArticle  && article.getQuantite() >= quantite) {
                    pa.setQuantite(quantite);
                    article.setQuantite(article.getQuantite()-quantite);
                    panierArticles.set(i, pa);
                }
                else
                    i++;
            }
        }
        else
            throw  new ValidationException("L'article n'existe pas");
    }

    @Override
    public void removeArticle(int idArticle) {
        for (PanierArticle pa : panierArticles) {
            if (pa.getArticle().getIdArticle() == idArticle) {
                Article article = (Article) em.createNamedQuery("Article.findByIdArticle").setParameter("idArticle", idArticle).getSingleResult();
                article.setQuantite(article.getQuantite()+pa.getQuantite());
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
    @Remove
    public void validatePanier(int idClient) {
        Client client = (Client) em.createNamedQuery("Client.findByIdclient").setParameter("idClient", idClient).getSingleResult();
        if (isEmpty() && client!=null){
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
        else
            throw new ValidationException("Erreur panier vide");
    }

    @Override
    public void invalidatePanier(int idClient) {
        
    }

    

}
