package com.proc.system.Controller;

import com.proc.system.Model.NewUserForm;
import com.proc.system.Model.NewUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GetNewUserDetailsController  {

    NewUserManager newUserManager;
    BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();

  @Autowired
  public GetNewUserDetailsController(NewUserManager NewUserManager){
      this.newUserManager =NewUserManager;
  }

    @PostMapping("/getNewUserDetails")
    public String getDetails(@ModelAttribute NewUserForm NewUserForm, Model model ){


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

             return"temp";
    }

}
