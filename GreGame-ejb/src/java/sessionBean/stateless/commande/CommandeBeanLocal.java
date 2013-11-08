/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.commande;

import entityBean.Client;
import entityBean.Commande;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FALL
 */
@Local
public interface CommandeBeanLocal {
    
    public void ajouterCommande(Commande commande);
    public List<Commande> getCommande(Client client);
    public List<Commande> getAllCommnades();
    
}
