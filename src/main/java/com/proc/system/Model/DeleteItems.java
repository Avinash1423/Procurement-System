package com.proc.system.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;

@Repository
public class DeleteItems {

    private final SfppRepository sfppRepository;
    ItemRepository itemRepository;

   @Autowired
   public DeleteItems(ItemRepository itemRepository, SfppRepository sfppRepository){

       this.itemRepository=itemRepository;
       this.sfppRepository = sfppRepository;
   }

    public String  deletelist(List<Integer> listOfCodes, Model model){


try {
    for (Integer code : listOfCodes) {

        sfppRepository.deleteBySfppId_itemCode(code);
        itemRepository.deleteById(code);

    }
}catch (Exception e) {

}
    List<ItemObject> listOfItems=itemRepository.findAll();
    model.addAttribute("ItemDeleteError","Cannot delete: Item/s may be associated with a purchase requisition or other records.");
    model.addAttribute("listOfItems",listOfItems);
    model.addAttribute("ItemList",new ItemList());

        return "redirect:/ManageItems";

    }
}
