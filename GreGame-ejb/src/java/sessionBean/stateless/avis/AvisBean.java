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
    public void saveAvis(Avis avis) {
        em.persist(avis);
    }
    
    @Override
    public void deleteAvis(Avis avis) {
        em.remove(avis);
    }    

    @Override
    public Avis getAvis(int avisID) {
        Avis avis;
        avis=em.find(Avis.class,avisID);
        return avis;
    }    
    @Override
        public void updateAvis(Avis avis) {
        em.merge(avis);
    }   
    
        public List<Avis>  getAllAvis() {
        return em.createNamedQuery("Avis.getAll").getResultList();
    }    
}
