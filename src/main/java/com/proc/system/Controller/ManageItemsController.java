package com.proc.system.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageItemsController {


    public ManageItemsController(){

    }

    @GetMapping("/addNewItem")
    public String addNewItem(HttpSession session){
        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }

        return "addNewItem";

    }



}
