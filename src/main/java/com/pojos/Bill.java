package com.pojos;

import org.hibernate.annotations.ColumnDefault;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lobby_id")
    private Lobby lobby;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill")
    private List<BillDetail> detailList;

    public Lobby getLobby() {
        return lobby;
    }

    public List<BillDetail> getBillDetailList() {
        return detailList;
    }

    public void setBillDetailList(List<BillDetail> detailList) {
        this.detailList = detailList;
    }

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
