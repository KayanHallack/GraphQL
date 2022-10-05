package com.estudo.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.estudo.dto.ProductDTO;
import com.estudo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    ProductService productService;

    public List<ProductDTO> getProducts(){
        return productService.getProducts();
    }

    public ProductDTO findProduct(Long id){
        return productService.findProduct(id);
    }

    public ProductDTO persistProduct(ProductDTO client){
        return productService.persistProduct(client);
    }

    public boolean deleteProduct(Long id){
        return productService.deleteProduct(id);
    }
}
