package com.proc.system.Controller;

import com.proc.system.Model.DeleteItems;
import com.proc.system.Model.ItemList;
import com.proc.system.Model.ItemRepository;
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
public class DeleteItemsController {

    DeleteItems deleteItems;


    public DeleteItemsController( DeleteItems deleteItems){

        this.deleteItems=deleteItems;

    }



    @PostMapping("/deleteItems")
    public String deleteItems(@ModelAttribute ItemList itemList ){

        List<Integer> listOfCodes=itemList.getListOfCodes();
        deleteItems.deletelist(listOfCodes);
        return "redirect:/ManageItems";

    }

}
