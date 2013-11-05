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
 public void createAvis(Avis avis);
 public void deleteAvis(int idAvis);
 public Avis searchAvis(int idAvis);
 public List<Avis>  searchAllAvis();
 public void updateAvis(int idAvis, String massage);
}

