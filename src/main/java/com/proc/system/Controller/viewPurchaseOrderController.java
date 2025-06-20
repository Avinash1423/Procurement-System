package com.proc.system.Controller;

import com.proc.system.Model.PurchaseOrderObjectRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class viewPurchaseOrderController {

    PurchaseOrderObjectRepository purchaseOrderObjectRepository;
    public viewPurchaseOrderController(PurchaseOrderObjectRepository purchaseOrderObjectRepository){

        this.purchaseOrderObjectRepository=purchaseOrderObjectRepository;

    }

    @GetMapping("/viewPurchaseOrder")
    public String viewPurchaseOrder(Model model, HttpSession session){
        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }

     model.addAttribute("openPos",purchaseOrderObjectRepository.findByStatus("Open"));

        return "openPOs";
    }

    @PostMapping("/poAction")
    public String poAction(@RequestParam(required = false) Integer poNumber, Model model,HttpSession session){


        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }
        if(poNumber==null){

            model.addAttribute("openPos",purchaseOrderObjectRepository.findByStatus("Open"));

            return "openPOs";

        }
        else {

            purchaseOrderObjectRepository.updateAsDelivered(poNumber);
            model.addAttribute("openPos",purchaseOrderObjectRepository.findByStatus("Open"));
            return "openPOs";

        }
    }
    @GetMapping("/deliveredPos")
   public String deliveredPos(Model model,HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }

        model.addAttribute("DeliveredPos",purchaseOrderObjectRepository.findByStatus("Delivered"));

        return "/deliveredPos";

   }

    @PostMapping("/posInvoiced")
    public String posInvoiced(@RequestParam(required = false) Integer poNumber, Model model,HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }

        if(poNumber==null){

            model.addAttribute("openPos",purchaseOrderObjectRepository.findByStatus("Delivered"));

            return "/deliveredPos";

        }
        else {

            purchaseOrderObjectRepository.updateAsInvoiced(poNumber);
            model.addAttribute("DeliveredPos",purchaseOrderObjectRepository.findByStatus("Delivered"));
            return "/deliveredPos";

        }
    }

    @GetMapping("/viewInvoicedPos")
    public String viewInvoicedPos(Model model,HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }

        model.addAttribute("InvoicedPos",purchaseOrderObjectRepository.findByStatus("Invoiced"));
        return "/InvoicedPos";
    }
}


