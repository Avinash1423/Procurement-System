package com.proc.system.Controller;

import com.proc.system.Model.SupplierObject;
import com.proc.system.Model.SupplierObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddNewSupplierController {

    SupplierObjectRepository supplierObjectRepository;

    @Autowired
    public  AddNewSupplierController (SupplierObjectRepository supplierObjectRepository){

        this.supplierObjectRepository=supplierObjectRepository;
    }

@PostMapping("/addNewSupplier")
public  String addNewSupplier(@RequestParam String name, @RequestParam String email, @RequestParam String phoneNumber , Model model){

        if(name.isEmpty()||email.isEmpty()||phoneNumber.isEmpty()){

            model.addAttribute("emptyFieldError","Please Enter All Required field");

        }
        else{

            SupplierObject supplierObject=new SupplierObject(name,email,phoneNumber);
         try {
             supplierObjectRepository.save(supplierObject);
         }catch(Exception e){

             model.addAttribute("dataIntegrityError","Data Entered is Invalid,try again.");
             return "/addNewSupplier";
         }

        }

        return "redirect:/ManageSuppliers";
}


}
