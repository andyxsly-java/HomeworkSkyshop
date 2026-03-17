package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.error.ShopError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException
            (NoSuchProductException e) {


        ShopError error = null;
        error = new ShopError(
                "PRODUCT_NOT_FOUND",
                error.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).

                body(error);
    }
}
