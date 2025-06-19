package com.proc.system.Model;

import jakarta.persistence.*;

@Entity
@Table(name="SFPP")
public class SfppObject {

    public SfppId getSfppId() {
        return sfppId;
    }

    public void setSfppId(SfppId sfppId) {
        this.sfppId = sfppId;
    }

    @EmbeddedId
    private SfppId sfppId;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Column(name="PRICE")
    private Integer price;

    public ItemObject getItem() {
        return item;
    }

    @ManyToOne
    @MapsId("itemCode")
    @JoinColumn(name="ITEMCODE" ,insertable = false,updatable = false,foreignKey = @ForeignKey(name="fk_sfpp_item"))
    private ItemObject item;

    public SupplierObject getSupplier() {
        return supplier;
    }

    @ManyToOne
    @MapsId("supplierId")
    @JoinColumn(name="SUPPLIERID",insertable = false,updatable = false,foreignKey = @ForeignKey(name="fk_sfpp_sup"))
    private SupplierObject supplier;


//    @Column(name="ITEMCODE")
//    private Integer itemCode;
//
//    @Column(name="SUPPLIERID")
//    private Integer supplierId;

    public SfppObject() {

    }

    public SfppObject(Integer itemCode,Integer supplierId,Integer price){
      this.sfppId=new SfppId();
      sfppId.setItemCode(itemCode);
      sfppId.setSupplierId(supplierId);
      this.price=price;

    }

    public SfppObject(ItemObject item,SupplierObject supplier,Integer price){

        this.price=price;
        this.item=item;
        this.supplier=supplier;
        this.sfppId= new SfppId(item.getItemCode(), supplier.getSupplierId());


    }


}
