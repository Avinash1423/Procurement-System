package com.proc.system.Controller;

import com.proc.system.Model.GetAllUsers;
import com.proc.system.Model.NewUserForm;
import jakarta.servlet.http.HttpSession;
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
    public String addNewUser(HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }
        return "addNewUser";
    }
    @GetMapping("/deleteExistingUser")
    public String deleteExistingUser(HttpSession session) {

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }
        return "deleteUser";
    }

    @GetMapping("/viewAllUsers")
    public String viewAllUsers(Model model,HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }
        List<NewUserForm> listOfAllUsers= getAllUsers.getAllUsers();
        model.addAttribute( "listOfAllUsers",listOfAllUsers);
      //  model.addAttribute("listObject",new NewUserForm());

        return "viewAllUsers";

    }


}
