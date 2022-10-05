package com.estudo.services;

import com.estudo.dto.ProductDTO;
import com.estudo.entities.ProductEntity;
import com.estudo.exceptions.NotFoundException;
import com.estudo.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;


    public List<ProductDTO> getProducts() {
        List<ProductEntity> products = productRepository.findAll();

        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Cacheable(value = "product", key = "#id")
    public ProductDTO findProduct(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product Not Found"));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Transactional
    @CachePut(value = "product", key = "#product.id")
    public ProductDTO persistProduct(ProductDTO product) {
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        productRepository.save(productEntity);

        return modelMapper.map(productEntity, ProductDTO.class);
    }

    @CacheEvict(value = "product", key = "#id")
    public boolean deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
