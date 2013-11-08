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
 *
 * @author FALL
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a"),
    @NamedQuery(name = "Adresse.findByIdAdresse", query = "SELECT a FROM Adresse a WHERE a.idAdresse = :idAdresse"),
    @NamedQuery(name = "Adresse.findByRue1", query = "SELECT a FROM Adresse a WHERE a.rue1 = :rue1"),
    @NamedQuery(name = "Adresse.findByRue2", query = "SELECT a FROM Adresse a WHERE a.rue2 = :rue2"),
    @NamedQuery(name = "Adresse.findByVille", query = "SELECT a FROM Adresse a WHERE a.ville = :ville"),
    @NamedQuery(name = "Adresse.findByCodePostale", query = "SELECT a FROM Adresse a WHERE a.codePostale = :codePostale")})
public class Adresse implements Serializable {
    private int idAdresse;
    private String rue1;
    private String rue2;
    private int codePostale;
    private String ville;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getIdAdresse() {
        return idAdresse;
    }
    public void setIdAdresse(int idAdresse) {
        this.idAdresse = idAdresse;
    }

    @NotNull
    public String getRue1() {
        return rue1;
    }
    public void setRue1(String rue1) {
        this.rue1 = rue1;
    }

    @NotNull
    public String getRue2() {
        return rue2;
    }
    public void setRue2(String rue2) {
        this.rue2 = rue2;
    }

    @NotNull
    public int getCodePostale() {
        return codePostale;
    }
    public void setCodePostale(int codePostale) {
        this.codePostale = codePostale;
    }

    @NotNull
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        
        this.ville = ville;
    }

}
