package com.proc.system.Controller;

import com.proc.system.Model.SupplierObject;
import com.proc.system.Model.SupplierObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ManageSuppliersController {

    private final SupplierObjectRepository supplierObjectRepository;


    @Autowired
    public ManageSuppliersController(SupplierObjectRepository supplierObjectRepository) {
        this.supplierObjectRepository = supplierObjectRepository;
    }


    @GetMapping("/addNewSupplier")
    public String addNewSupplier(){

        return "addNewSupplier";
    }

     @PostMapping("/goToSupplier")
     public String goToSupplier(@RequestParam(required = false) Integer supplierId , Model model){

        if(supplierId==null){

            model.addAttribute("supplierNotSelectedError","Supplier not Selected");//doesnt work
            model.addAttribute("listOfSuppliers", supplierObjectRepository.findAll());
            return  "ManageSuppliers";

        }
        else {

            SupplierObject supplierObject = supplierObjectRepository.findById(supplierId).get();

            model.addAttribute("supplierId", supplierObject.getSupplierId());
            model.addAttribute("name", supplierObject.getName());
            model.addAttribute("phoneNumber", supplierObject.getPhoneNumber());
            model.addAttribute("email", supplierObject.getEmail());
        }

        return  "goToSupplier";

     }

      @PostMapping("/deleteSupplier")
      public String deleteSupplier(@RequestParam Integer supplierId){

        supplierObjectRepository.deleteById(supplierId);

        return"redirect:/ManageSuppliers";


     }


}
