/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import entityBean.Avis;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessionBean.stateless.avis.AvisBeanLocal;

/**
 *
 * @author FALL
 */
public class AvisBean  implements Serializable{
    
    @EJB
    private AvisBeanLocal avisBean;
    private Avis avis;
    private List<Avis> listAvis = new ArrayList<Avis>();
    private int idClient;
    private int idProduit;

    /**
     * Creates a new instance of AvisBean
     */
    public AvisBean() {
        avis = new Avis();
    }
    
    public String creerAvis(){
        avisBean.createAvis(avis);
        return "avis.cree";
    }

    /**
     * @return the avisBean
     */
    
    
    public AvisBeanLocal getAvisBean() {
        return avisBean;
    }

    /**
     * @param avisBean the avisBean to set
     */
    public void setAvisBean(AvisBeanLocal avisBean) {
        this.avisBean = avisBean;
    }

    /**
     * @return the listAvis
     */
    public List<Avis> getListAvis() {
        String param1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        String param2 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idClient");
        System.out.println(param1+param2);
        setListAvis(avisBean.searchAllAvis(1));
        return listAvis;
    }

    /**
     * @param listAvis the listAvis to set
     */
    public void setListAvis(List<Avis> listAvis) {
        this.listAvis = listAvis;
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
     * @return the avis
     */
    public Avis getAvis() {
        return avis;
    }

    /**
     * @param avis the avis to set
     */
    public void setAvis(Avis avis) {
        this.avis = avis;
    }
}
