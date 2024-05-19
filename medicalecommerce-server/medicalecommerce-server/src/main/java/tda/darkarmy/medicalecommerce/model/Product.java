package tda.darkarmy.medicalecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String expiryDate;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private String imageUrl;

    private Boolean isApproved = false;

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "cart_item_id")
    @JsonIgnore
    private CartItem cartItem;

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "order_item_id")
    @JsonIgnore
    private OrderItem orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    public Product() {}

    public Product(Long id, String name, String expiryDate, String description, double price, String brand, String category, int stock, String imageUrl, Boolean isApproved) {
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.isApproved = isApproved;
    }

    public Product(Long id, String name, String expiryDate, String description, double price, String brand, String category, int stock, String imageUrl, Boolean isApproved, User user) {
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.isApproved = isApproved;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expiryDate=" + expiryDate +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", stock=" + stock +
                ", imageUrl='" + imageUrl + '\'' +
                ", isApproved=" + isApproved +
                '}';
    }
}
