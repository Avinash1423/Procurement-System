package com.proc.system.Controller;

import com.proc.system.Model.DeleteUser;
import com.proc.system.Model.NewUserForm;
import com.proc.system.Model.NewUserFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteUserDetails {

    NewUserFormRepository newUserFormRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    DeleteUser deleteUser;

    public DeleteUserDetails(DeleteUser deleteUser,NewUserFormRepository newUserFormRepository){

        this.deleteUser=deleteUser;
        this.newUserFormRepository=newUserFormRepository;

    }

    @PostMapping("/deleteUser")
    public String  delete (@RequestParam Integer empId, @RequestParam String password, @RequestParam String confirmPassword,Model model){

        System.out.println(password+" "+confirmPassword);

        if(!password.equals(confirmPassword)){

            model.addAttribute("passwordMismatchError","The passwords do not match");

            return"deleteUser";
        }
        else {
            if(newUserFormRepository.findById(empId).isPresent()){

             if(deleteUser.verifyPassword(empId,password)){

                 newUserFormRepository.deleteById(empId);

                 return "temp";
             }
             else{

                 model.addAttribute("passwordIncorrectError","Incorrect Password");
                 return "deleteUser";
             }
            }

            else{

                model.addAttribute("userDoesNotExistError","The User Does not Exist");
                return "deleteUser";
            }
        }
    // return"temp";
    }
}
