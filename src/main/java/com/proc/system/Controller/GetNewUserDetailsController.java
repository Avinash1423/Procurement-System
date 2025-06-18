package com.proc.system.Controller;

import com.proc.system.Model.GetAllUsers;
import com.proc.system.Model.NewUserForm;
import com.proc.system.Model.NewUserManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GetNewUserDetailsController  {

    NewUserManager newUserManager;
    GetAllUsers getAllUsers;
    BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();

  @Autowired
  public GetNewUserDetailsController(NewUserManager NewUserManager,GetAllUsers getAllUsers){
      this.newUserManager =NewUserManager;
      this.getAllUsers=getAllUsers;
  }

    @PostMapping("/getNewUserDetails")
    public String getDetails(@ModelAttribute NewUserForm NewUserForm, Model model , HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }
        Integer empId=NewUserForm.getEmpId();

        String firstName=NewUserForm.getFirstName();

        String lastName=NewUserForm.getLastName();

        String password=NewUserForm.getPassword();

        String confirmPassword=NewUserForm.getConfirmPassword();

if(!newUserManager.validateExists(empId)) {
    if (password.equals(confirmPassword)) {

        String hashedPassword = bCryptPasswordEncoder.encode(password);

        try {
            newUserManager.insertNewUser(empId, firstName, lastName, hashedPassword);
        } catch (Exception e) {
            e.printStackTrace();

            model.addAttribute("dbError", "Input is invalid or incomplete.");
            return "addNewUser";

        }
    } else {

        model.addAttribute("passwordMismatchError", "The passwords do not match");
        return "addNewUser";
    }
} else{

    model.addAttribute("idExistsError","The employee already exists");
    return "addNewUser";

}

        List<NewUserForm> listOfAllUsers= getAllUsers.getAllUsers();
        model.addAttribute( "listOfAllUsers",listOfAllUsers);
        //  model.addAttribute("listObject",new NewUserForm());

        return "viewAllUsers";
    }

}
