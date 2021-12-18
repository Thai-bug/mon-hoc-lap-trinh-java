package com.request;

import java.util.List;

public class BillRequest {
    int id;
    String customerName;
    List<Integer> addFoods;
    List<Integer> deleteFoods;

    List<Integer> addDrinks;
    List<Integer> deletedDrink;

    List<Integer> addedServices;
    List<Integer> deletedServices;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Integer> getAddFoods() {
        return addFoods;
    }

    public void setAddFoods(List<Integer> addFoods) {
        this.addFoods = addFoods;
    }

    public List<Integer> getDeleteFoods() {
        return deleteFoods;
    }

    public void setDeleteFoods(List<Integer> deleteFoods) {
        this.deleteFoods = deleteFoods;
    }

    public List<Integer> getAddDrinks() {
        return addDrinks;
    }

    public void setAddDrinks(List<Integer> addDrinks) {
        this.addDrinks = addDrinks;
    }

    public List<Integer> getDeletedDrink() {
        return deletedDrink;
    }

    public void setDeletedDrink(List<Integer> deletedDrink) {
        this.deletedDrink = deletedDrink;
    }

    public List<Integer> getAddedServices() {
        return addedServices;
    }

    public void setAddedServices(List<Integer> addedServices) {
        this.addedServices = addedServices;
    }

    public List<Integer> getDeletedServices() {
        return deletedServices;
    }

    public void setDeletedServices(List<Integer> deletedServices) {
        this.deletedServices = deletedServices;
    }
}
