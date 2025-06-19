package com.proc.system.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="SUPPLIERS")
public class SupplierObject {

    public SupplierObject(){


    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLIERID",nullable = false)
    private  Integer supplierId;


    @Column(name = "NAME",nullable = false)
    private String  name;

    @Column(name = "EMAIL",nullable = false,unique = true)
    private String  email;


    @Column(name = "PHONENUMBER",nullable = false,unique = true)
    private String  phoneNumber;

    @OneToMany(mappedBy = "supplier")
    private List<SfppObject> supplierOffers=new ArrayList<>();

    @OneToMany(mappedBy="supplierFromPurOrder")
     List<PurchaseOrderObject> listOfSuppliers=new ArrayList<>();

    public SupplierObject(String name,String email,String phoneNumber){
      this.email=email;
      this.name=name;
      this.phoneNumber=phoneNumber;

    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public Integer getSupplierId() {
        return supplierId;
    }


}
