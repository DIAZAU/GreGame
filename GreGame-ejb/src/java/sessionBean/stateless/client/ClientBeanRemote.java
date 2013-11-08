package sessionBean.stateless.client;

import entityBean.Adresse;
import entityBean.Client;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author FALL
 */
@Remote
public interface ClientBeanRemote {

    public Client findClient(int idClient);

    public void deleteClient(int idClient);

    public List<Client> findClients();

    public List<Adresse> findAdresses();
    
    public Adresse getAdresseToClient(Client client);
    
}
