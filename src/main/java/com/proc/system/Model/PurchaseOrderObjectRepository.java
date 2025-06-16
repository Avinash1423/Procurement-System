package com.proc.system.Model;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderObjectRepository extends JpaRepository<PurchaseOrderObject,Integer> {

    List<PurchaseOrderObject> findByStatus(String status);

    @Modifying
    @Transactional
    @Query("UPDATE PurchaseOrderObject p SET p.status='Delivered' WHERE p.poNumber= :id ")

    void updateAsDelivered(@Param("id")Integer poNumber);

    @Modifying
    @Transactional
    @Query("UPDATE PurchaseOrderObject p SET p.status='Invoiced' WHERE p.poNumber= :id ")

    void updateAsInvoiced(@Param("id")Integer poNumber);

}
