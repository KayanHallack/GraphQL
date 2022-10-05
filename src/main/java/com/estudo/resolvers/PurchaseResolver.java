package com.estudo.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.estudo.dto.ClientDTO;
import com.estudo.dto.ProductDTO;
import com.estudo.dto.PurchaseDTO;
import com.estudo.services.ClientService;
import com.estudo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseResolver implements GraphQLResolver<PurchaseDTO> {

    @Autowired
    ClientService clientService;

    @Autowired
    ProductService productService;

    public ClientDTO client(PurchaseDTO purchase) {
        return clientService.findClient(purchase.getClientId());
    }

    public ProductDTO product(PurchaseDTO purchase) {
        return productService.findProduct(purchase.getProductId());
    }
}
