package com.pojos;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill")
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "provisional_momeny")
    private double provisionalMoney;

    @Column(name = "final_money")
    private double finalMoney;

    @Column(name = "status")
    @ColumnDefault("true")
    private boolean status;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",
            columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createdAt = new Date();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private Employee employee;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lobby_id")
    private Lobby lobby;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "bill_drink",
            joinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id", referencedColumnName = "id"))
    private List<Drink> drinkList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "bill_food",
            joinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "food_id", referencedColumnName = "id"))
    private List<Food> foodList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "bill_service",
            joinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private List<Service> serviceList;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill")
//    private List<BillDetail> detailList;

    public Bill() {
    }
    public Lobby getLobby() {
        return lobby;
    }

//    public List<BillDetail> getDetailList() {
//        return detailList;
//    }
//
//    public void setDetailList(List<BillDetail> detailList) {
//        this.detailList = detailList;
//    }
//
//
//    public List<BillDetail> getBillDetailList() {
//        return detailList;
//    }
//
//    public void setBillDetailList(List<BillDetail> detailList) {
//        this.detailList = detailList;
//    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getProvisionalMoney() {
        return provisionalMoney;
    }

    public void setProvisionalMoney(double provisionalMoney) {
        this.provisionalMoney = provisionalMoney;
    }

    public double getFinalMoney() {
        return finalMoney;
    }

    public void setFinalMoney(double finalMoney) {
        this.finalMoney = finalMoney;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
