package com.proc.system.Controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.proc.system.Model.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminViewController {

    private final SfppRepository sfppRepository;
    ItemRepository itemRepository;
    SupplierObjectRepository supplierobjectrepository;
    PurReqObjectRepository purReqObjectRepository;

    @Autowired
    public AdminViewController(ItemRepository itemRepository, SupplierObjectRepository supplierobjectrepository, SfppRepository sfppRepository, PurReqObjectRepository purReqObjectRepository){

        this.itemRepository=itemRepository;
        this.supplierobjectrepository=supplierobjectrepository;
        this.sfppRepository = sfppRepository;
        this.purReqObjectRepository=purReqObjectRepository;
    }

   @GetMapping("/ManageUsers")
    public String manageUsers(HttpSession session){



        return"ManageUsers";

    }

    @GetMapping("/ManageItems")
    public String ManageItems(Model model,HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")){
            return "adminLoginPage";

        }

        List<ItemObject> listOfItems=itemRepository.findAll();
       // return listOfItems;
       model.addAttribute("listOfItems",listOfItems);
       model.addAttribute("ItemList",new ItemList());
       return "ManageItems";

    }

    @GetMapping("/ManageSuppliers")
    public String ManageSuppliers(Model model ,HttpSession session) {


        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")){
            return "adminLoginPage";

        }
       List<SupplierObject> listOfSuppliers=supplierobjectrepository.findAll();



       model.addAttribute("listOfSuppliers",listOfSuppliers);


        return "ManageSuppliers";

    }
    @GetMapping("/Sfpp")
    public String Sfpp(Model model , HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")){
            return "adminLoginPage";

        }

        model.addAttribute("newSfppTable",sfppRepository.getAllSfppViews());
        model.addAttribute("itemPickList",itemRepository.findAll());
        model.addAttribute("supplierPickList",supplierobjectrepository.findAll());
        return "supplierForPurchasePart";

    }

    @GetMapping("/ViewPurReqs")
    public String ViewPurReqs(Model model,HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")){
            return "adminLoginPage";

        }

        model.addAttribute("listOfReqs",purReqObjectRepository.findByStatus("Pending"));
        return"adminPurReq";

    }

    @GetMapping("/Logout")
    public String LogOut(HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")){
            return "adminLoginPage";

        }

        session.setAttribute("role",null);
        return"MainPage";
    }

    @GetMapping("/adminHome")
    public String adminHome(HttpSession session){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")){
            return "adminLoginPage";

        }

        return "adminView";

    }
}


