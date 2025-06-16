package com.proc.system.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SfppId implements Serializable {

    public Integer getItemCode() {
        return ItemCode;
    }

    @Column(name="ITEMCODE")
    private Integer ItemCode;

    public Integer getSupplierId() {
        return supplierId;
    }

    @Column(name="SUPPLIERID")
    private Integer supplierId;

    public void setItemCode(Integer itemCode) {
        ItemCode = itemCode;
    }


    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }


    public SfppId(Integer ItemCode,Integer supplierId){
        this.ItemCode=ItemCode;
        this.supplierId=supplierId;

    }

    public SfppId(){

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SfppId sfppId = (SfppId) o;
        return Objects.equals(ItemCode, sfppId.ItemCode) && Objects.equals(supplierId, sfppId.supplierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ItemCode, supplierId);
    }


}
