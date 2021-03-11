package ru.sapteh;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.Client;
import ru.sapteh.entity.ClientService;
import ru.sapteh.service.ClientDaoImpl;


import java.util.*;

public class MainDao {
    public static void main(String[] args) {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    DAO<Client, Integer> clientDaoImpl = new ClientDaoImpl(factory);

    List<Client> clients = clientDaoImpl.findByAll();

        for(Client client : clients) {

            Set<ClientService> clientServiceSet = client.getClientServiceSet();
            if (clientServiceSet.size() != 0)

                for (ClientService clientService : clientServiceSet) {
                    Date startTime = clientService.getStartTime();
                    System.out.println(startTime);
                }
        }
    }
}
