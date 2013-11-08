/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.catalogue;

import entityBean.Article;
import entityBean.Categorie;
import entityBean.Fournisseur;
import entityBean.Produit;
import java.util.List;
import javax.ejb.Remote;
import javax.persistence.Query;

/**
 *
 * @author FALL
 */
@Remote
public interface CatalogueBeanRemote {
    
    public boolean createCategorie(Categorie cat) ;
    
    public boolean createProduit(Produit pro) ;
    
    public boolean createArticle(Article art) ;
    
    public Categorie findCategorie(int idCategorie);
    
    public Produit findProduit(int idProduit);
    
    public Article findArticle(int idArticle) ;
    
    public void deleteCategorie(int idCategorie);
    
    public void deletaProduit(int idProduit);
   
    public void deleteArticle(int idAricle);
    
    public List<Produit> searchProduit(String critereSearch);
    
    public List<Article> searchArticle(String critereSearch) ;
    
    public List<Categorie> searchCategorie(String critereSearch);
    
    public List<Produit> findAllProduit() ;
    
    public List<Article> findAllArticle();
    
    public List<Categorie> findAllCategorie() ;

    public List<Fournisseur> findAllFournisseur() ;
  
    
}
