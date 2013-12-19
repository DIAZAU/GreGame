/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import entityBean.Adresse;
import entityBean.Client;
import entityBean.Commande;
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
import sessionBean.stateless.client.ClientBeanLocal;
import sessionBean.stateless.commande.CommandeBeanLocal;

/**
 *
 * @author FALL
 */
public class CompteClientBean implements Serializable{
    
    private boolean logged = false;
    private Client client;
    private Adresse adresse;
    private Adresse adresseLivraison;
    @EJB
    private ClientBeanLocal clientBean;
    @EJB
    private CommandeBeanLocal commandeBean;
    private List<Commande> listeCommande = new ArrayList<Commande>();
    private List<Commande> historiqueCommande = new ArrayList<Commande>();
    private final HttpSession session;
    
    public CompteClientBean() {
        client = new Client();
        adresse = new Adresse();
        
        adresseLivraison = new Adresse();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        session = request.getSession(true);
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
            FacesMessage facesMsg = new FacesMessage();
            facesMsg.setDetail(msg);
            facesMsg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage("errorAuth", facesMsg);
            return "";
        } catch (Exception e){
            String msg = "Identifiants invalides";
            FacesMessage facesMsg = new FacesMessage();
            facesMsg.setDetail(msg);
            facesMsg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage("errorAuth", facesMsg);
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
            FacesMessage facesMsg = new FacesMessage();
            facesMsg.setDetail(msg);
            facesMsg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage("errorInscription", facesMsg);
            return "";
            
        } catch (Exception e){
            String msg = "Nous avons rencontrés des problémes lors de l'ajout";
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage("errorInscription", facesMsg);
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
            FacesMessage facesMsg = new FacesMessage();
            facesMsg.setDetail(msg);
            facesMsg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage("errorInscription", facesMsg);
            return "";
            
        } catch (Exception e){
            String msg = "Nous avons rencontrés des problémes lors de l'ajout";
            FacesMessage facesMsg = new FacesMessage();
            facesMsg.setDetail(msg);
            facesMsg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage("errorInscription", facesMsg);
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
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
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

    /**
     * @return the listeCommande
     */
    public List<Commande> getListeCommande() {
        setListeCommande(commandeBean.getListCommandeEncours(client.getIdClient()));
        return listeCommande;
    }

    /**
     * @param listeCommande the listeCommande to set
     */
    public void setListeCommande(List<Commande> listeCommande) {
        this.listeCommande = listeCommande;
    }

    /**
     * @return the historiqueCommande
     */
    public List<Commande> getHistoriqueCommande() {
        setHistoriqueCommande(commandeBean.getListCommande(client.getIdClient()));
        return historiqueCommande;
    }

    /**
     * @param historiqueCommande the historiqueCommande to set
     */
    public void setHistoriqueCommande(List<Commande> historiqueCommande) {
        this.historiqueCommande = historiqueCommande;
    }

    /**
     * @return the commandeBean
     */
    public CommandeBeanLocal getCommandeBean() {
        return commandeBean;
    }

    /**
     * @param commandeBean the commandeBean to set
     */
    public void setCommandeBean(CommandeBeanLocal commandeBean) {
        this.commandeBean = commandeBean;
    }

}
