package org.skypro.skyshop.model.exception;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super("Товар не найден");
    }
    public NoSuchProductException(String message) {
        super(message);
    }
}
