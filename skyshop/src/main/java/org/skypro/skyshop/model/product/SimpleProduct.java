package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(UUID id,String name, int price) {
        super(id,name);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0: " + price);
        } else {
            this.price = price;
        }
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public String getContentType() {
        return "";
    }
}
