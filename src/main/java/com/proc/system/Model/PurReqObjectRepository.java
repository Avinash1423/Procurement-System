package com.proc.system.Model;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurReqObjectRepository extends JpaRepository<PurReqObject,Integer> {

  List<PurReqObject>findByRaisedBy(Integer raisedBy);

  List<PurReqObject>findByStatus(String status);

  @Modifying
  @Transactional
  @Query(" UPDATE PurReqObject p SET p.status='Approved' WHERE p.purReqId = :id")
  void updateStatusAsApproved(@Param("id") Integer id);

  @Modifying
  @Transactional
  @Query("UPDATE PurReqObject p SET p.status='Cancelled'WHERE p.purReqId = :id")
  void updateStatusAsCancelled(@Param("id")Integer id);

}
