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
      
    public void addArticle(int idArticle, int quantite);
    
    public  void updateQuantiteArticle(int idArticle, int quantite);
    
    public void removeArticle(int idArticle);

    public Double getTotal();

    public boolean isEmpty();

    public List<PanierArticle> getpanierArticles();
    
    public void validatePanier(int idClent);
    
    public void invalidatePanier(int idClient);
    
}
