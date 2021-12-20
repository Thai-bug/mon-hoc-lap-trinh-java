package com.pojos;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.validator.employee.CompanyEmail;
import com.validator.employee.Phone;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity()
@Table(name = "employee")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Employee implements Serializable {
    public static final String ADMIN = "ADMIN";
    public static final String MANAGER = "MANAGER";
    public static final String USER = "USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, message = "Không để trống")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 1, message = "Không để trống")
    @Column(name = "last_name")
    private String lastName;

    @CompanyEmail(message = "Email không hợp lệ")
//    @UniqueEmail(message = "Email tồn tại")
    @Column(name = "email")
    private String email;

    @Size(min = 8, message = "Mật khẩu chưa mạnh")
    @Column(name = "password")
    private String password;

    @Phone(message = "Số điện thoại không hợp lệ")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Min(value = 1, message = "Vui lòng chọn giới tính")
    @Column(name = "gender")
    private int gender;

    @Column(name = "status")
    @ColumnDefault("true")
    private boolean status;

    @NotNull(message = "Không để trống")
    @Column(name = "role")
    private String role;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",
            columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createdAt = new Date();

    // @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Employee parent;

    // @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
    @JsonIgnore
    private Set<Bill> bills;

    @Column(name = "avatar")
    private String avatarLink;

    @Transient
    private MultipartFile avatar;

    @Transient
    private String confirmPassword;

    public Employee() {
        this.password = null;
        this.phoneNumber = null;
        this.status = true;
        this.confirmPassword = "";
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public Employee getParent() {
        return parent;
    }

    public void setParent(Employee parent) {
        this.parent = parent;
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }


}
