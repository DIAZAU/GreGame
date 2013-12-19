/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;

/**
 *
 * @author FALL
 */


public class ModeLivraison implements Serializable {
    private int id;
    private Double prix;
    private String modeLivraison;
    private int nbJour; 

    /**
     * @return the prix
     */
    public Double getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(Double prix) {
        this.prix = prix;
    }

    /**
     * @return the modeLivraison
     */
    public String getModeLivraison() {
        return modeLivraison;
    }

    /**
     * @param modeLivraison the modeLivraison to set
     */
    public void setModeLivraison(String modeLivraison) {
        this.modeLivraison = modeLivraison;
    }

    /**
     * @return the nbJour
     */
    public int getNbJour() {
        return nbJour;
    }

    /**
     * @param nbJour the nbJour to set
     */
    public void setNbJour(int nbJour) {
        this.nbJour = nbJour;
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
