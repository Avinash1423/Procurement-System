package com.proc.system.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="PURCHASEORDERS")
public class PurchaseOrderObject {

    public PurchaseOrderObject(){


    }



    @Id
     @Column(name="PONUMBER",nullable = false)
     Integer poNumber;

    @Column(name="ITEMID",nullable = false)
    Integer itemId;


    @Column(name="ITEMNAME",nullable = false)
    String itemName;


    @Column(name="ITEMQUANTITY",nullable = false)
    Integer itemQuantity;



    @Column(name="SUPPLIERID",nullable = false)
    Integer supplierId;


    @Column(name="UNITPRICE",nullable = false)
    Integer unitPrice;


    @Column(name="STATUS",nullable = false,insertable = false)
    String  status;

    @Column(name="PODATE",nullable = false,insertable = false)
    LocalDateTime poDate;

    public PurchaseOrderObject(Integer poNumber,Integer itemId,String itemName, Integer itemQuantity,Integer supplierId, Integer unitPrice){
        this.poNumber=poNumber;
        this.itemId=itemId;
        this.itemName=itemName;
        this.itemQuantity=itemQuantity;
        this.supplierId=supplierId;
        this.unitPrice=unitPrice;

    }
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public LocalDateTime getPoDate() {
        return poDate;
    }

    public void setPoDate(LocalDateTime poDate) {
        this.poDate = poDate;
    }

    public Integer getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(Integer poNumber) {
        this.poNumber = poNumber;
    }

}

