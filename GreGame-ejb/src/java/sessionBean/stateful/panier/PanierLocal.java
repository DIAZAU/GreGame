package sessionBean.stateful.panier;

import entityBean.Article;
import entityBean.Client;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FALL && LABED
 */
@Local
public interface PanierLocal {
      
    public void addArticle(Article article, int quantité);
    
    public  void updateQuantiteArticle(Article article, int quantité);
    
    public void removeArticle(Article article);

    public Double getTotal();

    public boolean isEmpty();

    public List<PanierArticle> getpanierArticles();
    
    public void validatePanier(Client client);
    
    public void invalidatePanier(Client client);
    
}
