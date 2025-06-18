package com.proc.system.Controller;

import com.proc.system.Model.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminPoController {

    ItemRepository itemRepository;
    SfppRepository sfppRepository;
    public  AdminPoController( ItemRepository itemRepository, SfppRepository sfppRepository){

        this.itemRepository=itemRepository;
        this.sfppRepository=sfppRepository;

    }

    @GetMapping("/adminCreateNewPo")
    public String adminCreateNewPo(Model model ,HttpSession session){


        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }
        model.addAttribute("itemPickList", itemRepository.findAll());
        return "adminNewPo";

    }

    @PostMapping("/newAdminPo")
    public String newAdminPo(@RequestParam(required = false) Integer itemId, @RequestParam(required = false) String quantity, Model model, HttpSession session) {

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }


        if (itemId == null || quantity == null) {

            model.addAttribute("itemPickList", itemRepository.findAll());
            return "adminNewPo";

        }
        Integer poNumber=(int)((Math.random()*100)+1);
        Integer itemQuantity = Integer.parseInt(quantity);


        ItemObject item=itemRepository.findById(itemId).get();

        String itemName=item.getItemName();//send

        List<SfppObject> listofSfppObject= sfppRepository.findByitem(itemId);

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
       // session.setAttribute("selectedReqId",selectedReqId);


        return "createPO";



       // return "/adminNewPo2";
    }
}