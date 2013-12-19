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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sessionBean.stateless.avis.AvisBeanLocal;
import sessionBean.stateless.catalogue.CatalogueBeanRemote;
import sessionBean.stateless.client.ClientBeanRemote;

/**
 *
 * @author FALL
 */
public class AvisBean  implements Serializable{
    
    @EJB
    private AvisBeanLocal avisBean;
    @EJB
    private ClientBeanRemote clientBean;
    @EJB
    private CatalogueBeanRemote catalogueBean;
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
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            String param1 = (String)fc.getExternalContext().getSessionMap().get("idPro");
            String param2 = (String)fc.getExternalContext().getSessionMap().get("idClient");
            setIdClient(Integer.parseInt(param2));
            setIdProduit(Integer.parseInt(param1));
            avis.setClient(clientBean.findClient(idClient));
            avis.setProduit(catalogueBean.findProduit(idProduit));
            avis.setNoteAvis(1);
            avisBean.createAvis(avis);
            return "avis.cree";
        } catch (NumberFormatException e) {
            String msg = "Veuillez vous connecté pour pouvoir donner un avis";
            FacesMessage facesMsg = new FacesMessage();
            facesMsg.setDetail(msg);
            facesMsg.setSeverity(FacesMessage.SEVERITY_INFO);
            fc.addMessage(null, facesMsg);
            return "";
        }
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
        FacesContext fc = FacesContext.getCurrentInstance();
        String param1 = (String)fc.getExternalContext().getSessionMap().get("idPro");
        setListAvis(avisBean.searchAllAvis(Integer.parseInt(param1)));
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

    /**
     * @return the clientBean
     */
    public ClientBeanRemote getClientBean() {
        return clientBean;
    }

    /**
     * @param clientBean the clientBean to set
     */
    public void setClientBean(ClientBeanRemote clientBean) {
        this.clientBean = clientBean;
    }

    /**
     * @return the catalogueBean
     */
    public CatalogueBeanRemote getCatalogueBean() {
        return catalogueBean;
    }

    /**
     * @param catalogueBean the catalogueBean to set
     */
    public void setCatalogueBean(CatalogueBeanRemote catalogueBean) {
        this.catalogueBean = catalogueBean;
    }
}
