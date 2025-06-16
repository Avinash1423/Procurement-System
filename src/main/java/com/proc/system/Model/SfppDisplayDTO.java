package com.proc.system.Model;

import org.springframework.stereotype.Repository;


public class SfppDisplayDTO {

    private Integer itemCode;
    private String itemName;
    private Integer supplierId;
    private String name;
    private Integer price;

    public Integer getItemCode() {
        return itemCode;
    }

    public void setItemCode(Integer itemCode) {
        this.itemCode = itemCode;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public SfppDisplayDTO(Integer itemCode,String itemName,Integer supplierId,String name,Integer price) {
        this.itemCode = itemCode;
        this.itemName=itemName;
        this.supplierId=supplierId;
        this.name=name;
        this.price=price;

    }





}
