/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.catalogue;

import entityBean.Adresse;
import entityBean.Article;
import entityBean.Categorie;
import entityBean.Fournisseur;
import entityBean.Produit;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author FALL
 */
@Remote
public interface CatalogueBeanRemote {
    
    public Categorie createCategorie(Categorie cat) ;
    
    public Produit createProduit(Produit pro) ;
    
    public Article createArticle(Article art) ;
    
    public Fournisseur createFournisseur(Fournisseur four); 
    
    public Categorie findCategorie(int idCategorie);
    
    public Produit findProduit(int idProduit);
    
    public Article findArticle(int idArticle) ;
    
    public void deleteCategorie(int idCategorie);
    
    public void deleteProduit(int idProduit);
   
    public void deleteArticle(int idAricle);
    
    public void deteleFournisseur(int idFournisseur);
    
    public List<Produit> searchProduit(String critereSearch);
    
    public List<Article> searchArticle(String critereSearch) ;
    
    public List<Categorie> searchCategorie(String critereSearch);
    
    public List<Produit> findAllProduit() ;
    
    public List<Article> findAllArticle();
    
    public List<Categorie> findAllCategorie() ;

    public List<Fournisseur> findAllFournisseur() ;
    
    public Adresse updateAdresse(int idAdresse);
    
    public Adresse createAdresse(Adresse adresse);
    
    public Fournisseur updateFournisseur(int idFournisseur, Fournisseur fournisseur);
    
    public void updateCategorie(int idCategorie, Categorie categorie);
    
    public void updateProduit(int idProduit, Produit produit);
   
    public void updateArticle(int idAricle, Article article); 
    
}
