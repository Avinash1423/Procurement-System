package com.proc.system.Controller;

import com.proc.system.Model.ItemRepository;
import com.proc.system.Model.PurReqObject;
import com.proc.system.Model.PurReqObjectRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PurReqController {

    ItemRepository itemRepository;
    PurReqObjectRepository purReqObjectRepository;

    public PurReqController(ItemRepository itemRepository, PurReqObjectRepository purReqObjectRepository) {

        this.itemRepository = itemRepository;
        this.purReqObjectRepository = purReqObjectRepository;

    }


    @GetMapping("/newPurReq")
    public String newPurReq(Model model, HttpSession session) {
        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Employee")) {
            return "/EmployeeLoginPage";

        }

        model.addAttribute("itemPickList", itemRepository.findAll());
        return "newPurReq";
    }

    @PostMapping("/purReqData")
    public String purReqData(@RequestParam(required = false) Integer item, @RequestParam(required = false) String quantity, Model model, HttpSession session) {

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Employee")) {
            return "/EmployeeLoginPage";

        }
        if (item==null || quantity==null) {

            model.addAttribute("itemPickList", itemRepository.findAll());
            return "newPurReq";

        }
        Integer intQuantity=Integer.parseInt(quantity);
        Integer loggedInEmployee=(Integer)session.getAttribute("LoggedInEmployee");
        PurReqObject purReqObject=new PurReqObject(item,intQuantity,loggedInEmployee);
        purReqObjectRepository.save(purReqObject);
        model.addAttribute("itemPickList", itemRepository.findAll());
        return "newPurReq";

    }

    @GetMapping("/LogoutEmp")
    public String LogOut(HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Employee")){
            return "/EmployeeLoginPage";

        }

        session.setAttribute("role",null);
        return"MainPage";
    }
}
