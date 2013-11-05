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
        em.persist(avis);
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
    public List<Avis>  searchAllAvis() {
        return em.createNamedQuery("Avis.findAll").getResultList();
    }    

    @Override
    public Avis searchAvis(int idAvis) {
        Avis avis;
        avis=em.find(Avis.class,idAvis);
        return avis;
    }
}
