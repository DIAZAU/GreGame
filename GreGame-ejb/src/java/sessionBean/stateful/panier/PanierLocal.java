package sessionBean.stateful.panier;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FALL && LABED
 */
@Local
public interface PanierLocal {
      
    public void addProduit(int idProduit, int quantite);
    
    public  void updateQuantiteProduit(int idProduit, int quantite);
    
    public void removeProduit(int idProduit);

    public Double getTotal();

    public boolean isEmpty();

    public List<ListeProduit> getpanierProduits();
    
    public void validatePanier(int idClent);
    
    public void invalidatePanier(int idClient);

    public void clear();

    public void initialize();
    
}
