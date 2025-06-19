package com.proc.system.Model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="PURCHASEORDERS")
public class PurchaseOrderObject {

    public PurchaseOrderObject(){


    }


    @Id
     @Column(name="PONUMBER",nullable = false)
     Integer poNumber;

//    @Column(name="ITEMID",nullable = false)
//    Integer itemId;


    @Column(name="ITEMNAME",nullable = false)
    String itemName;


    @Column(name="ITEMQUANTITY",nullable = false)
    Integer itemQuantity;


//    @Column(name="SUPPLIERID",nullable = false)
//    Integer supplierId;


    @Column(name="UNITPRICE",nullable = false)
    Integer unitPrice;

    @Column(name="STATUS",nullable = false,insertable = false)
    String  status;

    @Column(name="PODATE",nullable = false,insertable = false)
    LocalDateTime poDate;

    public SupplierObject getSupplierFromPurOrder() {
        return supplierFromPurOrder;
    }

    @ManyToOne //newly added
    @JoinColumn(name="SUPPLIERID",nullable = false,foreignKey = @ForeignKey(name= "fk_PO_Sup"))
    SupplierObject supplierFromPurOrder;

    public ItemObject getItemFromPurOrder() {
        return itemFromPurOrder;
    }

    @ManyToOne//newly added
    @JoinColumn(name="ITEMID",nullable=false,foreignKey = @ForeignKey(name="fk_PO_Item"))
    ItemObject itemFromPurOrder;

    public PurchaseOrderObject(Integer poNumber,ItemObject item,String itemName, Integer itemQuantity,SupplierObject supplier, Integer unitPrice){
        this.poNumber=poNumber;
        this.itemFromPurOrder=item;
        this.itemName=itemName;
        this.itemQuantity=itemQuantity;
        this.supplierFromPurOrder=supplier;
        this.unitPrice=unitPrice;

    }
//    public Integer getItemId() {
//        return item;
//    }
//
//    public void setItemId(Integer itemId) {
//        this.item = itemId;
//    }

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

//    public Integer getSupplierId() {
//        return supplierId;
//    }
//
//    public void setSupplierId(Integer supplierId) {
//        this.supplierId = supplierId;
//    }

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

