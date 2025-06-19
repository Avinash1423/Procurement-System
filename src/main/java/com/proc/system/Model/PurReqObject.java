package com.proc.system.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="PURREQ")
public class PurReqObject {

    public Integer getPurReqId() {
        return purReqId;
    }

    public void setPurReqId(Integer purReqId) {
        this.purReqId = purReqId;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PURREQID",nullable = false)
    Integer purReqId;

    public LocalDateTime getReqdate() {
        return reqdate;
    }

    public void setReqdate(LocalDateTime reqdate) {
        this.reqdate = reqdate;
    }

    @Column(name="REQDATE",nullable = false,insertable = false,updatable = false)
    LocalDateTime reqdate;

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name="ITEM",nullable = false)
     Integer item;

    @Column(name="QUANTITY",nullable = false)
    Integer quantity;

    @Column(name="STATUS",nullable = false,insertable = false,updatable = false)
    String status;

    public Integer getRaisedBy() {
        return raisedBy;
    }

    public void setRaisedBy(Integer raisedBy) {
        this.raisedBy = raisedBy;
    }

    @Column(name="RAISEDBY",nullable = false)
    Integer raisedBy;

      @ManyToOne
      @JoinColumn(name="ITEM",insertable = false,updatable = false,foreignKey=@ForeignKey(name="fk_PR_item"))
      ItemObject itemFromPurReq;

      @ManyToOne
      @JoinColumn(name="RAISEDBY",insertable = false,updatable = false,foreignKey=@ForeignKey(name="fk_PR_user"))
      NewUserForm user;

    public PurReqObject(Integer item,Integer Quantity,Integer raisedBy){

            this.item=item;
            this.quantity=Quantity;
            this.raisedBy=raisedBy;

    }

    public PurReqObject(){


    }


}
