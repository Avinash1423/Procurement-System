package com.proc.system.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageItemsController {


    public ManageItemsController(){

    }

    @GetMapping("/addNewItem")
    public String addNewItem(){

        return "addNewItem";

    }



}
