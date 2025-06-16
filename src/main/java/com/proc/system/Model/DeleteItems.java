package com.proc.system.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeleteItems {

   ItemRepository itemRepository;

   @Autowired
   public DeleteItems(ItemRepository itemRepository){

       this.itemRepository=itemRepository;
   }

    public void  deletelist(List<Integer> listOfCodes){

        for (Integer code:listOfCodes){

            itemRepository.deleteById(code);


        }

    }
}
