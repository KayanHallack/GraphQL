package com.estudo.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.estudo.dto.ClientDTO;
import com.estudo.dto.PurchaseDTO;
import com.estudo.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientResolver implements GraphQLResolver<ClientDTO> {

    @Autowired
    PurchaseService purchaseService;

    public List<PurchaseDTO> purchaseHistory(ClientDTO client) {
         return purchaseService.getPurchaseHistory(client.getId());
    }
}
