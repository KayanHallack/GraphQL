package com.estudo.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.estudo.dto.PurchaseDTO;
import com.estudo.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    PurchaseService purchaseService;

    public Page<PurchaseDTO> getPurchases(Integer page, Integer size) {
        return purchaseService.getPurchases(page, size);
    }

    public List<PurchaseDTO> getPurchaseHistory(Long clientId) {
        return purchaseService.getPurchaseHistory(clientId);
    }

    public PurchaseDTO purchase(PurchaseDTO purchase) {
        return purchaseService.purchase(purchase);
    }
}
