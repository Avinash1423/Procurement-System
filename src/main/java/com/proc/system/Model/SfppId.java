package com.proc.system.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SfppId implements Serializable {

    public Integer getItemCode() {
        return itemCode;
    }

    @Column(name="ITEMCODE")
    private Integer itemCode;

    public Integer getSupplierId() {
        return supplierId;
    }

    @Column(name="SUPPLIERID")
    private Integer supplierId;

    public void setItemCode(Integer itemCode) {
        itemCode = itemCode;
    }


    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }


    public SfppId(Integer itemCode,Integer supplierId){
        this.itemCode=itemCode;
        this.supplierId=supplierId;

    }

    public SfppId(){

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SfppId sfppId = (SfppId) o;
        return Objects.equals(itemCode, sfppId.itemCode) && Objects.equals(supplierId, sfppId.supplierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode, supplierId);
    }


}
