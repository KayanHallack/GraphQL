package com.estudo.services;

import com.estudo.dto.PurchaseDTO;
import com.estudo.entities.PurchaseEntity;
import com.estudo.repositories.PurchaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ModelMapper modelMapper;


    public Page<PurchaseDTO> getPurchases(Integer page, Integer size) {
        Page<PurchaseEntity> purchasePage = purchaseRepository.findAll(PageRequest.of(page, size));

        return purchasePage.map(purchase -> modelMapper.map(purchase, PurchaseDTO.class));
    }

    @Cacheable(value = "purchase", key = "#clientId")
    public List<PurchaseDTO> getPurchaseHistory(Long clientId) {
        List<PurchaseEntity> purchases = purchaseRepository.findAllByClientIdOrderByDateDesc(clientId);

        return purchases.stream()
                .map(purchase -> modelMapper.map(purchase, PurchaseDTO.class))
                .collect(Collectors.toList());

    }

    @Transactional
    @CacheEvict(value = "purchase", key = "#purchase.clientId")
    public PurchaseDTO purchase(PurchaseDTO purchase) {
        PurchaseEntity purchaseEntity = modelMapper.map(purchase, PurchaseEntity.class);
        purchaseRepository.save(purchaseEntity);

        return modelMapper.map(purchaseEntity, PurchaseDTO.class);
    }
}
