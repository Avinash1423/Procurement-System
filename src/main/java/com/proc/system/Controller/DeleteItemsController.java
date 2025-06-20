package com.proc.system.Controller;

import com.proc.system.Model.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
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
    ItemRepository itemRepository;


    public DeleteItemsController(DeleteItems deleteItems, SfppRepository sfppRepository, ItemRepository itemRepository) {

        this.deleteItems = deleteItems;
        this.sfppRepository = sfppRepository;
        this.itemRepository = itemRepository;

    }


    @PostMapping("/deleteItems")
    public String deleteItems(@RequestParam Integer itemToDelete, HttpSession session, Model model) {

        System.out.println(itemToDelete);
        String role = (String) session.getAttribute("role");

        if (role == null || !role.equals("Admin")) {
            return "adminLoginPage";
        }

     //   List<Integer> listOfCodes = itemList.getListOfCodes();

        try {
                 sfppRepository.deleteBySfppId_itemCode(itemToDelete);
                 itemRepository.deleteById(itemToDelete);


        } catch (Exception  e) {

            e.printStackTrace();
            List<ItemObject> listOfItems = itemRepository.findAll();
            model.addAttribute("ItemDeleteError", "Cannot delete: Item/s may be associated with a purchase requisition or other records.");
            model.addAttribute("listOfItems", listOfItems);
            model.addAttribute("ItemList", new ItemList());
            return "ManageItems";
        }

        // deleteItems.deletelist(listOfCodes);

        List<ItemObject> listOfItems = itemRepository.findAll();
        model.addAttribute("ItemDeleteError", "Cannot delete: Item/s may be associated with a purchase requisition or other records.");
        model.addAttribute("listOfItems", listOfItems);
        model.addAttribute("ItemList", new ItemList());
        return "ManageItems";

    }
}
