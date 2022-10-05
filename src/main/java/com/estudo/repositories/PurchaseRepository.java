package com.estudo.repositories;

import com.estudo.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    List<PurchaseEntity> findAllByClientIdOrderByDateDesc(Long clientId);
}
