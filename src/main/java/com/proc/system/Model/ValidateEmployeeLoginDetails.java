package com.proc.system.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class ValidateEmployeeLoginDetails{

   NewUserFormRepository newUserFormRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();

    @Autowired
    public ValidateEmployeeLoginDetails(NewUserFormRepository newUserFormRepository){
        this.newUserFormRepository=newUserFormRepository;

    }

    public boolean validate(Integer EmpId) {

        return newUserFormRepository.findById(EmpId).isPresent();
    }

    public boolean checkPassword(Integer EmpId ,String rawPassword){

        NewUserForm newUserForm=newUserFormRepository.findById(EmpId).get();

        String StoredPassword=newUserForm.getPassword();

       return bCryptPasswordEncoder.matches(rawPassword,StoredPassword);




    }
}
