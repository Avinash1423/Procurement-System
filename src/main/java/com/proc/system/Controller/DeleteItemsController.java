package com.proc.system.Controller;

import com.proc.system.Model.DeleteItems;
import com.proc.system.Model.ItemList;
import com.proc.system.Model.ItemRepository;
import com.proc.system.Model.SfppRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.awt.event.ItemListener;
import java.util.List;

@Controller
@Transactional
public class DeleteItemsController {

    private final SfppRepository sfppRepository;
    DeleteItems deleteItems;


    public DeleteItemsController(DeleteItems deleteItems, SfppRepository sfppRepository){

        this.deleteItems=deleteItems;
        this.sfppRepository = sfppRepository;
    }



    @PostMapping("/deleteItems")
    public String deleteItems(@ModelAttribute ItemList itemList, HttpSession session ){

        String role=(String)session.getAttribute("role");

        if ( role==null||!role.equals("Admin")) {
            return "/adminLoginPage";
        }

        List<Integer> listOfCodes=itemList.getListOfCodes();


        for(Integer code:listOfCodes){
            sfppRepository.deleteBySfppId_itemCode(code);

        }

        deleteItems.deletelist(listOfCodes);


        return "redirect:/ManageItems";

    }

}
