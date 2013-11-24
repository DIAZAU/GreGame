package entityBean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.validation.constraints.NotNull;



/**
 *
 * @author FALL
 */
@Entity
@NamedQueries(
        @NamedQuery(name="Avis.findAll", query="select a from Avis a"))
public class Avis implements Serializable {
	
    private int idAvis;
    private String messageAvis;
    private int noteAvis;
    private Client client;
    private Produit produit;

    public Avis() {
    }


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getIdAvis() {
        
        return idAvis;
    }
    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    public Avis(int idAvis, String messageAvis, int noteAvis) {
        this.idAvis = idAvis;
        this.messageAvis = messageAvis;
        this.noteAvis = noteAvis;
        
    }

    @ManyToOne 
    @JoinColumn (name = "produit_FK", referencedColumnName = "idProduit") 
    public Produit getProduit() {
        return produit;
    }
    public void setProduit(Produit produitFK) {
        this.produit = produitFK;
    }

    @ManyToOne(optional = false)
    @JoinColumn (name = "client_FK", referencedColumnName = "idClient") 
    public Client getClient() {
        return client;
    }
    public void setClient(Client clientFK) {
        this.client = clientFK;
    }
    @NotNull
    public String getMessageAvis() {
        return messageAvis;
    }
    public void setMessageAvis(String messageAvis) {
        this.messageAvis = messageAvis;
    }

    @NotNull
    public int getNoteAvis() {
        return noteAvis;
    }
    public void setNoteAvis(int noteAvis) {
        this.noteAvis = noteAvis;
    }
    
}
