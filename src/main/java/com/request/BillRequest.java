package com.request;

import com.pojos.*;

import java.util.Date;
import java.util.Set;

public class BillRequest {
    String code;
    String customerName;
    Set<Food> addedFoods;
    Set<Food> deletedFoods;

    Set<Drink> addedDrinks;
    Set<Drink> deletedDrinks;

    Set<Service> addedServices;
    Set<Service> deletedServices;

    String name;

    Status status;

    Date beginDate;
    Date endDate;

    Lobby lobby;

    Employee employee;

    long total;

    int tables;

    long deposit;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getTables() {
        return tables;
    }

    public void setTables(int tables) {
        this.tables = tables;
    }

    public long getDeposit() {
        return deposit;
    }

    public void setDeposit(long deposit) {
        this.deposit = deposit;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Set<Food> getAddedFoods() {
        return addedFoods;
    }

    public void setAddedFoods(Set<Food> addedFoods) {
        this.addedFoods = addedFoods;
    }

    public Set<Food> getDeletedFoods() {
        return deletedFoods;
    }

    public void setDeletedFoods(Set<Food> deletedFoods) {
        this.deletedFoods = deletedFoods;
    }

    public Set<Drink> getAddedDrinks() {
        return addedDrinks;
    }

    public void setAddedDrinks(Set<Drink> addedDrinks) {
        this.addedDrinks = addedDrinks;
    }

    public Set<Drink> getDeletedDrinks() {
        return deletedDrinks;
    }

    public void setDeletedDrinks(Set<Drink> deletedDrinks) {
        this.deletedDrinks = deletedDrinks;
    }

    public Set<Service> getAddedServices() {
        return addedServices;
    }

    public void setAddedServices(Set<Service> addedServices) {
        this.addedServices = addedServices;
    }

    public Set<Service> getDeletedServices() {
        return deletedServices;
    }

    public void setDeletedServices(Set<Service> deletedServices) {
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
