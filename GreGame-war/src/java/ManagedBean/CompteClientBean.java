/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import entityBean.Adresse;
import entityBean.Client;
import entityBean.Produit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;
import sessionBean.stateless.catalogue.CatalogueBeanLocal;
import sessionBean.stateless.client.ClientBeanLocal;

/**
 *
 * @author FALL
 */
public class CompteClientBean implements Serializable{
    

    private String key = "recherche";
    private boolean logged = false;
    private List<Produit> resultatRecherche;
    private Client client;
    private Adresse adresse;
    private Adresse adresseLivraison;
    @EJB
    private  ClientBeanLocal clientBean;
    @EJB
    private  CatalogueBeanLocal catalogueBean;
    private final HttpSession session;
    
    public CompteClientBean() {
        client = new Client();
        adresse = new Adresse();
        adresseLivraison = new Adresse();
        resultatRecherche = new ArrayList<Produit>();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        session = request.getSession(true);
    }
    
    public String recherche(){
        if (catalogueBean.search(key) != null)
            setResultatRecherche(catalogueBean.search(key));
        setKey("recherche");
        return "recherche.success";
    }
    
     public String supprimerCompte(){
        try {
            clientBean.updateClient(getRequestId(), client);
        } catch (Exception e) {
            String msg = "Nous avons rencontrés des problémes lors de la suppression ";
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage("ErreurSuppression", facesMsg);
            return "";
        }
        return "SuppressionReussi";
    }
    
    public int getRequestId(){
        
        int id = 1;
        return id;
    }
    
    public String authentication(){
        try {
            client = clientBean.authenticate(client.getEmailClient(), client.getMotDepasse());
            setLogged(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idClient", String.valueOf(client.getIdClient()));
        } catch (ValidationException e) {
            String msg = e.getMessage();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, facesMsg);
            return "";
            
        } catch (Exception e){
            String msg = "Nous avons rencontrés des problémes lors de l'authentification";
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, facesMsg);
            return "";
        }
        return "connecte";
    }
    
    public  String authentificationCammande(){
        if (authentication().compareTo("connecte") == 0){
            return "continuCommande";
        }
        else 
            return "";
    }
    public String inscrire(){
        try {
            client.setAdrClient(adresse);
            client.setAdrLivraison(adresse);
            adresseLivraison = adresse;
            client = clientBean.createClient(getClient());
            setLogged(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idClient", String.valueOf(client.getIdClient()));
            return "success";
        } catch (ValidationException e) {
            String msg = e.getMessage();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, facesMsg);
            return "";
            
        } catch (Exception e){
            String msg = "Nous avons rencontrés des problémes lors de l'ajout";
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, facesMsg);
            return "";
        }
    }
        
    public String inscrireCommande(){
         try {
            client.setAdrClient(adresse);
            client.setAdrLivraison(adresse);
            adresseLivraison = adresse;
            client = clientBean.createClient(getClient());
            setLogged(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idClient", String.valueOf(client.getIdClient()));
            return "continuCommande";
        } catch (ValidationException e) {
            String msg = e.getMessage();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, facesMsg);
            return "";
            
        } catch (Exception e){
            String msg = "Nous avons rencontrés des problémes lors de l'ajout";
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, facesMsg);
            return "";
        }
     }
    
    public String commandeEtape2(){
        client.setAdrLivraison(adresseLivraison);
        client = clientBean.updateClient(client.getIdClient(), client);
        return "continuCommande2";
    }

        
    
    public String deconnection(){
        setLogged(false);
        return "acceuil";
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the adresse
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
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

    /**
     * @return the logged
     */
    public boolean isLogged() {
        return logged;
    }

    /**
     * @param logged the logged to set
     */
    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    /**
     * @return the adresseLivraison
     */
    public Adresse getAdresseLivraison() {
        return adresseLivraison;
    }

    /**
     * @param adresseLivraison the adresseLivraison to set
     */
    public void setAdresseLivraison(Adresse adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

}
