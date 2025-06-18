package com.proc.system.Model;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupplierObjectRepository extends JpaRepository<SupplierObject,Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE SupplierObject s SET s.email= :email WHERE s.supplierId = :id ")

    void updateSupplierEmail(@Param("email")String email,@Param("id")Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE SupplierObject s SET s.phoneNumber= :phone WHERE s.supplierId = :id ")

    void updateSupplierPhone(@Param("phone") String phone,@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(" UPDATE SupplierObject s SET s.name= :name WHERE s.supplierId = :id ")

    void updateSupplierName(@Param("name") String name,@Param("id") Integer id);
}
