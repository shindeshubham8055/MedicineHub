package tda.darkarmy.medicalecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private String status="ORDERED";

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", nullable = false)
    @JsonIgnore
    private Orders orders;

    public OrderItem() {
    }

    public OrderItem(Long id, Integer quantity, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public OrderItem(Long id, Integer quantity, String status, Product product, Orders orders) {
        this.id = id;
        this.quantity = quantity;
        this.status = status;
        this.product = product;
        this.orders = orders;
    }

    public OrderItem(Product product, Integer quantity, Orders orders) {
        this.product = product;
        this.quantity = quantity;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", product=" + product +
                ", orders=" + orders +
                '}';
    }
}
