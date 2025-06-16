package com.proc.system.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SfppRepository extends JpaRepository<SfppObject,SfppId>{

    @Query( "SELECT  new com.proc.system.Model.SfppDisplayDTO(s.sfppId.ItemCode,i.itemName,s.sfppId.supplierId,sup.name,s.price) "+
            "FROM SfppObject s " +
            "LEFT JOIN ItemObject i ON s.sfppId.ItemCode=i.itemCode "+
            "LEFT JOIN SupplierObject  sup ON s.sfppId.supplierId=sup.supplierId")
    List<SfppDisplayDTO> getAllSfppViews();

//    SELECT
//    s.ItemCode,
//    i.itemName,
//    s.SupplierId,
//    sup.Name,
//    s.Price
//    FROM Sfpp s
//    LEFT JOIN Items i
//    ON s.ItemCode=i.itemCode
//    LEFT JOIN suppliers  sup
//    ON s.supplierId=sup.supplierId;

 @Query("SELECT new  com.proc.system.Model.SfppObject(s.sfppId.ItemCode,s.sfppId.supplierId,s.price) "+
        "FROM SfppObject s "+
         "WHERE s.sfppId.ItemCode = :Id "
 )
 List<SfppObject> findByitem(@Param("Id")Integer itemId);

    //SELECT * FROM SfppObject WHERE itemId=


}
