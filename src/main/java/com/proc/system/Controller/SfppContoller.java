package com.proc.system.Controller;

import com.proc.system.Model.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class SfppContoller {

    private final SfppRepository sfppRepository;
    ItemRepository itemRepository;
    SupplierObjectRepository supplierobjectrepository;

    @Autowired
    public SfppContoller(ItemRepository itemRepository, SupplierObjectRepository supplierobjectrepository, SfppRepository sfppRepository) {
        this.supplierobjectrepository = supplierobjectrepository;
        this.itemRepository = itemRepository;
        this.sfppRepository = sfppRepository;
    }


    @PostMapping("/SfppReturn")
    public String supplierForPurchasePart(@RequestParam(required = false) String chooseItem, @RequestParam(required = false) String chooseSupplier, @RequestParam(required = false) String price, Model model) {

        if (chooseItem==null || chooseSupplier==null || price==null) {

            model.addAttribute("itemPickList", itemRepository.findAll());
            model.addAttribute("supplierPickList", supplierobjectrepository.findAll());
            model.addAttribute("newSfppTable",sfppRepository.getAllSfppViews());
            return "/supplierForPurchasePart";

        } else {


            Integer itemCode = Integer.parseInt(chooseItem);
            Integer SupplierId = Integer.parseInt(chooseSupplier);
            Integer numberPrice = Integer.parseInt(price);

            ItemObject item=itemRepository.findById(itemCode).get();//new
            SupplierObject supplier=supplierobjectrepository.findById(SupplierId).get();


            SfppId sfppId = new SfppId(itemCode, SupplierId);


            if (!sfppRepository.findById(sfppId).isPresent()) {
                SfppObject sfppObject = new SfppObject(item, supplier, numberPrice);
                sfppRepository.save(sfppObject);

                model.addAttribute("itemPickList", itemRepository.findAll());
                model.addAttribute("supplierPickList", supplierobjectrepository.findAll());
                model.addAttribute("newSfppTable",sfppRepository.getAllSfppViews());
                return "/supplierForPurchasePart";
            }
            else {

                model.addAttribute("linkExistsError", "The link already exists");
                model.addAttribute("supplierPickList", supplierobjectrepository.findAll());
                model.addAttribute("itemPickList", itemRepository.findAll());
                model.addAttribute("newSfppTable",sfppRepository.getAllSfppViews());
                return "/supplierForPurchasePart";
            }
//            model.addAttribute("itemPickList", itemRepository.findAll());
//            model.addAttribute("supplierPickList", supplierobjectrepository.findAll());
//            return "/supplierForPurchasePart";
        }

    }

    @PostMapping("/destroyLink")
    public String destroyLink(@RequestParam String link, HttpSession session,Model model){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }

        String[] parts=link.split("-");

        Integer itemCode=Integer.parseInt(parts[0]);
        Integer supplierId=Integer.parseInt(parts[1]);



        SfppId sfppid=new SfppId(itemCode,supplierId);
        sfppRepository.deleteById(sfppid);

        model.addAttribute("newSfppTable",sfppRepository.getAllSfppViews());
        model.addAttribute("itemPickList",itemRepository.findAll());
        model.addAttribute("supplierPickList",supplierobjectrepository.findAll());
        return "supplierForPurchasePart";


    }
}

