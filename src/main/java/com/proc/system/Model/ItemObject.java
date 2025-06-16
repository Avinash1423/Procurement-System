package com.proc.system.Model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ITEMS")
public class ItemObject {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ITEMCODE",nullable = false)
    private Integer itemCode;


    @Column(name="ITEMNAME",nullable = false)
    private String  itemName;


    @Column(name="UNITOFMEASURE" ,nullable =false)
    private String unitOfMeasure;

    @OneToMany(mappedBy = "item")
    private List<SfppObject> itemOffers=new ArrayList<>();

     @OneToMany(mappedBy = "itemFromPurReq")
     List<PurReqObject> listofPurReqs=new ArrayList<>();

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

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public ItemObject(){


    }
    public ItemObject(String itemName, String unitOfMeasure){

         this.itemName=itemName;
         this.unitOfMeasure=unitOfMeasure;
    }


}
