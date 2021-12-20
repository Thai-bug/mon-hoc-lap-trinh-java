package com.pojos;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.Set;

@Entity
@Table(name = "bill")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "provisional_momeny")
    private long provisionalMoney;

    @Column(name = "final_money")
    private long finalMoney;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",
            columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createdAt = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;

    // @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_id")
    private Employee employee;

    // @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lobby_id")
    private Lobby lobby;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "bill_drink",
            joinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id", referencedColumnName = "id"))
    private Set<Drink> drinkList;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "bill_food",
            joinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "food_id", referencedColumnName = "id"))
    private Set<Food> foodList;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "bill_service",
            joinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private Set<Service> serviceList;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "total_table")
    private int totalTable;

    public Bill() {
    }

    public int getTotalTable() {
        return totalTable;
    }

    public void setTotalTable(int totalTable) {
        this.totalTable = totalTable;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Drink> getDrinkList() {
        return drinkList;
    }

    public void setDrinkList(Set<Drink> drinkList) {
        this.drinkList = drinkList;
    }

    public Set<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(Set<Food> foodList) {
        this.foodList = foodList;
    }

    public Set<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(Set<Service> serviceList) {
        this.serviceList = serviceList;
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

    public long getProvisionalMoney() {
        return provisionalMoney;
    }

    public void setProvisionalMoney(long provisionalMoney) {
        this.provisionalMoney = provisionalMoney;
    }

    public long getFinalMoney() {
        return finalMoney;
    }

    public void setFinalMoney(long finalMoney) {
        this.finalMoney = finalMoney;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
