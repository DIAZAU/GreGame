/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import entityBean.Produit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessionBean.stateless.catalogue.CatalogueBeanLocal;

/**
 *
 * @author FALL
 */
public class CatalogueBean implements Serializable{

    /**
     * Creates a new instance of CatalogueBean
     */
    @EJB
    private CatalogueBeanLocal catalogue;
    private Produit produit;
    private String key = "recherche";
    private String categorie;
    private int id;
    private List<Produit> resultatRecherche;
    
    public CatalogueBean() {
        produit = new Produit();
        resultatRecherche = new ArrayList<>();
    }
    
    public String detailProduit(){
        String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idPro", param);
        setId(Integer.parseInt(param));
        produit = catalogue.findProduit(id);
        return "produit.trouver";
    }
    
    public String rechercheParCategorie(){
        String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("categorie");
        setCategorie(param);
        System.out.println(param);
        setResultatRecherche((List<Produit>) catalogue.searchByCategorie(getCategorie()));
        return "categorie.trouver";
    }
    
    public String recherche(){
        if (catalogue.search(key) != null)
            setResultatRecherche(catalogue.search(key));
        setKey("recherche");
        return "recherche.success";
    }

    /**
     * @return the produit
     */
    public Produit getProduit() {
        return produit;
    }

    /**
     * @param produit the produit to set
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * @return the resultatRecherche
     */
    public List<Produit> getResultatRecherche() {
        return resultatRecherche;
    }

    /**
     * @param resultatRecherche the resultatRecherche to set
     */
    public void setResultatRecherche(List<Produit> resultatRecherche) {
        this.resultatRecherche = resultatRecherche;
    }
}
