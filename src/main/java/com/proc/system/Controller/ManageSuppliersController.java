package com.proc.system.Controller;

import com.proc.system.Model.SfppRepository;
import com.proc.system.Model.SupplierObject;
import com.proc.system.Model.SupplierObjectRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@Transactional
public class ManageSuppliersController {

    private final SupplierObjectRepository supplierObjectRepository;
    private final SfppRepository sfppRepository;


    @Autowired
    public ManageSuppliersController(SupplierObjectRepository supplierObjectRepository, SfppRepository sfppRepository) {
        this.supplierObjectRepository = supplierObjectRepository;
        this.sfppRepository = sfppRepository;
    }


    @GetMapping("/addNewSupplier")
    public String addNewSupplier(HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }

        return "addNewSupplier";
    }

     @PostMapping("/goToSupplier")
     public String goToSupplier(@RequestParam(required = false) Integer supplierId , Model model,HttpSession session) {

         String role = (String) session.getAttribute("role");

         if (role == null || !role.equals("Admin")) {
             return "/adminLoginPage";
         }
         if (supplierId == null) {

             model.addAttribute("supplierNotSelectedError", "Supplier not Selected");//doesnt work
             model.addAttribute("listOfSuppliers", supplierObjectRepository.findAll());
             return "ManageSuppliers";

         } else {

             SupplierObject supplierObject = supplierObjectRepository.findById(supplierId).get();

             model.addAttribute("supplierId", supplierObject.getSupplierId());
             model.addAttribute("name", supplierObject.getName());
             model.addAttribute("phoneNumber", supplierObject.getPhoneNumber());
             model.addAttribute("email", supplierObject.getEmail());

             session.setAttribute("selectedSupplier", supplierObject.getSupplierId());


             return "GoToSupplier";

         }
     }

      @PostMapping("/deleteSupplier")
      public String deleteSupplier(@RequestParam Integer supplierId,HttpSession session,Model model){

          String role=(String)session.getAttribute("role");

          if ( role==null||!role.equals("Admin")) {
              return "adminLoginPage";
          }

      try {
          sfppRepository.deleteBySfppId_supplierId(supplierId);
          supplierObjectRepository.deleteById(supplierId);
        } catch(Exception e)
      {
          model.addAttribute("SupplierDeleteError","Cannot delete: Supplier may be associated with a purchase order or other records.");

      }

        return"redirect:/ManageSuppliers";


     }


}
