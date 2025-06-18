package com.proc.system.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DeleteUser {

    NewUserFormRepository newUserFormRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DeleteUser(NewUserFormRepository newUserFormRepository ,BCryptPasswordEncoder bCryptPasswordEncoder){

         this.newUserFormRepository=newUserFormRepository;
         this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public boolean verifyPassword(Integer empId, String password) {

        NewUserForm newUserForm=newUserFormRepository.findById(empId).get();

        String actualPassword= newUserForm.getPassword();



        return bCryptPasswordEncoder.matches(password,actualPassword);


    }

}
