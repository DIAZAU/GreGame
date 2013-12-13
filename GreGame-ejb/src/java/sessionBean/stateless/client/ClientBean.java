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
 * @author FALL
 */
@Stateless
public class ClientBean implements ClientBeanLocal, ClientBeanRemote{
    
    @PersistenceContext(unitName = "GreGame_Persistence")
    private EntityManager em;

    @Override
    public Client authenticate(String login, String password) throws ValidationException{
        
        Query query = em.createNamedQuery("Client.findClient");
        query.setParameter("emailClient", login);
        query.setParameter("password", password);
        //il n'est qu'un seul client avec unseul login et un mot de passe
        if (query.getSingleResult() != null){
            return (Client) query.getSingleResult();
        }
        else
            throw  new ValidationException("Identifiants non reconnues");
    }

    @Override
    public Client createClient(Client client) throws ValidationException{
        if (client == null ) {
            throw new ValidationException("Client object is null or Adresse object is null");
        }
        List<Client> clients = em.createNamedQuery("Client.findByEmail").setParameter("emailClient", client.getEmailClient()).getResultList();
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
        if (adresse != null){
            em.persist(adresse);
            return adresse;
        }
        return null;
    }
    
    @Override
    public Client findClient(int idClient) {
        Client client = em.find(Client.class, idClient);
        return client;
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
