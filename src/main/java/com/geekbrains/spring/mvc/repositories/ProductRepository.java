package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.exceptions.ProductNotFoundException;
import com.geekbrains.spring.mvc.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> catalog;
    private Long nextId;  // управляем Id на уровне репозитория (для тестового примера, думаю, допускается))

    @PostConstruct
    public void init() {
        catalog = new ArrayList<>();
        nextId = 1L;
        addProduct(new Product("Полотенце", 125));
        addProduct(new Product("Зубная паста", 70));
        addProduct(new Product("Зубная щетка", 95));
        addProduct(new Product("Мыло", 40));
        addProduct(new Product("Шампунь", 140));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(catalog);
    }

    public Product getProductById(Long id) throws ProductNotFoundException {
        for (Product product : catalog) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new ProductNotFoundException(id);
    }

    public synchronized void addProduct(Product product) {
        // simply ignore and reassign the "id" field of a product
        product.setId(nextId);
        catalog.add(product);
        nextId++;
    }

    public synchronized void removeProductById(Long id) {
        catalog.removeIf(p -> p.getId().equals(id));
    }
}
