package com.request;

import com.pojos.Drink;
import com.pojos.Food;
import com.pojos.Service;
import com.pojos.Status;

import java.util.Date;
import java.util.List;

public class BillRequest {
    String code;
    String customerName;
    List<Food> addFoods;
    List<Food> deleteFoods;

    List<Drink> addDrinks;
    List<Drink> deletedDrink;

    List<Service> addedServices;
    List<Service> deletedServices;

    String name;

    Status status;

    Date beginDate;
    Date endDate;

    int lobbyId;

    public String getId() {
        return code;
    }

    public void setId(String code) {
        this.code = code;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


}
