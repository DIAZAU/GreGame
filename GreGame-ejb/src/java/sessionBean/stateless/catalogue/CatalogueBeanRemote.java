/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.catalogue;

import entityBean.Article;
import entityBean.Categorie;
import entityBean.Produit;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author FALL
 */
@Remote
public interface CatalogueBeanRemote {
    public boolean createCategorie(Categorie cat);
    public boolean createProduit (Produit pro);
    public boolean createArticle (Article art);
    public Categorie findCategorie (int idCategorie);
    public Produit findProduit (int idProduit);
    public Article findArticle (int idArticle);
    public boolean deleteCategorie(int idCategorie);
    public boolean deletaProduit (int idProduit);
    public boolean deleteArticle (int idAricle);
    public ArrayList<Produit> searchProduit (String critereSearch);
    public ArrayList<Article> searchArticle (String critereSearch);
    public ArrayList<Categorie> searchCategorie (String critereSearch);
    public ArrayList<Produit> findAllProduit();
    public ArrayList<Article> findAllArticle();
    public ArrayList<Categorie> findAllCategorie();
    
}
