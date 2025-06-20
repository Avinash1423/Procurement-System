package com.proc.system.Controller;

import com.proc.system.Model.SupplierObjectRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GoToSupplierContoller2 {

    SupplierObjectRepository supplierObjectRepository;

    public GoToSupplierContoller2(SupplierObjectRepository supplierObjectRepository) {

        this.supplierObjectRepository=supplierObjectRepository;
    }

    @PostMapping("/newSupplierPhone")
    public String newSupplierPhone(@RequestParam(required = false) String phone , Model model,HttpSession session) {

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "adminLoginPage";
        }

        try {
            supplierObjectRepository.updateSupplierPhone(phone, (Integer) session.getAttribute("selectedSupplier"));
        }catch(Exception e)
        {
            model.addAttribute("dataIntegrityError","Data Entered is Invalid,try again.");
            model.addAttribute("selectedSupplier",session.getAttribute("selectedSupplier"));
            return "updateSupplierPhone";

        }

        model.addAttribute("listOfSuppliers", supplierObjectRepository.findAll());
        return "ManageSuppliers";

    }

    @PostMapping("/newSupplierName")
    public String newSupplierName(@RequestParam(required = false) String name,Model model,HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "adminLoginPage";
        }

try {
    supplierObjectRepository.updateSupplierName(name, (Integer) session.getAttribute("selectedSupplier"));
}catch(Exception e){

    model.addAttribute("dataIntegrityError","Data Entered is Invalid,try again.");
    model.addAttribute("selectedSupplier",session.getAttribute("selectedSupplier"));
    return "updateSupplierPhone";
}
        model.addAttribute("listOfSuppliers", supplierObjectRepository.findAll());
        return "ManageSuppliers";

    }

    @PostMapping("/newSupplierEmail")
    public String newSupplierEmail(@RequestParam(required = false) String email, Model model, HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "adminLoginPage";
        }

try {
    supplierObjectRepository.updateSupplierEmail(email, (Integer) session.getAttribute("selectedSupplier"));
} catch (Exception e) {
    model.addAttribute("dataIntegrityError","Data Entered is Invalid,try again.");
    model.addAttribute("selectedSupplier",session.getAttribute("selectedSupplier"));
    return "updateSupplierEmail";
}

        model.addAttribute("listOfSuppliers", supplierObjectRepository.findAll());
        return "ManageSuppliers";

    }

}

