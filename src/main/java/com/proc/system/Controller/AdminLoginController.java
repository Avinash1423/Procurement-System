package com.proc.system.Controller;

import com.proc.system.Model.AdminObject;
import com.proc.system.Model.AdminRepository;
import com.proc.system.Model.GetAllUsers;
import com.proc.system.Model.ValidateAdminLoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminLoginController {

    private final AdminRepository adminRepository;
    ValidateAdminLoginDetails validateAdminLoginDetails;


    @Autowired
    public AdminLoginController(ValidateAdminLoginDetails validateAdminLoginDetails, AdminRepository adminRepository){
        this.validateAdminLoginDetails=validateAdminLoginDetails;
        this.adminRepository = adminRepository;
    }
    @GetMapping("/adminLogin")
    public String adminLoginMethod() {

        return "adminLoginPage";

    }

    @PostMapping("/validateLogin")

    public String getAdminLoginDetails(@RequestParam String adminId, @RequestParam String adminPassword ,Model model) {


        if(adminId.isEmpty()||adminPassword.isEmpty()){

            model.addAttribute("emptyFieldError","Complete all required Fields");

            return "adminLoginPage";

        }

        if (!validateAdminLoginDetails.validate(adminId)){
            model.addAttribute("accountDoesNotExistError","Account doesn't exist. Try again.");
            return "adminLoginPage";
        }
        else if (!validateAdminLoginDetails.checkPassword(adminId,adminPassword)) {

            model.addAttribute("passwordIncorrectError", "Password is incorrect. Try Again.");
            return "adminLoginPage";
        }
        else

            return  "adminView";
        }




    }

