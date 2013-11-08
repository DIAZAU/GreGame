package sessionBean.stateless.client;

import entityBean.Adresse;
import entityBean.Client;
import javax.ejb.Local;

/**
 *
 * @author LABED
 */
@Local
public interface ClientBeanLocal {
    
    public Client authenticate(String login, String password);
    
    public Adresse createAdresse(Adresse adresse);

    public Client createClient(Client client);
    
    public Client updateClient(int idClient, Client client);
    
    public void deleteClient(int idClient);

    
}
