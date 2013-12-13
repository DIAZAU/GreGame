/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author FALL
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ModeLivraison.findAll", query = "SELECT ml FROM ModeLivraison ml")})
public class ModeLivraison implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
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
