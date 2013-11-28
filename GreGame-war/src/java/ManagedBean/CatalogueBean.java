/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import entityBean.Produit;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import sessionBean.stateless.catalogue.CatalogueBeanLocal;

/**
 *
 * @author FALL
 */
public class CatalogueBean {

    /**
     * Creates a new instance of CatalogueBean
     */
    @EJB
    private CatalogueBeanLocal catalogue;
    private Produit produit;
    private String key;
    private int id;
    
    public CatalogueBean() {
        produit = new Produit();
    }
    
    public String detailProduit(){
        String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        setId(Integer.parseInt(param));
        produit = catalogue.findProduit(id);
        return "produit.trouve";
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
}
