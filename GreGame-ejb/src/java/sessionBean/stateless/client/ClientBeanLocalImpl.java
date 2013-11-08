/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean.stateless.client;

import entityBean.Adresse;
import entityBean.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ValidationException;

/**
 *
 * @author FALL && LABED
 */
@Stateless
public class ClientBeanLocalImpl implements ClientBeanLocal{
    
    @PersistenceContext(unitName = "GreGame_Persistence")
    private EntityManager em;

    @Override
    public Client authenticate(String login, String password) {
        if (login == null || "".equals(login)) {
            throw new ValidationException("Invalid login");
        }
        Query query = em.createNamedQuery("Client.findClient");
        query.setParameter("login", login);
        query.setParameter("password", password);
        //il n'est qu'un seul client avec unseul login et un mot de passe
        if (query.getSingleResult() != null){
            return (Client) query.getSingleResult();
        }
        else
            throw  new ValidationException("Indentifiant error");
    }

    @Override
    public Client createClient(Client client) {
        if (client == null ) {
            throw new ValidationException("Client object is null or Adresse object is null");
        }
        List<Client> clients = em.createNamedQuery("Client.findByLogin").setParameter("login", client.getLogin()).getResultList();
        if (!clients.isEmpty())
            throw  new ValidationException("Le login existe");
        else
            em.persist(client);
        return client;
    }


    @Override
    public Client updateClient(int idClient, Client client) {
        Client clientToUpdate = em.find(Client.class, idClient);
        clientToUpdate.setAdrClient(client.getAdrClient());
        clientToUpdate.setAdrLivraison(client.getAdrLivraison());
        clientToUpdate.setMotDepasse(client.getMotDepasse());
        clientToUpdate.setNomClient(client.getNomClient());
        clientToUpdate.setPrenomClient(client.getPrenomClient());
        return clientToUpdate;
    }


    @Override
    public void deleteClient(int idClient) {
        Client client = em.find(Client.class, idClient);
        em.remove(client);
    }

    @Override
    public Adresse createAdresse(Adresse adresse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
