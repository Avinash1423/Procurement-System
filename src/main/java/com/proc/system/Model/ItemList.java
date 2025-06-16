package com.proc.system.Model;

import java.util.List;


public class ItemList {

    public List<Integer> listOfCodes;
    private List<String> listOfNames;
    private List<String> list0fUoms;


    public List<String> getList0fUoms() {
        return list0fUoms;
    }

    public void setList0fUoms(List<String> list0fUoms) {
        this.list0fUoms = list0fUoms;
    }


    public List<Integer> getListOfCodes() {
        return listOfCodes;
    }

    public void setListOfCodes(List<Integer> listOfCodes) {
        this.listOfCodes = listOfCodes;
    }

    public List<String> getListOfNames() {
        return listOfNames;
    }

    public void setListOfNames(List<String> listOfNames) {
        this.listOfNames = listOfNames;
    }

}
