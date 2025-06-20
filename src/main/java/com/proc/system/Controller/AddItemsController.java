package com.proc.system.Controller;

import com.proc.system.Model.ItemObject;
import com.proc.system.Model.ItemRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddItemsController {

    ItemRepository itemRepository;

    @Autowired
    public AddItemsController(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;

    }


    @PostMapping("/addNewItem")
    public String addItem(@RequestParam String itemName, @RequestParam String uom, Model model, HttpSession session) {

        String role = (String) session.getAttribute("role");

        if (role == null || !role.equals("Admin")) {
            return "adminLoginPage";
        }

            if (itemName.isEmpty() || uom.isEmpty()) {
                model.addAttribute("fieldIncompleteError", "Complete All  Required field");
                return "addNewItem";

            } else {
                ItemObject newItem = new ItemObject(itemName, uom);

                itemRepository.save(newItem);
            }
            return "redirect:/ManageItems";

        }

    }



