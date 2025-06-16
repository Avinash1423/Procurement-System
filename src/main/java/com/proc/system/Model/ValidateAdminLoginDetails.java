package com.proc.system.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ValidateAdminLoginDetails{

    AdminRepository AdminRepository;

    @Autowired
    public ValidateAdminLoginDetails(AdminRepository AdminRepository){
        this.AdminRepository=AdminRepository;

    }

    public boolean validate(String adminId) {

        return AdminRepository.findById(adminId).isPresent();
    }

    public boolean checkPassword(String adminId ,String password){

        AdminObject admin=AdminRepository.findById(adminId).get();
        return password.equals(admin.getAdminPassword());


    }
}
