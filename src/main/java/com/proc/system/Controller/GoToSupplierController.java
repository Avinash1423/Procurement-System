package com.proc.system.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoToSupplierController {

    public GoToSupplierController(){


    }

    @GetMapping("/updateSupplierEmail")
    public  String updateSupplierEmail(HttpSession session, Model model){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "adminLoginPage";
        }

        model.addAttribute("selectedSupplier",session.getAttribute("selectedSupplier"));

        return "updateSupplierEmail";

    }




    @GetMapping("/updateSupplierName")
    public  String updateSupplierName(HttpSession session, Model model){
        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }

        model.addAttribute("selectedSupplier",session.getAttribute("selectedSupplier"));
        return "updateSupplierName";

    }

    @GetMapping("/updateSupplierPhone")
    public  String updateSupplierPhone(HttpSession session,Model model){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }
        model.addAttribute("selectedSupplier",session.getAttribute("selectedSupplier"));
        return "updateSupplierPhone";
    }

}
