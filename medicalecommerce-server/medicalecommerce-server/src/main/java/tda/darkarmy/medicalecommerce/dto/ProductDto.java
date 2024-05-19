package tda.darkarmy.medicalecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {
    private String name;
    private String expiryDate;
    private String description;
    private double price;
    private String brand;
    private String category;
    private int stock;
    private MultipartFile image;

    public ProductDto(String name, String expiryDate, String description, double price, String brand, String category, int stock, MultipartFile image) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.stock = stock;
        this.image = image;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "name='" + name + '\'' +
                ", expiryDate=" + expiryDate +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", stock=" + stock +
                ", image=" + image +
                '}';
    }
}
