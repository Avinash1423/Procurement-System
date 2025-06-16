package com.proc.system.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NewUserManager {

    NewUserFormRepository newUserFormRepository;

    @Autowired
    public NewUserManager(NewUserFormRepository NewUserFormRepository){

        this.newUserFormRepository =NewUserFormRepository;
    }

    public void insertNewUser(Integer empId,String firstName,String lastName,String password){

        NewUserForm user=new NewUserForm(empId,firstName,lastName,password);

        newUserFormRepository.save(user);

    }

    public boolean validateExists(Integer empId){

        return newUserFormRepository.findById(empId).isPresent();
    }


}
