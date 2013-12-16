/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import entityBean.ModeLivraison;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ValidationException;
import sessionBean.stateful.panier.*;
import sessionBean.stateful.panier.PanierLocal;
import sessionBean.stateless.catalogue.CatalogueBeanLocal;

/**
 *
 * @author FALL
 */
public class PanierBean implements Serializable{
    
    @EJB
    private PanierLocal panier;
    @EJB
    private CatalogueBeanLocal catalogue;
    private int idProduit; 
    private int quantite;
    private boolean isEmphy;
    private int idClient;
    private boolean commandeValide = false;
    private List<ModeLivraison> modeLovraison = new ArrayList<>(); 
    private List<ListeProduit> listeProduit = new ArrayList<>();
    private Double prixTotal;
    /**
     * Creates a new instance of PanierBean
     */
    public PanierBean() {
       panier = new Panier();
    }
    
    public List<ModeLivraison> listeModeLivraison(){
        setModeLovraison(catalogue.findAllModeLivraison());
        return getModeLovraison();
    }
    
    public List<ListeProduit> listeDeProduit(){
        return listeProduit;
    }
    
    public Double prixTotals(){
        if (panier.getpanierProduits().isEmpty())
            return prixTotal;
        return panier.getTotal();
    }
    
    public String afficherPanier(){
        if (panier.isEmpty())
            setIsEmphy(true);
        else
            setIsEmphy(false);
        return "panier.afficher";
    }
    
    public int nombreArticle(){
        if (getPanier().isEmpty())
            return 0;
        return getPanier().getpanierProduits().size();
    }
    public void ajouteProduit(){
        String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProduit");
        setQuantite(1);
        setIdProduit(Integer.parseInt(param));
        panier.addProduit(getIdProduit(),getQuantite());
    }
    public void supprimeProduit(int idProduit){
        panier.removeProduit(idProduit);
    }
    
    public void majProduit(int idProduit){
        getPanier().updateQuantiteProduit(idProduit, getQuantite());
    }
    
    public String validerPanier(){
        String param = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idClient");
        setIdClient(Integer.parseInt(param));
        try {
            listeProduit.clear();
            for (ListeProduit pro : getPanier().getpanierProduits()){
                listeProduit.add(pro);
            }
            prixTotal = panier.getTotal();
            getPanier().validatePanier(getIdClient());
        } catch (ValidationException e) {
            String msg = e.getMessage();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage("ErreurValidation", facesMsg);
            return "panier.valider";
        } catch (Exception e){
            String msg = e.getMessage();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage("ErreurValidation", facesMsg);
            return "panier.valider";
        }
        setCommandeValide(true);
        return "panier.valider";
    }
    
    public String commandeEtape1(){
        return "continuCommande";
    }
    
    /**
     * @return the panier
     */
    public PanierLocal getPanier() {
        return panier;
    }

    /**
     * @param panier the panier to set
     */
    public void setPanier(PanierLocal panier) {
        this.panier = panier;
    }

    /**
     * @return the idProduit
     */
    public int getIdProduit() {
        return idProduit;
    }

    /**
     * @param idProduit the idProduit to set
     */
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    /**
     * @return the quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * @return the isEmphy
     */
    public boolean isIsEmphy() {
        return isEmphy;
    }

    /**
     * @param isEmphy the isEmphy to set
     */
    public void setIsEmphy(boolean isEmphy) {
        this.isEmphy = isEmphy;
    }

    /**
     * @return the idClient
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * @param idClient the idClient to set
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * @return the commandeValide
     */
    public boolean isCommandeValide() {
        return commandeValide;
    }

    /**
     * @param commandeValide the commandeValide to set
     */
    public void setCommandeValide(boolean commandeValide) {
        this.commandeValide = commandeValide;
    }

    /**
     * @return the catalogue
     */
    public CatalogueBeanLocal getCatalogue() {
        return catalogue;
    }

    /**
     * @param catalogue the catalogue to set
     */
    public void setCatalogue(CatalogueBeanLocal catalogue) {
        this.catalogue = catalogue;
    }

    /**
     * @return the modeLovraison
     */
    public List<ModeLivraison> getModeLovraison() {
        return modeLovraison;
    }

    /**
     * @param modeLovraison the modeLovraison to set
     */
    public void setModeLovraison(List<ModeLivraison> modeLovraison) {
        this.modeLovraison = modeLovraison;
    }

    /**
     * @return the listeProduit
     */
    public List<ListeProduit> getListeProduit() {
        return listeProduit;
    }

    /**
     * @param listeProduit the listeProduit to set
     */
    public void setListeProduit(List<ListeProduit> listeProduit) {
        this.listeProduit = listeProduit;
    }

    /**
     * @return the prixTotal
     */
    public Double getPrixTotal() {
        return prixTotal;
    }

    /**
     * @param prixTotal the prixTotal to set
     */
    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }
}
