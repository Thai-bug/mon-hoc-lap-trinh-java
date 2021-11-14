package com.pojos;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="first_name", nullable = false)
    @ColumnDefault("")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @ColumnDefault("")
    private String lastName;

    @Column(name = "email", nullable = false)
    @ColumnDefault("")
    private String email;

    @Column(name = "password", nullable = false)
    @ColumnDefault("")
    private String password;

    @Column(name = "phone_number", nullable = false)
//    @ColumnDefault("")
    @NotNull(message = "{user.error.phoneNumber.required }")
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    @ColumnDefault("true")
    private boolean status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable = false,
            columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date createdAt = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
