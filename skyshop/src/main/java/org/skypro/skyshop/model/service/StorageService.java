package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.addAll;

@Service
public class StorageService {

    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService () {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        initData();
    }

    private void initData() {
        Product book = new SimpleProduct(
                UUID.randomUUID(),
                "Книга",
                500
        );

        Product toy = new DiscountedProduct(
                UUID.randomUUID(),
                "Игрушка",
                1737,
                37
        );

        Product bag = new FixPriceProduct(
                UUID.randomUUID(),
                "Подарочный пакет"
        );

        products.put(book.getId(), book);
        products.put(toy.getId(), toy);
        products.put(bag.getId(), bag);

        Article article1 = new Article(
                UUID.randomUUID(),
                "Java Streams",
                "Описание Stream API"
        );

        Article article2 = new Article(
                UUID.randomUUID(),
                "Spring Boot",
                "Основы Spring Boot"
        );

        articles.put(article1.getId(), article1);
        articles.put(article2.getId(), article2);
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(products.values());
        result.addAll(articles.values());

        return result;

    }

        public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }
}

