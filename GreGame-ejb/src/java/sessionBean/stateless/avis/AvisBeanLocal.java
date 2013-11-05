/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.avis;

import entityBean.Avis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FALL
 */
@Local
public interface AvisBeanLocal {
 public void saveAvis(Avis avis);
 public void deleteAvis(Avis avis);
 public Avis getAvis(int avisID);
 public List<Avis>  getAllAvis();
  public void updateAvis(Avis avis);
}
