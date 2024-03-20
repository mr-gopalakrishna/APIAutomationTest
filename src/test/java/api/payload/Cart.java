package api.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cart {


    public int productId;

    public int quantity;
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
