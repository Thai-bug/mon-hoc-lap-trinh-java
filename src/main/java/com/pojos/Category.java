package com.pojos;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    @ColumnDefault("true")
    private boolean status = true;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable = false,
            columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date createdAt = new Date();

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
    public Category() {
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private Set<Product> products;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Category(String title, boolean status, Date createdAt) {
        this.title = title;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
