/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.avis;

import entityBean.Avis;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FALL
 */
@Stateless
public class AvisBean implements AvisBeanLocal {
    @PersistenceContext(unitName = "GreGame_Persistence")
    private EntityManager em;


    @Override
    public void createAvis(Avis avis) {
        if (avis != null){
            em.persist(avis);
            System.out.println("ok");
        }
    }
    

    @Override
    public void deleteAvis(int idAvis) {
        Avis avis = em.find(Avis.class, idAvis);
        em.remove(avis);
    }        

    @Override
    public void updateAvis(int idAvis, String message) {
        Avis avisToUpdate = em.find(Avis.class, idAvis);
        avisToUpdate.setMessageAvis(message);
    }   
    
    @Override
    public List<Avis>  searchAllAvis(int idProduit) {
        Query request = em.createNamedQuery("Avis.findAllByProduit");
        request.setParameter("produit", idProduit);
        return request.getResultList();
    }    

}
