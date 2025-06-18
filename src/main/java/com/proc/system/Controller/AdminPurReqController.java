package com.proc.system.Controller;

import com.proc.system.Model.*;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminPurReqController {

    PurReqObjectRepository purReqObjectRepository;
    ItemRepository itemRepository;
    SfppRepository  sfppRepository;

    public AdminPurReqController(PurReqObjectRepository purReqObjectRepository,ItemRepository itemRepository,SfppRepository  sfppRepository){

          this.purReqObjectRepository=purReqObjectRepository;
          this.itemRepository=itemRepository;
          this.sfppRepository=sfppRepository;    }

    @PostMapping("/processPurReq")
    public String ProcessPurReq(@RequestParam(required = false) String action, @RequestParam(required = false) Integer selectedReqId, Model model, HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }

        if(action==null||selectedReqId==null){
            model.addAttribute("listOfReqs",purReqObjectRepository.findByStatus("Pending"));
            return"adminPurReq";


        }
      if (action.equals("approve")){

          Integer poNumber=(int)((Math.random()*100)+1);//send

          PurReqObject purReqObject= purReqObjectRepository.findById(selectedReqId).get();

         Integer itemId= purReqObject.getItem(); //send
         Integer itemQuantity= purReqObject.getQuantity();//send

         ItemObject item=itemRepository.findById(itemId).get();

         String itemName=item.getItemName();//send

          List<SfppObject> listofSfppObject= sfppRepository.findByitem(itemId);//send


          model.addAttribute("poNumber",poNumber);
          model.addAttribute("itemId",itemId);
          model.addAttribute("itemName",itemName);
          model.addAttribute("itemQuantity",itemQuantity);
          model.addAttribute("listofSfppObject",listofSfppObject);

          session.setAttribute("poNumber",poNumber);
          session.setAttribute("itemId",itemId);
          session.setAttribute("itemName",itemName);
          session.setAttribute("itemQuantity",itemQuantity);
          session.setAttribute("listofSfppObject",listofSfppObject);
          session.setAttribute("selectedReqId",selectedReqId);


         return "createPO";






      }
      if(action.equals("disapprove")){
          purReqObjectRepository.updateStatusAsCancelled(selectedReqId);


          model.addAttribute("listOfReqs",purReqObjectRepository.findAll());
          return"adminPurReq";

      }

        model.addAttribute("listOfReqs",purReqObjectRepository.findByStatus("Pending"));
        return"adminPurReq";

    }


}
