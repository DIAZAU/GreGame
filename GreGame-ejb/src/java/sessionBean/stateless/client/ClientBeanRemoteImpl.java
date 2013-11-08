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

/**
 *
 * @author FALL
 */
@Stateless
public class ClientBeanRemoteImpl implements ClientBeanRemote{
    
    @PersistenceContext(unitName = "GreGame_Persistence")
    private EntityManager em;

    @Override
    public Client findClient(int idClient) {
        Client client = em.find(Client.class, idClient);
        client.getListAvis().size();
        return client;
    }

    @Override
    public void deleteClient(int idClient) {
        Client client = em.find(Client.class, idClient);
        em.remove(client);
    }

    @Override
    public List<Client> findClients() {
        List<Client> clients = null;
        clients = em.createNamedQuery("Client.findAll").getResultList();
        return clients;
    }

    @Override
    public List<Adresse> findAdresses() {
        List<Adresse> adresses = em.createNamedQuery("Adresse.findAll").getResultList();
        return adresses;
    }

    @Override
    public Adresse getAdresseToClient(Client client) {
        StringBuilder query = new StringBuilder("SELECT a FROM Adresse WHERE id IN "
                + "SELECT c.adresse.idAdresse FROM Client c WHERE c.idClient LIKE :idClient");
        Query searchAdresse;
        searchAdresse = em.createNamedQuery(query.toString());
        searchAdresse.setParameter("idClient", client.getIdClient());
        return (Adresse)searchAdresse.getSingleResult();
    }
    
}
