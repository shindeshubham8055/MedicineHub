package tda.darkarmy.medicalecommerce.dto;

public class CartItemDto {
    private Long productId;
    private Integer quantity;

    public CartItemDto(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItemDto{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
