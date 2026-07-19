package com.smartfoodstall.billing.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal gst;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal grandTotal;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillItem> items = new ArrayList<>();

    protected Bill() {
    }

    public Bill(LocalDateTime createdAt, BigDecimal subtotal, BigDecimal gst, BigDecimal grandTotal) {
        this.createdAt = createdAt;
        this.subtotal = subtotal;
        this.gst = gst;
        this.grandTotal = grandTotal;
    }

    public void addItem(BillItem item) {
        items.add(item);
        item.setBill(this);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getGst() {
        return gst;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public List<BillItem> getItems() {
        return items;
    }
}
