/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.catalogue;

import entityBean.Article;
import entityBean.Categorie;
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
    public Article findArticle (int idArticle);
    public Categorie findCategorie (int idCategorie);
    public List search(String critereSearch);
    
}
