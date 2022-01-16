package com.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity()
@Table(name = "lobby")
public class Lobby implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    String name;

    @Column(name = "seats")
    @Min(value = 30)
    @Max(value = 1000)
    int seats;

    @Column(name = "status")
    @ColumnDefault("true")
    boolean status;

    @Column(name = "money")
    @Min(value = 5000000)
    @Max(value = 1000000000)
    long money;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",
            columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createdAt = new Date();

    @OneToMany(mappedBy = "lobby")
    @JsonIgnore
    private Set<Bill> bills;

    public Lobby() {
        this.status = true;
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getseats() {
        return seats;
    }

    public void setseats(int seats) {
        this.seats = seats;
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

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

}
