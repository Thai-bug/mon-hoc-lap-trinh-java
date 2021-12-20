package com.request;

import com.pojos.*;

import java.util.Date;
import java.util.List;

public class BillRequest {
    String code;
    String customerName;
    List<Food> addedFoods;
    List<Food> deletedFoods;

    List<Drink> addDrinks;
    List<Drink> deletedDrink;

    List<Service> addedServices;
    List<Service> deletedServices;

    String name;

    Status status;

    Date beginDate;
    Date endDate;

    Lobby lobby;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Food> getAddedFoods() {
        return addedFoods;
    }

    public void setAddedFoods(List<Food> addedFoods) {
        this.addedFoods = addedFoods;
    }

    public List<Food> getDeletedFoods() {
        return deletedFoods;
    }

    public void setDeletedFoods(List<Food> deletedFoods) {
        this.deletedFoods = deletedFoods;
    }

    public List<Drink> getAddDrinks() {
        return addDrinks;
    }

    public void setAddDrinks(List<Drink> addDrinks) {
        this.addDrinks = addDrinks;
    }

    public List<Drink> getDeletedDrink() {
        return deletedDrink;
    }

    public void setDeletedDrink(List<Drink> deletedDrink) {
        this.deletedDrink = deletedDrink;
    }

    public List<Service> getAddedServices() {
        return addedServices;
    }

    public void setAddedServices(List<Service> addedServices) {
        this.addedServices = addedServices;
    }

    public List<Service> getDeletedServices() {
        return deletedServices;
    }

    public void setDeletedServices(List<Service> deletedServices) {
        this.deletedServices = deletedServices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }
}
