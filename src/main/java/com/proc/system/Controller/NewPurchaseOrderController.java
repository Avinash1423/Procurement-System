package com.proc.system.Controller;

import com.proc.system.Model.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NewPurchaseOrderController {

    PurchaseOrderObjectRepository  purchaseOrderObjectRepository;
    PurReqObjectRepository purReqObjectRepository;
    SfppRepository sfppRepository;

    public  NewPurchaseOrderController(PurchaseOrderObjectRepository  purchaseOrderObjectRepository, PurReqObjectRepository purReqObjectRepository,SfppRepository sfppRepository){

        this.purchaseOrderObjectRepository=purchaseOrderObjectRepository;
        this.purReqObjectRepository=purReqObjectRepository;
        this.sfppRepository=sfppRepository;
    }

    @PostMapping("/newPo")
   public String newPo(@RequestParam(required = false) String supplierAndPrice, HttpSession session, Model model){
        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }

        Integer poNumber= (Integer)session.getAttribute("poNumber");
        Integer itemId= (Integer) session.getAttribute("itemId");
        String itemName=(String) session.getAttribute("itemName");
        Integer itemQuantity=(Integer)session.getAttribute("itemQuantity");
        Integer selectedReqId=(Integer)session.getAttribute("selectedReqId");
        List<SfppObject> listofSfppObject= sfppRepository.findByitem(itemId);

        if (supplierAndPrice==null){

//            Integer poNumber= (Integer)session.getAttribute("poNumber");
//            Integer itemId= (Integer) session.getAttribute("itemId");
//            String itemName=(String) session.getAttribute("itemName");
//            Integer itemQuantity=(Integer)session.getAttribute("itemQuantity");
//            Integer selectedReqId=(Integer)session.getAttribute("selectedReqId");

            model.addAttribute("poNumber",poNumber);
            model.addAttribute("itemId",itemId);
            model.addAttribute("itemName",itemName);
            model.addAttribute("itemQuantity",itemQuantity);
            model.addAttribute("listofSfppObject",listofSfppObject);

            return "createPO";

        }

//        Integer poNumber= (Integer)session.getAttribute("poNumber");
//        Integer itemId= (Integer) session.getAttribute("itemId");
//        String itemName=(String) session.getAttribute("itemName");
//        Integer itemQuantity=(Integer)session.getAttribute("itemQuantity");
//        Integer selectedReqId=(Integer)session.getAttribute("selectedReqId");

       // List<SfppObject> listofSfppObject= (List<SfppObject>) session.getAttribute("listofSfppObject");
        

        String[] split =supplierAndPrice.split(",");

        Integer supplierId=Integer.parseInt(split[0].trim());
        
        Integer price=Integer.parseInt(split[1].trim());
        



        PurchaseOrderObject purchaseOrderObject=new PurchaseOrderObject(poNumber,itemId,itemName,itemQuantity,supplierId,price);

        purchaseOrderObjectRepository.save(purchaseOrderObject);



        purReqObjectRepository.updateStatusAsApproved(selectedReqId);

        return"adminView";





   }




}


