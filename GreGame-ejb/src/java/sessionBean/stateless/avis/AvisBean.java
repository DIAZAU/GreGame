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

    public void ajouterAvis(Avis avis) {
        em.persist(avis);
    }
    
    public void supprimerAvis(Avis avis) {
        em.remove(avis);
    }    

    public Avis getAvis(int avisID) {
        Avis avis;
        avis=em.find(Avis.class,avisID);
        return avis;
    }    
        public void modifierAvis(Avis avis) {
        em.merge(avis);
    }   
    
        public List<Avis>  getAllAvis() {
        return em.createNamedQuery("Avis.getAll").getResultList();
    }    
}
