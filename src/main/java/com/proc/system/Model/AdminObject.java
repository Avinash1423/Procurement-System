package com.proc.system.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.lang.NonNull;

@Entity
@Table(name="ADMINLOG")
public class AdminObject {

    @Id
    @Column(name="ADMINID" ,nullable = false)
    private  String adminId;

    @Column(name="ADMINPASSWORD",nullable = false)
    private String adminPassword;

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;

    }

}
