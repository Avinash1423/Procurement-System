package com.proc.system.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GetAllUsers {

    NewUserFormRepository newUserFormRepository;

    @Autowired
    public GetAllUsers( NewUserFormRepository newUserFormRepository){
        this.newUserFormRepository=newUserFormRepository;

    }

    public List<NewUserForm> getAllUsers(){

        return newUserFormRepository.findAll();

    }

}
