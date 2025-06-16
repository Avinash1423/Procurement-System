package com.proc.system.Controller;

import com.proc.system.Model.GetAllUsers;
import com.proc.system.Model.NewUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ManageUsersController {

    GetAllUsers getAllUsers;

    public ManageUsersController( GetAllUsers getAllUsers){

        this.getAllUsers=getAllUsers;

    }

    @GetMapping("/addNewUser")
    public String addNewUser(){

        return "addNewUser";
    }
    @GetMapping("/deleteExistingUser")
    public String deleteExistingUser() {

        return "deleteUser";
    }

    @GetMapping("/viewAllUsers")
    public String viewAllUsers(Model model){

        List<NewUserForm> listOfAllUsers= getAllUsers.getAllUsers();
        model.addAttribute( "listOfAllUsers",listOfAllUsers);
      //  model.addAttribute("listObject",new NewUserForm());

        return "viewAllUsers";

    }


}
