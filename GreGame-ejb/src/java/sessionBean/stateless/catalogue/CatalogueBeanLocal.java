/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.catalogue;

import entityBean.Categorie;
import entityBean.ModeLivraison;
import entityBean.Produit;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FALL
 */
@Local
public interface CatalogueBeanLocal {
    public Produit findProduit(int idProduit);
    public Categorie findCategorie (int idCategorie);
    public List search(String critereSearch);
    public List searchByCategorie(String critereSearch);
    public List getListCommande(int idClient);
    public List getListCommandeEncours(int idClient);
    
    public ModeLivraison createModeLivraison(ModeLivraison ml);
    
    public ModeLivraison updateModeLivraison(int idModeLivraison, ModeLivraison ml);
    
    public void deleteModeLivraison(int idModeLivraison);
    
    public List findAllModeLivraison();
    
}
