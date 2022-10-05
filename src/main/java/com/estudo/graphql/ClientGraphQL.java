package com.estudo.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.estudo.dto.ClientDTO;
import com.estudo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    ClientService clientService;

    public List<ClientDTO> getClients(){
        return clientService.getClients();
    }

    public ClientDTO findClient(Long id){
        return clientService.findClient(id);
    }

    public ClientDTO persistClient(ClientDTO client){
        return clientService.persistClient(client);
    }

    public boolean deleteClient(Long id){
        return clientService.deleteClient(id);
    }
}
