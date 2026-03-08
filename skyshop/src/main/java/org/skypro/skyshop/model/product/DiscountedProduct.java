package org.skypro.skyshop.model.product;

import java.util.UUID;
import org.skypro.skyshop.model.search.Searchable;

public class DiscountedProduct extends Product {
    private final int price;
    private final int discount;

    public DiscountedProduct(UUID id, String name, int price, int discount) {
        super(id,name);

        if (price <= 0) {
            throw new IllegalArgumentException("Базовая цена должна быть больше 0: " + price);
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100: " + discount);
        }

        this.price = price;
        this.discount = discount;

    }

    public int getFinalPrice() {
        return price * (100 - discount) / 100;
    }

    @Override
    public int getPrice() {
        return price - (price * discount / 100);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String getContentType() {
        return "";
    }
}