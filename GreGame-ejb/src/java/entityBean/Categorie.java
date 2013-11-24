package entityBean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.validation.constraints.NotNull;


/**
 * Cette classe represente les categories.
 * @author FALL
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c"),
    @NamedQuery(name = "Categorie.findByCategoriegenre", query = "SELECT c FROM Categorie c WHERE c.genre LIKE :genre"),
    @NamedQuery(name = "Categorie.findByCategorieId", query = "SELECT c FROM Categorie c WHERE c.idCategorie = :idCategorie")})
public class Categorie implements Serializable {
    private int idCategorie;
    private String genre;
    private String nomCategorie;

    public Categorie() {
        super();
    }
    
    public Categorie (int id, String genre, String nom){
        this.genre = genre;
        this.idCategorie = id;
        this.nomCategorie = nom;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    @NotNull
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the nomCategorie
     */
    public String getNomCategorie() {
        return nomCategorie;
    }

    /**
     * @param nomCategorie the nomCategorie to set
     */
    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }


}
