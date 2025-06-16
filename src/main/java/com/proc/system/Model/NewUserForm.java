package com.proc.system.Model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PROEMP")
@Repository
public class NewUserForm {


    @Id
    @Column(name="EMPID" ,nullable=false)
    private Integer empId;

    @Column(name="FIRSTNAME" ,nullable=false)
    private String firstName;

    @Column(name="LASTNAME" ,nullable=false)
    private String lastName;

    @Column(name="PASSWORD" ,nullable=false)
    private String password;

    @Transient
    private String confirmPassword;

       @OneToMany(mappedBy = "user")
       List<PurReqObject> listofPurReq=new ArrayList<>();

    public NewUserForm(){

    }

    public NewUserForm(Integer empId,String firstName,String lastName,String password){
       this.empId=empId;
       this.firstName=firstName;
       this.lastName=lastName;
       this.password=password;


    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}
