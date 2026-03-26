package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;
import org.skypro.skyshop.model.service.BasketService;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void addProduct_whenProductNoExists_shouldThrowException(ProductBasket basketService) {
        UUID invalidId = UUID.randomUUID();
        when(storageService.getProductById(invalidId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(invalidId));
    }

    @Test
    void addProduct_whenProductExists_shouldCallBasketAddProduct(ProductBasket productBasket) {
        UUID validId = UUID.randomUUID();
        Product product = mock(Product.class);

        when(storageService.getProductById(validId)).thenReturn(Optional.of(product));

        basketService.addProduct(validId);

        verify(productBasket, times(1)).addProduct(validId);
    }

    @Test
    void getUserBasket_whenBasketEmpty_shouldReturnEmptyBasket(ProductBasket productBasket) {
        when(productBasket.getProducts()).thenReturn(Map.of());

        UserBasket userBasket = basketService.getUserBasket();

        assertThat(userBasket.getItems()).isEmpty();
        assertThat(userBasket.getTotal()).isZero();
    }

    @Test
    void getUserBasket_whenBasketNoEmpty_shouldReturnCorrectBasket(ProductBasket productBasket) {
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);

        UserBasket userBasket = basketService.getUserBasket();

        when(storageService.getProductById(id))
                .thenReturn(Optional.of(product));
        when(productBasket.getProducts()).thenReturn(Map.of(id, 1));

        assertThat(userBasket.getItems()).hasSize(3);
        assertThat(userBasket.getTotal()).isEqualTo(500);

    }
}
