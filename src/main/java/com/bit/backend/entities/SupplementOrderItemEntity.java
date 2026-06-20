package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "supplement_order_item")
public class SupplementOrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private SupplementOrderEntity order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private SupplementProductEntity product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price_at_order", nullable = false)
    private Double priceAtOrder;

    public SupplementOrderItemEntity() {}

    public SupplementOrderItemEntity(Long id, SupplementOrderEntity order,
                                     SupplementProductEntity product,
                                     Integer quantity, Double priceAtOrder) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.priceAtOrder = priceAtOrder;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public SupplementOrderEntity getOrder() { return order; }
    public void setOrder(SupplementOrderEntity order) { this.order = order; }

    public SupplementProductEntity getProduct() { return product; }
    public void setProduct(SupplementProductEntity product) { this.product = product; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPriceAtOrder() { return priceAtOrder; }
    public void setPriceAtOrder(Double priceAtOrder) { this.priceAtOrder = priceAtOrder; }
}
