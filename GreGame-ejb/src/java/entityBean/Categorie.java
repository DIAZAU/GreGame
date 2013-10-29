package entityBean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;


@Entity
public class Categorie implements Serializable {
    private int idCategorie;
    private String genre;

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
}
