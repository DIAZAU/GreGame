/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.commande;

import entityBean.Client;
import entityBean.Commande;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author FALL
 */
@Remote
public interface CommandeBeanRemote {
    
  public void supprimerCommande(int idCommande);
  public List<Commande> getCommande(Client client);
  public List<Commande> getAllCommnades();
    
}
